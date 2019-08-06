package com.loveseriously.crawler.core.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis 连接池帮助类
 *
 * @author lw
 * @date 2019-08-04
 */
public class RedisUtil {
    // Redis 服务器 IP
    private static String address = "127.0.0.1";

    // Redis 的端口号
    private static int port = 6379;

    // 访问密码
    private static String password = "920619";

    // 连接 redis 等待时间
    private static int timeOut = 10000;

    // 可用连接实例的最大数目，默认值为8;
    // 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例, 则此时pool的状态为exhausted(耗尽)
    private static int maxTotal = 1024;

    // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例, 默认值也是8
    private static int maxIdle = 200;

    // 等待可用连接的最大时间，单位毫秒, 默认值为-1，表示永不超时。 如果超过等待时间, 则直接抛出JedisConnectionException
    private static int maxWait = 10000;

    // 在borrow一个jedis实例时, 是否提前进行validate操作; 如果为true, 则得到的jedis实例均是可用的
    private static boolean testOnBorrow = true;

    // 连接池
    private static JedisPool jedisPool = null;

    // init
    static {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(maxTotal);
            config.setMaxIdle(maxIdle);
            config.setMaxWaitMillis(maxWait);
            config.setTestOnBorrow(testOnBorrow);
            //jedisPool = new JedisPool(config, address, port, timeOut, password);
            jedisPool = new JedisPool(config, address, port, timeOut);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // 获取 Jedis 实例
    public static Jedis getJedis() {
        if (jedisPool != null) {
            return jedisPool.getResource();
        }
        return null;
    }
}
