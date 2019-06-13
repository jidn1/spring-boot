package com.translate.common.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/6/10 11:49
 * @Description:
 */
@Configuration
public class JedisConfig {

    private Logger logger = LoggerFactory.getLogger(JedisConfig.class);


    @Value("${redis.host}")
    private String host;

    @Value("${redis.port}")
    private int port;

    @Value("${redis.password}")
    private String password;

    @Value("${redis.database}")
    private int database;

    @Value("${redis.timeout}")
    private int timeout;

    @Value("${redis.pool.max-active}")
    private int maxActive;

    @Value("${redis.pool.max-idle}")
    private int maxIdle;

    @Value("${redis.pool.min-idle}")
    private int minIdle;

    @Value("${redis.pool.max-wait}")
    private long maxWaitMillis;


    @Bean
    public JedisPool redisPoolFactory(){
        try {
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxIdle(maxIdle);
            jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
            jedisPoolConfig.setMaxTotal(maxActive);
            jedisPoolConfig.setMinIdle(minIdle);
            JedisPool jedisPool = new JedisPool(jedisPoolConfig,host,port,timeout,password,database);
            logger.info("============JedisPool注入成功！");
            logger.info("redis地址：" + host + ":" + port);
            return  jedisPool;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
