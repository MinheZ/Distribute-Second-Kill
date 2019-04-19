package com.minhe.seckill.api;

/**
 * @program: SSM-SECOND-KILL
 * @description: 对 StockService 操作的 API
 * @author: MinheZ
 * @create: 2019-04-19 10:12
 **/

public interface StockService {

    /**
     * @Description: 获取当前库存
     * @Param: []
     * @return: java.lang.Integer
     * @Author: MinheZ
     * @Date: 2019/4/19
    **/
    Integer getCurrentCount() throws Exception;

}
