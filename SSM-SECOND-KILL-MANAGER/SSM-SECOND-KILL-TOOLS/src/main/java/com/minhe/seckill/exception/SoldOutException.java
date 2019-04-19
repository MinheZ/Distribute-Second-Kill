package com.minhe.seckill.exception;

/**
 * @program: SSM-SECOND-KILL
 * @description: 库存不足异常
 * @author: MinheZ
 * @create: 2019-04-19 16:34
 **/

public class SoldOutException extends StockException{
    public SoldOutException(String message) {
        super(message);
    }

    public SoldOutException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
