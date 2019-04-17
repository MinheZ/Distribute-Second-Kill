package com.minhe.seckill.service;

import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * @program: SSM-SECOND-KILL
 * @description: StockMapper.xml
 * @author: MinheZ
 * @create: 2019-04-17 10:13
 **/

public interface StockMapper {

    /**
     * @Description: 减库存，当有2个参数时，必须使用 Param 注解
     * @Param: [id, killTime]
     * @return: int
     * @Author: MinheZ
     * @Date: 2019/4/17
    **/
    int reduceCount(@Param("id") Integer id, @Param("killTime") Date killTime);

}
