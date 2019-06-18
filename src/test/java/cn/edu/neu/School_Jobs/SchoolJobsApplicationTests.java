package cn.edu.neu.School_Jobs;

import cn.edu.neu.School_Jobs.service.RedisServer;
import cn.edu.neu.School_Jobs.service.ScoreService;
import cn.edu.neu.School_Jobs.service.UserService;
import cn.edu.neu.School_Jobs.util.Encryptor;
import cn.edu.neu.School_Jobs.util.Jwt;
import com.alibaba.fastjson.JSONObject;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.Payload;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class SchoolJobsApplicationTests {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisServer redisServer;
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private Jedis jedis;
    @Test
    public void contextLoads() throws ParseException {
//        Jedis jedis = new Jedis("localhost");
//        System.out.println(jedis.getClient().getPort());
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("111","123");
//        jedis.set("111",jsonObject.toString());
//        JSONObject jsonObject1=JSONObject.parseObject(jedis.get("111"));
//        System.out.println("连接本地的Redis服务器成功");
//        System.out.println(jsonObject1);
//        //查看服务是否运行
//        System.out.println("服务正在运行：" + jedis.ping());
//        System.out.println(redisServer.testFunction("111"));
//System.out.println(scoreService.findByGrade("true"));
//        System.out.println(userService.selectAllByPhone("18689952277"));
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("count", 1);
//        jsonObject.put("keke", 2);
//        redisServer.set("test", jsonObject.toString(), 300);
//
//        System.out.println(redisServer.get("aaa") == null);
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHBpcnlUaW1lIjoxNTYwNzc0MjI3NzIwLCJzdGFydFRpbWUiOjE1NjA3NzA2Mjc3MjAsInR5cGUiOjAsInVzZXJJZCI6MX0.NXyGUsU-tD39nAlMukRKoQ1msEnatRheheJX8-afEdA";
        Map<String, Object> result = Jwt.validToken(token);
        JWSObject jwsObject = JWSObject.parse(token);
        Payload payload = jwsObject.getPayload();
        System.out.println(payload.toJSONObject().getAsString("userId"));
    }

    @Test
    public void encryption() {
    }

}
