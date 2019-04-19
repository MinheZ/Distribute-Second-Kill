package com.minhe.seckill.exception;

/**
 * @program: SSM-SECOND-KILL
 * @description:
 * @author: MinheZ
 * @create: 2019-04-17 19:49
 **/

public class StockException extends RuntimeException {

    public StockException(String message) {
        super(message);
    }

    public StockException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
