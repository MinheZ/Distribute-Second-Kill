package com.minhe.seckill.dao;

import com.minhe.seckill.api.pojo.StockOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class StockOrderMapperTest {

    @Autowired
    private StockOrderMapper orderMapper;
    private Integer id = 2;
    private String name = "火车票";
    private long userPhone = 18161225484L;

    @Test
    public void insertStockOrderTest() {
        int ret = orderMapper.insertStockOrder(id, name, userPhone);
        System.out.println(ret);
    }
    /**
     * @Description: 根据库存订单ID 查询出库存的对象
     * @Param: []
     * @return: void
     * @Author: MinheZ
     * @Date: 2019/4/17
    **/
    @Test
    public void queryByIdWithStockTest() {
        StockOrder stockOrder = orderMapper.queryByIdWithStock(id, userPhone);
        stockOrder.getStock().setName(stockOrder.getName());
        System.out.println(stockOrder + "\n" + stockOrder.getStock());
    }
}