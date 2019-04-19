package com.minhe.seckill.service.impl;

import com.minhe.seckill.dao.StockMapper;
import com.minhe.seckill.dao.StockOrderMapper;
import com.minhe.seckill.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: SSM-SECOND-KILL
 * @description:
 * @author: MinheZ
 * @create: 2019-04-17 17:12
 **/

@Service
public class StockServiceImpl implements StockService {

    // 日志对象
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private StockOrderMapper orderMapper;

    private final String salt = "hd*&&^d4(98IGUyd34";

    /**
     * @Description: 根据 ID 查库存
     * @Param: [id]
     * @return: com.minhe.seckill.pojo.Stock
     * @Author: MinheZ
     * @Date: 2019/4/17
    **/
//    @Override
//    public Stock getStockById(Integer id) {
//        return stockMapper.queryById(id);
//    }
//
//    /**
//     * @Description: 根据 ID 获取商品剩余库存
//     * @Param: [id]
//     * @return: int
//     * @Author: MinheZ
//     * @Date: 2019/4/17
//    **/
//    @Override
//    public int getStockCount(Integer id) {
//        return stockMapper.queryById(id).getCount();
//    }
//
//    @Override
//    public Exposer exposerStockUrl(Integer id) {
//        Stock stock = stockMapper.queryById(id);
//        if (stock == null) {
//            return new Exposer(false, id);
//        }
//        Date startTime = stock.getStartTime();
//        Date endTime = stock.getEndTime();
//        Date nowTime = new Date();
//
//        // 判断当前时间是否在秒杀时间范围之内
//        if (startTime.getTime() > nowTime.getTime() || nowTime.getTime() > endTime.getTime()) {
//            return new Exposer(false, id, nowTime.getTime(), startTime.getTime(), endTime.getTime());
//        }
//
//        String md5 = getMD5(id);
//        return new Exposer(true, md5, id);
//    }
//
//    private String getMD5(Integer id) {
//        String base = id + "/" + salt;
//        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
//        return md5;
//    }



    /**
     * @Description: 根据 ID 更新库存
     * @Param: [stock]
     * @return: int
     * @Author: MinheZ
     * @Date: 2019/4/17
     **/
//    @Override
//    public int updateStockById(Stock stock) {
//
//        return 0;
//    }
}
