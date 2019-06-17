package cn.edu.neu.School_Jobs.service.impl;

import cn.edu.neu.School_Jobs.service.RedisServer;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by fzb on 2019/6/11
 */
@Service
public class RedisServerImpl implements RedisServer {
    @Autowired
    JedisPool jedisPool;
    @Autowired
    Jedis jedis;
    @Override
    public String testFunction(String value) {
        return jedis.set(value,value);
    }

    @Override
    public Boolean set(String key, String value, int time) {
        jedis.set(key, value);
        jedis.expire(key, time);
        return true;
    }

    @Override
    public JSONObject get(String key) {
        return JSONObject.parseObject(jedis.get(key));
    }
}
