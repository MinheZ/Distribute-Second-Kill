package com.minhe.seckill.service.impl;

import com.minhe.seckill.dao.StockMapper;
import com.minhe.seckill.dao.StockOrderMapper;
import com.minhe.seckill.pojo.Stock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class StockServiceImplTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private StockOrderMapper orderMapper;

    private Integer id = 2;
    @Test
    public void getStockById() {
        Stock stock = stockMapper.queryById(id);
        logger.info("stock={}", stock);
    }

    @Test
    public void updateStockById() {
    }

    @Test
    public void getStockCount() {
        logger.info("count={}", stockMapper.queryById(id).getCount());
    }
}