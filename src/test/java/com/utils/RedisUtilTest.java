package com.utils;


import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author lw
 * @date 2019-08-15
 */
public class RedisUtilTest {

    // 字符串操作
    @Test
    public void testStr() {
        Jedis jedis = RedisUtil.getJedis();
        jedis.set("id", "15"); // 只能是字符串
        String id = jedis.get("id");
        System.out.println(id);
        jedis.close();
    }

    // 操作 map
    @Test
    public void testMap() {
        Jedis jedis = RedisUtil.getJedis();
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "xinxin");
        map.put("age", "22");
        map.put("qq", "123456");
        jedis.hmset("user", map);
        List<String> rsmap = jedis.hmget("user", "name", "age", "qq");
        System.out.println(rsmap);
        jedis.hdel("user", "age");
        Iterator<String> iter = jedis.hkeys("user").iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            System.out.println(key + ":" + jedis.hmget("user", key));
        }
        jedis.close();
    }

    // 操作 list
    @Test
    public void testList() {
        Jedis jedis = RedisUtil.getJedis();
        jedis.del("java framework");
        System.out.println(jedis.lrange("java framework", 0, -1));
        jedis.lpush("java framework", "spring");
        jedis.lpush("java framework", "struts");
        jedis.lpush("java framework", "hibernate");
        System.out.println(jedis.lrange("java framework", 0, -1));
        jedis.del("java framework");
        jedis.rpush("java framework", "spring");
        jedis.rpush("java framework", "struts");
        jedis.rpush("java framework", "hibernate");
        System.out.println(jedis.lrange("java framework", 0, -1));
        jedis.close();
    }

    // 操作 set
    @Test
    public void testSet() {
        Jedis jedis = RedisUtil.getJedis();
        jedis.sadd("user1", "liuling");
        jedis.sadd("user1", "xinxin");
        jedis.sadd("user1", "ling");
        jedis.sadd("user1", "zhangxinxin");
        jedis.sadd("user1", "who");
        jedis.srem("user1", "who"); // 移除noname
        System.out.println(jedis.smembers("user1"));// 获取所有加入的value
        System.out.println(jedis.sismember("user1", "who"));// 判断 who
        System.out.println(jedis.srandmember("user1")); // 是否是user集合的元素
        System.out.println(jedis.scard("user1"));// 返回集合的元素个数
        jedis.close();
    }

    // jedis 排序
    @Test
    public void testOrder() {
        Jedis jedis = RedisUtil.getJedis();
        jedis.del("a");
        jedis.rpush("a", "1");
        jedis.lpush("a", "6");
        jedis.lpush("a", "3");
        jedis.lpush("a", "9");
        System.out.println(jedis.lrange("a", 0, -1));
        System.out.println(jedis.sort("a"));
        System.out.println(jedis.lrange("a", 0, -1));
        jedis.close();
    }
}