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
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

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
            redisTemplate.opsForValue().set(key,secondKillVo,1,TimeUnit.HOURS);
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
                redisTemplate.opsForValue().set(key,secondKillVo,1,TimeUnit.HOURS);
            }
            return listVo;
        }
    }
}
