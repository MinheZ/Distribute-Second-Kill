package com.minhe.seckill.service;

import com.minhe.seckill.api.pojo.Stock;
import com.minhe.seckill.dto.Exposer;


/**
 * @program: SSM-SECOND-KILL
 * @description: StockService
 * @author: MinheZ
 * @create: 2019-04-17 17:11
 **/

public interface StockService {

    Stock getStockById(Integer id);

    int getStockCount(Integer id);

    Exposer exposerStockUrl(Integer id);



    //int updateStockById(Stock stock);
}
