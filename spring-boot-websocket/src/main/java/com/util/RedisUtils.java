package com.util;

import redis.clients.jedis.JedisPool;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/7/30 19:45
 * @Description:
 */
public class RedisUtils {

    public static final JedisPool JEDIS_POOL = (JedisPool) SpringUtil.getBean("jedisPool");
}
