package com.springbootvideo.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;

/**
 * @Copyright © 正经吉
 * @Author: Jidn
 * @Date: 2018/12/28 16:45
 * @Description: RedisServiceImpl
 */
@Service("redisService")
public class RedisService {

    @Autowired
    private JedisPool jedisPool;

    private Jedis getResource() {
        return this.jedisPool.getResource();
    }

    public String get(String key) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        } finally {
            jedis.close();
        }
    }

      
    public String save(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (!StringUtils.isEmpty(value)) {
                return jedis.set(key, value);
            }
        } catch (Exception e) {
            jedis.close();
            e.printStackTrace();
            return "";
        } finally {
            jedis.close();
        }
        return null;
    }

     
    public void save(String key, String value, int time) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (!StringUtils.isEmpty(value)) {
                jedis.set(key, value);
                jedis.expire(key, time);
            }
        } catch (Exception e) {
            jedis.close();
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }

    public Long delete(String key) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.del(key);
        } catch (Exception e) {
            jedis.close();
            e.printStackTrace();
            return Long.valueOf(0L);
        } finally {
            jedis.close();
        }
    }

    public void setTime(String paramString, int paramInt) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.expire(paramString, paramInt);
        } catch (Exception e) {
            jedis.close();
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }

    public void saveMap(String paramString, Map<String, String> paramMap) {
        Jedis jedis = null;
        try {
            if (null != null && paramMap.size() > 0) {
                jedis = getResource();
                jedis.hmset(paramString, paramMap);
            }
        } catch (Exception e) {

        } finally {
            jedis.close();
        }
    }

    public void hset(String paramString1, String paramString2, String paramString3) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (!StringUtils.isEmpty(paramString2) && !StringUtils.isEmpty(paramString3)) {
                jedis.hset(paramString1, paramString2, paramString3);
            }
        } catch (Exception e) {
        } finally {
            jedis.close();
        }
    }
    public String hget(String paramString1, String paramString2) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            String ss = jedis.hget(paramString1, paramString2);
            return ss;
        } catch (Exception e) {
        } finally {
            jedis.close();
        }
        return null;
    }

    public String hdel(String paramString1, String paramString2) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            String ss = jedis.hget(paramString1, paramString2);
            return ss;
        } catch (Exception e) {
        } finally {
            jedis.close();
        }
        return null;
    }

    public Long getKeyTime(String paramString) {
        try {
            Long l = getResource().ttl(paramString);
            Long localLong1 = l;
            return localLong1;
        } catch (Exception e) {
            e = e;
        } finally {
        }
        return null;
    }

    public Map<String, String> hgetAll(String paramString) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            Map<String, String> map = jedis.hgetAll(paramString);
            return map;
        } catch (Exception e) {
        } finally {
            jedis.close();
        }
        return null;
    }

    public void returnResource(JedisPool pool, Jedis jedis) {
        if (jedis != null) {
            pool.returnResource(jedis);
        }
    }
}
