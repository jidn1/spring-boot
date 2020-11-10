package com.notes.common.redis;

import com.notes.common.util.SpringUtil;
import redis.clients.jedis.JedisPool;

public class BaseRedis {
    public static final JedisPool JEDIS_POOL = (JedisPool) SpringUtil.getBean("jedisPool");
}
