package com.minhe.seckill.api.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

/**
 * @program: SSM-SECOND-KILL
 * @description: 序列化
 * @author: MinheZ
 * @create: 2019-04-19 10:32
 **/

public class JasonSerializer<T> implements Serializer<T> {

    public JasonSerializer() {
    }

    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public byte[] serialize(String s, T t) {
        try {
            byte[] result = null;
            if (t != null) {
                result = JSONObject.toJSONBytes(t, SerializerFeature.UseISO8601DateFormat,
                        SerializerFeature.WriteMapNullValue, SerializerFeature.WriteClassName);
            }
            return result;
        } catch (Exception e) {
            throw new SerializationException("Can't serialize data [" + t + "] for topic [" + s + "]", e);
        }
    }

    @Override
    public void close() {

    }
}
