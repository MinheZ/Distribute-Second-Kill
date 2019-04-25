package com.minhe.seckill.service.impl;

import com.minhe.seckill.api.constant.RedisKeysConstant;
import com.minhe.seckill.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-service.xml", "classpath:spring/spring-dao.xml"})
public class OrderServiceImplTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OrderService order;

    @Autowired
    private RedisTemplate redisTemplate;

    private Integer sid = 1;

    @Test
    public void createWrongOrder() {
        logger.info("result={}", order.createWrongOrder(sid));
    }

    @Test
    public void createOrderByOptimisticLock() {
        logger.info("result={}", order.createOrderByOptimisticLock(sid));
    }

    @Test
    public void createByOptimisticLockUseRedis() {
        logger.info("result={}", order.createByOptimisticLockUseRedis(sid));
//        System.out.println(Integer.parseInt(null));
//        try {
//            System.out.println(Integer.parseInt(null));
//        } catch (Exception e) {
//            logger.error("Exception", e);
//        }
//        System.out.println(1);
    }

    @Test
    public void redisTemplateTest() {
        Map<String, String> map = new HashMap<>(4);
        map.put(RedisKeysConstant.STOCK_NAME + sid, "火车票");
        map.put(RedisKeysConstant.STOCK_COUNT + sid, "10");
        map.put(RedisKeysConstant.STOCK_SALE + sid, "0");
        map.put(RedisKeysConstant.STOCK_VERSION + sid, "0");
        redisTemplate.opsForHash().putAll(RedisKeysConstant.STOCK_ID + sid, map);
        logger.info("key={}", redisTemplate.opsForHash().hasKey(RedisKeysConstant.STOCK_ID + sid, RedisKeysConstant.STOCK_NAME + sid));
        //logger.info("count={}", redisTemplate.opsForHash().get(RedisKeysConstant.STOCK_ID + sid,
         //                                                   RedisKeysConstant.STOCK_COUNT + sid));
        redisTemplate.opsForHash().get(RedisKeysConstant.STOCK_ID + sid,
                RedisKeysConstant.STOCK_COUNT + sid);
//        Integer count = redisTemplate.opsForHash().get(RedisKeysConstant.STOCK_ID + sid,
//                RedisKeysConstant.STOCK_COUNT + sid);
        System.out.println(redisTemplate.opsForHash().get(RedisKeysConstant.STOCK_ID + sid,
                RedisKeysConstant.STOCK_COUNT + sid));
    }

    @Test
    public void redisTemplateDel() {
//        redisTemplate.opsForHash().delete(RedisKeysConstant.STOCK_ID + sid,
//                                    RedisKeysConstant.STOCK_NAME + sid,
//                                             RedisKeysConstant.STOCK_COUNT,
//                                             RedisKeysConstant.STOCK_SALE + sid,
//                                             RedisKeysConstant.STOCK_VERSION + sid);
        // 删除所有
        redisTemplate.delete(RedisKeysConstant.STOCK_ID + sid);
    }

    @Test
    public void redisTemplateIncrement() {
        redisTemplate.opsForHash().increment(RedisKeysConstant.STOCK_ID + sid,
                RedisKeysConstant.STOCK_SALE + sid, 1);
        logger.info("sale={}", redisTemplate.opsForHash().get(RedisKeysConstant.STOCK_ID + sid,
                RedisKeysConstant.STOCK_SALE + sid));
    }
}