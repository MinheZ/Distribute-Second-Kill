package com.minhe.seckill.api.utils;

import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

/**
 * @program: SSM-SECOND-KILL
 * @description: 序列化
 * @author: MinheZ
 * @create: 2019-04-19 10:32
 **/
//implements Serializer
public class JasonSerializer<T> implements Serializer<T> {
    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public byte[] serialize(String s, T t) {
        return new byte[0];
    }

    @Override
    public void close() {

    }
}
