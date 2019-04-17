package com.minhe.seckill.exception;

/**
 * @program: SSM-SECOND-KILL
 * @description: 重复秒杀异常
 * @author: MinheZ
 * @create: 2019-04-17 19:51
 **/

public class RepetKillException extends StockException{

    public RepetKillException(String message) {
        super(message);
    }


    public RepetKillException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
