package com.minhe.seckill.service.impl;

import com.minhe.seckill.service.StockService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-service.xml", "classpath:spring/spring-dao.xml"})
public class StockServiceImplTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StockService stockService;

    private Integer id = 2;

    @Test
    public void getStockCount() {
        logger.info("stockCount={}", stockService.getStockCount(id));
    }

    @Test
    public void getStockById() {
        logger.info("stock={}", stockService.getStockById(id));
    }

    @Test
    public void updateStockById() {
    }

    @Test
    public void getCurrentCount() {
    }
}