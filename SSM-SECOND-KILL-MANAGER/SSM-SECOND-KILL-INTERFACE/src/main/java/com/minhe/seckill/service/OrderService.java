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
    int createWrongOrder(Integer id) throws StockException;

}
