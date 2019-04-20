package com.minhe.seckill.controller;

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

    private Integer id = 1;

    @Test
    public void getStockCount() {
        logger.info("count={}", stockService.getStockCount(id));
        //System.out.println(stockService.getStockCount(id));
    }
}