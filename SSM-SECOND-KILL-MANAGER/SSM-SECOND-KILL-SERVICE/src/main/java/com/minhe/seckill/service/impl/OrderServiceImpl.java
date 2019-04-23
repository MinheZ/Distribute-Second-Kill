package com.minhe.seckill.service.impl;

import com.minhe.seckill.api.constant.RedisKeysConstant;
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
import org.springframework.data.redis.core.RedisTemplate;
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

    @Autowired
    private RedisTemplate<String, String> redisTe;

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

    @Transactional
    @Override
    public int createByOptimisticLockUseRedis(Integer sid) {
        Stock stock = checkStockByRedis(sid);
        if (stock == null)
            return 0;
        saleStockByOptimisticLockUseRedis(stock);
        return createOrder(stock);
    }

    private void saleStockByOptimisticLockUseRedis(Stock stock) {
        int count = stockService.updateStockByOptimisticLock(stock);
        if (count == 0) {
            throw new RuntimeException("并发更新库存失败！");
        }
        // 自增
        redisTe.opsForValue().increment(RedisKeysConstant.STOCK_SALE + stock.getId(), 1);
        redisTe.opsForValue().increment(RedisKeysConstant.STOCK_VERSION + stock.getId(), 1);
    }

    /**
     * @Description: 检查库存，这里应该先访问缓存，未命中再访问数据库，重新写缓存
     * @Param: [sid]
     * @return: com.minhe.seckill.pojo.Stock
     * @Author: MinheZ
     * @Date: 2019/4/23
    **/
    private Stock checkStockByRedis(Integer sid) {
        try {
            Integer count = Integer.parseInt(redisTe.opsForValue().get(RedisKeysConstant.STOCK_COUNT + sid));
            Integer sale = Integer.parseInt(redisTe.opsForValue().get(RedisKeysConstant.STOCK_SALE + sid));
            String name = redisTe.opsForValue().get(RedisKeysConstant.STOCK_NAME + sid);
            if (count.equals(sale)) {
                throw new SoldOutException("sold out!");
            }
            Integer version = Integer.parseInt(redisTe.opsForValue().get(RedisKeysConstant.STOCK_VERSION + sid));
            Stock stock = new Stock();
            stock.setId(sid);
            stock.setName(name);
            stock.setCount(count);
            stock.setSale(sale);
            stock.setVersion(version);
            return stock;
        } catch (Exception e) {
            logger.error("Exception", e);
        }
        // 查数据库
        Stock stock = checkStock(sid);
        if (stock != null) {    // 向缓存写数据
            try {
                redisTe.opsForValue().set(RedisKeysConstant.STOCK_NAME + stock.getId(), stock.getName());
                redisTe.opsForValue().set(RedisKeysConstant.STOCK_COUNT + stock.getId(), String.valueOf(stock.getCount()));
                redisTe.opsForValue().set(RedisKeysConstant.STOCK_SALE + stock.getId(), String.valueOf(stock.getSale()));
                redisTe.opsForValue().set(RedisKeysConstant.STOCK_VERSION + stock.getId(), String.valueOf(stock.getVersion()));
            } catch (Exception e) {
                logger.error("Exception", e);
            }
        }
        return stock;
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
