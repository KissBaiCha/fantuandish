package com.chixing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chixing.commons.IGlobalCache;
import com.chixing.entity.Food;
import com.chixing.entity.SecondKill;
import com.chixing.entity.vo.SecondKillVo;
import com.chixing.mapper.SecondKillMapper;
import com.chixing.service.IFoodService;
import com.chixing.service.ISecondKillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2022-09-30
 */
@Component
@Service
public class SecondKillServiceImpl  implements ISecondKillService {

    @Autowired
    private SecondKillMapper secondKillMapper;
    @Autowired
    private IFoodService foodService;
    @Autowired
    private IGlobalCache iGlobalCache;

    @Override
//    @Scheduled(cron = "0/20 * * * * ?")//20s
    @Scheduled(cron = "0 */60 * * * ?")//1h
    public List<SecondKillVo> getAllFromMysql() {
        String key = "allSkPro:skpro_*";
        Set keys = iGlobalCache.getRedisTemplate().keys(key);
        if (keys.size()>0)
            iGlobalCache.getRedisTemplate().delete(keys);
        QueryWrapper<SecondKill> secondKillQueryWrapper = new QueryWrapper<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        secondKillQueryWrapper.gt("second_kill_start_time", LocalDateTime.parse(LocalDate.now()+" 00:00:00",formatter));
//        secondKillQueryWrapper.lt("second_kill_end_time",LocalDateTime.parse(LocalDate.now().plusDays(1)+" 00:00:00",formatter));
        List<SecondKill> secondKills = secondKillMapper.selectList(secondKillQueryWrapper);
        List<SecondKillVo> listVo = new ArrayList<>();
        for (SecondKill secondKill : secondKills){
            Integer secondKillId = secondKill.getSecondKillId();
            BigDecimal secondKillPrice = secondKill.getSecondKillPrice();
            Integer foodId = secondKill.getFoodId();
            Food food = foodService.getById(foodId);
            String foodMainImg = food.getFoodMainImg();
            String foodName = food.getFoodName();
            BigDecimal foodPrice = food.getFoodPrice();
            Integer shopId = food.getShopId();
            Integer secondKillStock = secondKill.getSecondKillStock();
            SecondKillVo secondKillVo = new SecondKillVo(secondKillId,foodId,shopId,foodMainImg,foodName,secondKillPrice,foodPrice,secondKillStock);
            listVo.add(secondKillVo);
            key = "allSkPro:skpro_"+secondKillId;
            iGlobalCache.getRedisTemplate().opsForValue().set(key,secondKillVo,1,TimeUnit.HOURS);
        }
        return listVo;
    }

    @Override
    public List<SecondKillVo> getAllPro() {
        String key = "allSkPro:skpro_*";
        if (iGlobalCache.getRedisTemplate().keys(key).size()>0){
            Set keys =  iGlobalCache.getRedisTemplate().keys(key);
            return iGlobalCache.getRedisTemplate().opsForValue().multiGet(keys);
        }else{
            List<SecondKillVo> listVo = getAllFromMysql();
            for (SecondKillVo secondKillVo : listVo){
                key = "allSkPro:skpro_"+secondKillVo.getSecondKillId();
                iGlobalCache.getRedisTemplate().opsForValue().set(key,secondKillVo,1,TimeUnit.HOURS);
            }
            return listVo;
        }
    }

    //抢购时商品减库存
    @Override
    public SecondKillVo decreaseProductNumFromRedis(Integer secondKillId){
        SecondKillVo secondKillPro = null;
        String key = "skpro_"+secondKillId;
        String uuid = UUID.randomUUID().toString().replace("-","");

        boolean isLock = iGlobalCache.getRedisTemplate().opsForValue().setIfAbsent(key,uuid,100,TimeUnit.SECONDS);
        if (isLock){
            secondKillPro = (SecondKillVo) iGlobalCache.get("allSkPro:skpro_"+secondKillId);
            if (secondKillPro == null)
                return null;
            if (secondKillPro.getSecondKillStock()==0)
                return null;
            else {
                secondKillPro.setSecondKillStock(secondKillPro.getSecondKillStock()-1);
                iGlobalCache.set("allSkPro:skpro_"+secondKillId,secondKillPro,60*60);

                //执行lua脚本，删除锁
//                Long execute = iGlobalCache.getRedisTemplate().execute(script,Arrays.asList(key),uuid);
                iGlobalCache.del(key);
                return secondKillPro;
            }
        }else {
            try {
                Thread.sleep(100);
                secondKillPro = (SecondKillVo) iGlobalCache.get("allSkPro:skpro_"+secondKillId);
                if (secondKillPro!=null && secondKillPro.getSecondKillStock()>0){
                    decreaseProductNumFromRedis(secondKillId);
                    return secondKillPro;
                }else
                    return null;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
