package com.minhe.seckill.controller;

import com.minhe.seckill.service.OrderService;
import com.minhe.seckill.service.StockService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-mvc.xml", "classpath:spring/spring-dubbo-consumer.xml"})
public class IndexControllerTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StockService stockService;

    @Autowired
    private OrderService orderService;

    private Integer sid = 1;

    @Test
    public void getStockCount() {
        logger.info("count={}", stockService.getStockCount(sid));
        //System.out.println(stockService.getStockCount(id));
    }

    @Test
    public void createWrongOrder() {
        logger.info("sid=[{}]", sid);
        int id = 0;
        try {
            id = orderService.createWrongOrder(sid);
        } catch (Exception e) {
            logger.info("Exception", e);
        }
        logger.info("id={}", id);
    }
}