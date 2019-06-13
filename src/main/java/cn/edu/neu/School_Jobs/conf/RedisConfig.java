package cn.edu.neu.School_Jobs.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Component
@PropertySource("classpath:application.properties")
public class RedisConfig {
    private Logger log = LoggerFactory.getLogger(RedisConfig.class);

    @Value("${redis.host}")
    private String host;

    @Value("${redis.port}")
    private int port;

    @Value("${redis.password}")
    private String password;


    @Bean
    public JedisPoolConfig getRedisConfig() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        return poolConfig;
    }




    @Bean
    public JedisPool getJedisPool() {
        JedisPoolConfig config = getRedisConfig();
        JedisPool pool = new JedisPool(config,host,port,2000,password);
        log.info("init JredisPool ...");
        return pool;
    }
    @Bean
    public Jedis getJedis(){
        Jedis jedis = new Jedis(host);
        log.info("init JredisPool ...");
        return jedis;
    }

}
