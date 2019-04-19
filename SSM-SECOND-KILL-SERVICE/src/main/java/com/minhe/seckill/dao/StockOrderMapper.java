package com.minhe.seckill.dao;

import com.minhe.seckill.api.pojo.StockOrder;
import org.apache.ibatis.annotations.Param;

/**
 * @program: SSM-SECOND-KILL
 * @description: StockOrderMapper
 * @author: MinheZ
 * @create: 2019-04-17 15:47
 **/

public interface StockOrderMapper {

    int insertStockOrder(@Param("id") Integer id, @Param("name") String name, @Param("userPhone") long userPhone);

    StockOrder queryByIdWithStock(@Param("id") Integer id, @Param("userPhone") long userPhone);
}
