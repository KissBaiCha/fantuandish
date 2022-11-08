package com.chixing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chixing.commons.IGlobalCache;
import com.chixing.entity.Food;
import com.chixing.entity.SecondKill;
import com.chixing.entity.vo.SecondKillVo;
import com.chixing.mapper.FoodMapper;
import com.chixing.mapper.SecondKillMapper;
import com.chixing.service.IFoodService;
import com.chixing.service.ISecondKillService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2022-09-30
 */

@Service
@Slf4j
public class SecondKillServiceImpl implements ISecondKillService {

    @Autowired
    private SecondKillMapper secondKillMapper;
    @Autowired
    private FoodMapper foodMapper;
    @Autowired
    private IGlobalCache iGlobalCache;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public SecondKill getById(Integer skId) {
        return secondKillMapper.selectById(skId);
    }

    @Override
    @Scheduled(cron = "0 */60 * * * ?")//1h
    public void getAllFromMysql() {
        String key = "allSkPro:skpro_*";
        Set<String> keys = iGlobalCache.getKeys(key);
        if (keys.size() > 0) {
            iGlobalCache.getRedisTemplate().delete(keys);
        }
        QueryWrapper<SecondKill> secondKillQueryWrapper = new QueryWrapper<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        secondKillQueryWrapper.gt("second_kill_start_time", LocalDateTime.parse(LocalDate.now()+" 00:00:00",formatter));
//        secondKillQueryWrapper.lt("second_kill_end_time",LocalDateTime.parse(LocalDate.now().plusDays(1)+" 00:00:00",formatter));
        List<SecondKill> secondKills = secondKillMapper.selectList(secondKillQueryWrapper);
        for (SecondKill secondKill : secondKills) {
            Integer secondKillId = secondKill.getSecondKillId();
            BigDecimal secondKillPrice = secondKill.getSecondKillPrice();
            Integer foodId = secondKill.getFoodId();
            Food food = foodMapper.selectById(foodId);
            String foodMainImg = food.getFoodMainImg();
            String foodName = food.getFoodName();
            BigDecimal foodPrice = food.getFoodPrice();
            Integer shopId = food.getShopId();
            Integer secondKillStock = secondKill.getSecondKillStock();
            SecondKillVo secondKillVo = new SecondKillVo(secondKillId, foodId, shopId, foodMainImg, foodName, secondKillPrice, foodPrice, secondKillStock);
            key = "allSkPro:skpro_" + secondKillId;
            iGlobalCache.set(key, secondKillVo, 60 * 60 * 2);
        }
    }

    @Override
    public List<SecondKillVo> getAllPro() {
        String key = "allSkPro:skpro_*";
        if (iGlobalCache.getKeys(key).size() > 0) {
            Set<String> keys = iGlobalCache.getKeys(key);
            return iGlobalCache.getRedisTemplate().opsForValue().multiGet(keys);
        }
        return null;
    }

    //抢购时商品减库存
    @Override
    public SecondKillVo decreaseProductNumFromRedis(Integer secondKillId) {
        SecondKillVo secondKillPro = null;
        String key = "skpro_" + secondKillId;
        String uuid = UUID.randomUUID().toString().replace("-", "");
        boolean isLock = iGlobalCache.getRedisTemplate().opsForValue().setIfAbsent(key, uuid, 100, TimeUnit.SECONDS);
        if (isLock) {
            secondKillPro = (SecondKillVo) iGlobalCache.get("allSkPro:skpro_" + secondKillId);
            if (secondKillPro == null)
                return null;
            if (secondKillPro.getSecondKillStock() == 0)
                return null;
            else {
                secondKillPro.setSecondKillStock(secondKillPro.getSecondKillStock() - 1);
                iGlobalCache.set("allSkPro:skpro_" + secondKillId, secondKillPro, 60 * 60);
                //执行lua脚本，删除锁
//                Long execute = iGlobalCache.getRedisTemplate().execute(script,Arrays.asList(key),uuid);
                iGlobalCache.del(key);
                rabbitTemplate.convertAndSend("DecrStockExchange", "DecrStock", secondKillId);
                return secondKillPro;
            }
        } else {
            try {
                Thread.sleep(100);
                secondKillPro = (SecondKillVo) iGlobalCache.get("allSkPro:skpro_" + secondKillId);
                if (secondKillPro != null && secondKillPro.getSecondKillStock() > 0) {
                    decreaseProductNumFromRedis(secondKillId);
                    return secondKillPro;
                } else
                    return null;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    @Transactional
    @RabbitHandler
    @RabbitListener(queues = "DecrStockQueue")
    public void decreaseProductNumFromDB(Channel channel, Message message, Integer secondKillId) {
        //获取数据库中秒杀对象
        SecondKill secondKill = secondKillMapper.selectById(secondKillId);
        int rows = 0;
        //获取version字段
        try {
            Integer version = secondKill.getVersion();
            QueryWrapper<SecondKill> secondKillQueryWrapper = new QueryWrapper<>();
            secondKillQueryWrapper.eq("second_kill_id", secondKillId);
            secondKillQueryWrapper.eq("version", version);
            //更改库存
            secondKill.setSecondKillStock(secondKill.getSecondKillStock() - 1);
            version = version + 1;
            secondKill.setVersion(version);
            //持久化到数据库
            rows = secondKillMapper.update(secondKill, secondKillQueryWrapper);
            if (rows > 0) {
                log.info("修改数据库成功,修改的秒杀id={}", secondKillId);
            }else {
                log.info("修改数据库失败,准备重新修改");
                rabbitTemplate.convertAndSend("DecrStockExchange", "DecrStock", secondKillId);
            }
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        } catch (IOException e) {
            e.printStackTrace();
            try {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
