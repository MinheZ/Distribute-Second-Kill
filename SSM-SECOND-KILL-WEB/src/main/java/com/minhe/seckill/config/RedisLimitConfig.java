package com.minhe.seckill.config;

import com.crossoverjie.distributed.constant.RedisToolsConstant;
import com.crossoverjie.distributed.limit.RedisLimit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * @program: SSM-SECOND-KILL
 * @description: Redis 限流
 * @author: MinheZ
 * @create: 2019-04-22 20:05
 **/

@Configuration
public class RedisLimitConfig {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("10")
    private int limit;  // 每 2s 放 10个请求

    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;

    @Bean
    public RedisLimit build() {
        System.out.println(limit);
        RedisLimit redisLimit = new RedisLimit.Builder(jedisConnectionFactory,
                RedisToolsConstant.SINGLE).limit(limit).build();
        System.out.println(limit);
        return redisLimit;
    }

}
