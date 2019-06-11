package cn.edu.neu.School_Jobs.service.impl;

import cn.edu.neu.School_Jobs.service.RedisServer;
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
}
