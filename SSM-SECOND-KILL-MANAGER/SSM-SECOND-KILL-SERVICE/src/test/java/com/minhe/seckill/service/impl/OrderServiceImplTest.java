package com.minhe.seckill.service.impl;

import com.minhe.seckill.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-service.xml", "classpath:spring/spring-dao.xml"})
public class OrderServiceImplTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OrderService order;

    private Integer id = 1;

    @Test
    public void createWrongOrder() {
        logger.info("result={}", order.createWrongOrder(id));
    }

    @Test
    public void createOrderByOptimisticLock() {
        logger.info("result={}", order.createOrderByOptimisticLock(id));
    }

    @Test
    public void createByOptimisticLockUseRedis() {
        logger.info("result={}", order.createByOptimisticLockUseRedis(2));
//        System.out.println(Integer.parseInt(null));
//        try {
//            System.out.println(Integer.parseInt(null));
//        } catch (Exception e) {
//            logger.error("Exception", e);
//        }
//        System.out.println(1);
    }
}