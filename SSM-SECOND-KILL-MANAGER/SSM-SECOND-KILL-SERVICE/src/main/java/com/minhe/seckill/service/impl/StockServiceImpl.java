package com.minhe.seckill.service.impl;


import com.minhe.seckill.dao.StockMapper;
import com.minhe.seckill.pojo.Stock;
import com.minhe.seckill.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: SSM-SECOND-KILL
 * @description: 这个实现类是真正实现数据落地的操作
 * @author: MinheZ
 * @create: 2019-04-17 17:12
 **/

@Service
public class StockServiceImpl implements StockService {

    // 日志对象
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StockMapper stockMapper;

    @Override
    public Integer getCurrentCount(Integer id) throws Exception {

        return null;
    }

    @Override
    public int getStockCount(Integer id) {
        return stockMapper.selectByPrimaryKey(id).getCount();
    }

    @Override
    public Stock getStockById(Integer id) {
        return stockMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateStockById(Stock stock) {
        // 会对字段进行判断再更新(如果为Null就忽略更新)，如果只想更新某一字段，可以用这个方法
        return stockMapper.updateByPrimaryKeySelective(stock);
    }
}
