package com.minhe.seckill.service.impl;

import com.minhe.seckill.dao.StockOrderMapper;
import com.minhe.seckill.exception.SoldOutException;
import com.minhe.seckill.exception.StockException;
import com.minhe.seckill.pojo.Stock;
import com.minhe.seckill.pojo.StockOrder;
import com.minhe.seckill.service.OrderService;
import com.minhe.seckill.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: SSM-SECOND-KILL
 * @description: 订单服务实现类
 * @author: MinheZ
 * @create: 2019-04-19 16:00
 **/
@Service
public class OrderServiceImpl implements OrderService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StockService stockService;

    @Autowired
    private StockOrderMapper orderMapper;

    @Transactional
    @Override
    public int createWrongOrder(Integer sid) throws StockException {
        // 先检验库存
        Stock stock = checkStock(sid);
        // 库存足够，则扣库存
        saleStock(stock);
        // 创建订单
        return createOrder(stock);
    }
    @Transactional
    @Override
    public int createOrderByOptimisticLock(Integer sid) throws StockException{
        // 先检验库存
        Stock stock = checkStock(sid);
        saleStockByOptimisticLock(stock);
        return createOrder(stock);
    }

    private void saleStockByOptimisticLock(Stock stock) {
        int i = stockService.updateStockByOptimisticLock(stock);
        if (i == 0)
            throw new RuntimeException("并发更新数据失败！");
    }


    private int createOrder(Stock stock) {
        StockOrder order = new StockOrder();
        order.setSid(stock.getId());
        order.setName(stock.getName());
        return orderMapper.insertSelective(order);
    }

    /**
     * @Description: 销量 + 1
     * @Param: [stock]
     * @return: void
     * @Author: MinheZ
     * @Date: 2019/4/19
    **/
    private void saleStock(Stock stock) {
        stock.setSale(stock.getSale() + 1);
        stockService.updateStockById(stock);
    }

    /**
     * @Description: 检验库存是否充足
     * @Param: [id]
     * @return: com.minhe.seckill.pojo.Stock
     * @Author: MinheZ
     * @Date: 2019/4/19
    **/
    private Stock checkStock(Integer id) {
        Stock stock =  stockService.getStockById(id);
        if (stock.getSale().equals(stock.getCount())) {
            throw new SoldOutException("sold out!");
        }
        return stock;
    }
}
