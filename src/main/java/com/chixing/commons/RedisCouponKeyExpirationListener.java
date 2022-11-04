package com.chixing.commons;

import com.chixing.entity.MyCoupon;
import com.chixing.mapper.MyCouponMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

/**
 * 监听 Redis key 过期事件
 * @author Xu Zhang
 * @date 2022/11/4
 */
@Slf4j
@Component
public class RedisCouponKeyExpirationListener extends KeyExpirationEventMessageListener {
    @Autowired
    private MyCouponMapper myCouponMapper;
    public RedisCouponKeyExpirationListener(RedisMessageListenerContainer redisMessageListenerContainer) {
        super(redisMessageListenerContainer);
    }

    /**
     * 针对 redis 数据失效事件，进行数据处理
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        // 拿到key
        log.info("监听Redis key过期，key：{}，channel：{}", message.toString(), new String(pattern));
        String key = message.toString();
        if(key.startsWith("myCoupon")){
            key = key.substring(9);
            log.info(key);
            MyCoupon myCoupon = myCouponMapper.selectById(Integer.valueOf(key));
            myCoupon.setMyCouponStatus(0);
            myCouponMapper.updateById(myCoupon);
        }
    }
}
