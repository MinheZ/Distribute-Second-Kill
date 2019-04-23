package com.minhe.seckill.service;

import com.minhe.seckill.exception.StockException;

/**
 * @program: SSM-SECOND-KILL
 * @description: 对订单的处理API
 * @author: MinheZ
 * @create: 2019-04-19 10:14
 **/

public interface OrderService {

    /**
     * @Description: 下订单
     * @Param: [id]
     * @return: int
     * @Author: MinheZ
     * @Date: 2019/4/19
    **/
    int createWrongOrder(Integer sid) throws StockException;

    /**
     * @Description: 使用乐观锁跟新数据库
     * @Param: [sid]
     * @return: int
     * @Author: MinheZ
     * @Date: 2019/4/20
    **/
    int createOrderByOptimisticLock(Integer sid) throws StockException;

    /**
     * @Description: Redis限流 + 乐观锁 + 缓存
     * @Param: [sid]
     * @return: void
     * @Author: MinheZ
     * @Date: 2019/4/22
    **/
    int createByOptimisticLockUseRedis(Integer sid);

}
