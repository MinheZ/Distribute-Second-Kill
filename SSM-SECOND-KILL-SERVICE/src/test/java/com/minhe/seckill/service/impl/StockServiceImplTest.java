package com.minhe.seckill.service.impl;

import com.minhe.seckill.api.pojo.Stock;
import com.minhe.seckill.dto.Exposer;
import com.minhe.seckill.service.StockService;
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
    private StockService stockService;

    private Integer id = 2;
    @Test
    public void getStockById() {
        Stock stock = stockService.getStockById(id);
        logger.info("stock={}", stock);
    }

    @Test
    public void getStockCount() {
        logger.info("count={}", stockService.getStockCount(id));
    }

    @Test
    public void exposerStockUrl() {
        Exposer exposer = stockService.exposerStockUrl(id);
        if (exposer.isExposer()) {
            logger.info("exposer={}", exposer);
            long userPhone = 18161225484L;
            String md5 = exposer.getMd5();
        }
    }
}