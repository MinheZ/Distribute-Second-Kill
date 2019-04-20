package com.minhe.seckill.service;

import com.minhe.seckill.pojo.Stock;

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
    Integer getCurrentCount(Integer id) throws Exception;

    /**
     * @Description: 根据 ID 获取库存对象
     * @Param: [id]
     * @return: com.minhe.seckill.pojo.Stock
     * @Author: MinheZ
     * @Date: 2019/4/19
     **/
    Stock getStockById(Integer id);

    /**
     * @Description:  根据 ID 获取库存数量
     * @Param: [id]
     * @return: int
     * @Author: MinheZ
     * @Date: 2019/4/19
    **/
    int getStockCount(Integer id);

    /**
     * @Description: 根据库存进行更新
     * @Param: [stock]
     * @return: int
     * @Author: MinheZ
     * @Date: 2019/4/19
    **/
    int updateStockById(Stock stock);

    /**
     * @Description: 乐观锁更新库存，在数据库中定义一个版本字段，每次修改版本号+1
     * @Param: [stock]
     * @return: int
     * @Author: MinheZ
     * @Date: 2019/4/20
    **/
    int updateStockByOptimisticLock(Stock stock);
}
