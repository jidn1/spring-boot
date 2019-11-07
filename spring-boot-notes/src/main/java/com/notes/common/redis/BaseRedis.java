package com.notes.common.redis;

import com.notes.common.util.SpringUtil;
import redis.clients.jedis.JedisPool;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/11/7 10:23
 * @Description:
 */
public class BaseRedis {

    public static final JedisPool JEDIS_POOL = (JedisPool) SpringUtil.getBean("jedisPool");
}
