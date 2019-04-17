package com.minhe.seckill.exception;

/**
 * @program: SSM-SECOND-KILL
 * @description: 秒杀关闭异常
 * @author: MinheZ
 * @create: 2019-04-17 19:54
 **/

public class SeckillCloseException extends StockException {
    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
