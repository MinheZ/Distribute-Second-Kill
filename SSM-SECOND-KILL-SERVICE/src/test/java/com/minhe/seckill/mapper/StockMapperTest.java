package com.minhe.seckill.mapper;


import com.minhe.seckill.dao.StockMapper;
import com.minhe.seckill.pojo.Stock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class StockMapperTest {

    @Autowired
    private StockMapper stockMapper;

    private Integer id = 2;

    @Test
    public void queryByIdTest() {
        Stock stock = stockMapper.queryById(id);
        System.out.println(stock);
    }

    @Test
    public void reduceCountTest() {
        Date killTime = new Date();
        int ret = stockMapper.reduceCount(id, killTime);
        System.out.println(ret);
    }

}