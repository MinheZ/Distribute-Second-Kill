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

import java.util.HashMap;
import java.util.Map;

/**
 * @program: SSM-SECOND-KILL
 * @description: 订单服务实现类
 * @author: MinheZ
 * @create: 2019-04-19 16:00
 **/
@Transactional(rollbackFor = Exception.class)
@Service
public class OrderServiceImpl implements OrderService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StockService stockService;

    @Autowired
    private StockOrderMapper orderMapper;

    @Autowired
    private RedisTemplate<String, String> redisTe;

//    private boolean flag = true;


    @Override
    public int createWrongOrder(Integer sid) throws StockException {
        // 先检验库存
        Stock stock = checkStock(sid);
        // 库存足够，则扣库存
        saleStock(stock);
        // 创建订单
        return createOrder(stock);
    }


    @Override
    public int createOrderByOptimisticLock(Integer sid) throws StockException {
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
        int i = stockService.updateStockByOptimisticLock(stock);
        if (i == 0) {
            throw new RuntimeException("并发更新库存失败！");
        }
        try {
            redisTe.opsForValue().increment(RedisKeysConstant.STOCK_SALE + stock.getId(), 1);
            redisTe.opsForValue().increment(RedisKeysConstant.STOCK_VERSION + stock.getId(), 1);
        } catch (Exception e) {
            logger.info("key increment Exception", e);
        }

//        Integer sid = stock.getId();
//        try {
//            // 自增
//            redisTe.opsForHash().increment(RedisKeysConstant.STOCK_ID + sid,
//                    RedisKeysConstant.STOCK_SALE + sid, 1);
//            redisTe.opsForHash().increment(RedisKeysConstant.STOCK_ID + sid,
//                    RedisKeysConstant.STOCK_VERSION + sid, 1);
//        } catch (Exception e) {
//            // 如果发生自增异常，删缓存
//            redisTe.delete(RedisKeysConstant.STOCK_ID + sid);
//            logger.error("Exception", e);
//        }
    }

    /**
     * @Description: 检查库存，这里应该先访问缓存，未命中再访问数据库，重新写缓存
     * @Param: [sid]
     * @return: com.minhe.seckill.pojo.Stock
     * @Author: MinheZ
     * @Date: 2019/4/23
     **/
    private Stock checkStockByRedis(Integer sid) {
        /*
         * 这里会出现好几个 NumberFormat Exception 异常，分析的结果是好几个线程并发得进入了try{}里面，
         * 而此时缓存还未更新。这里应该做缓存预热。
         * */
//        try {
        // 这里用 HASH 更好，主键为id， subKey 为字段
        /*
         *  判断 hash 中 hash-key 中的 sub-key1 是否存在，即是否已经在缓存中，理应判断所有的 sub-key，为了减少判断次数
         *  只查询 name 字段，在插入过程中保证所有 sub-key 的完整性就可以
         * */
//            Boolean hasKey = redisTe.opsForHash().hasKey(RedisKeysConstant.STOCK_ID + sid, RedisKeysConstant.STOCK_NAME + sid);
//            if (hasKey) {
//                Integer count = Integer.parseInt((String) redisTe.opsForHash().get(RedisKeysConstant.STOCK_ID + sid,
//                        RedisKeysConstant.STOCK_COUNT + sid));
//                Integer sale = Integer.parseInt((String) redisTe.opsForHash().get(RedisKeysConstant.STOCK_ID + sid,
//                        RedisKeysConstant.STOCK_SALE + sid));
//                String name = (String) redisTe.opsForHash().get(RedisKeysConstant.STOCK_ID + sid,
//                        RedisKeysConstant.STOCK_NAME + sid);
//                // 库存数量等于售出数量，卖完了
//                if (count.equals(sale)) {
//                    throw new SoldOutException("sold out!");
//                }
//                Integer version = (Integer) redisTe.opsForHash().get(RedisKeysConstant.STOCK_ID + sid,
//                        RedisKeysConstant.STOCK_VERSION + sid);
//
//                Stock stock = new Stock();
//                stock.setId(sid);
//                stock.setName(name);
//                stock.setCount(count);
//                stock.setSale(sale);
//                stock.setVersion(version);
//                return stock;
//            }
//        } catch (Exception e) {
//            logger.error("Exception", e);
//        }
//        // 如果缓存未命中， 查数据库
//        Stock stock = checkStock(sid);
//        if (stock != null) {
//            addAllToRedis(stock);
//        }
//        return stock;
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
            logger.error("NumberFormat Exception" + System.currentTimeMillis(), e);
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
                logger.error("No such item exception!", e);
            }
        }
        return stock;
    }

    /**
     * @Description: 所有字段加进缓存
     * @Param: [stock]
     * @return: void
     * @Author: MinheZ
     * @Date: 2019/4/25
     **/
    private void addAllToRedis(Stock stock) {
        Map<String, String> map = new HashMap<>(4);
        map.put(RedisKeysConstant.STOCK_NAME + stock.getId(), stock.getName());
        map.put(RedisKeysConstant.STOCK_COUNT + stock.getId(), String.valueOf(stock.getCount()));
        map.put(RedisKeysConstant.STOCK_SALE + stock.getId(), String.valueOf(stock.getSale()));
        map.put(RedisKeysConstant.STOCK_VERSION + stock.getId(), String.valueOf(stock.getVersion()));
        redisTe.opsForHash().putAll(RedisKeysConstant.STOCK_ID + stock.getId(), map);
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
        Stock stock = stockService.getStockById(id);
        if (stock.getSale().equals(stock.getCount())) {
            throw new SoldOutException("sold out!");
        }
        return stock;
    }
}
