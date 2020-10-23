package com.notes.study.redisCluster;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author jidn
 * @Date 2020-10-20
 * @Description
 */
@Component
@Configuration
@EnableCaching
public class Redis2Config {

    @Value("${spring.redis2.cluster.nodes:192.168.200.87:7001,192.168.200.87:7002,192.168.200.87:7003}")
    private String nodes;
    @Value("${spring.redis2.cluster.max-redirects:3}")
    private Integer maxRedirects;
    @Value("${spring.redis2.password:}")
    private String password;
    @Value("${spring.redis2.jedis.pool.max-active:10}")
    private Integer maxActive;
    @Value("${spring.redis2.jedis.pool.max-idle:10}")
    private Integer maxIdle;
    @Value("${spring.redis2.jedis.pool.max-wait:5000}")
    private Long maxWait;
    @Value("${spring.redis2.jedis.pool.min-idle:10}")
    private Integer minIdle;


    @Bean(name = "redisTemplate2")
    public StringRedis2Template redisTemplate2(RedisConnectionFactory redisConnectionFactory) {

        StringRedis2Template redisTemplate = new StringRedis2Template(redisConnectionFactory);

        return redisTemplate;
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory(JedisPoolConfig jedisPool,
                                                         RedisClusterConfiguration jedisConfig) {
        JedisConnectionFactory factory = new JedisConnectionFactory(jedisConfig, jedisPool);
        factory.afterPropertiesSet();
        return factory;
    }


    @Bean
    public JedisPoolConfig jedisPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWait);
        jedisPoolConfig.setMaxTotal(maxActive);
        jedisPoolConfig.setMinIdle(minIdle);
        return jedisPoolConfig;
    }

    @Bean
    public RedisClusterConfiguration jedisConfig() {
        RedisClusterConfiguration config = new RedisClusterConfiguration();
        String[] sub = nodes.split(",");
        List<RedisNode> nodeList = new ArrayList<>(sub.length);
        String[] tmp;
        for (String s : sub) {
            tmp = s.split(":");
            // fixme 先不考虑异常配置的case
            nodeList.add(new RedisNode(tmp[0], Integer.valueOf(tmp[1])));
        }
        config.setClusterNodes(nodeList);
        config.setMaxRedirects(maxRedirects);
        return config;
    }


}
