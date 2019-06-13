package cn.edu.neu.School_Jobs;

import cn.edu.neu.School_Jobs.service.RedisServer;
import cn.edu.neu.School_Jobs.service.ScoreService;
import cn.edu.neu.School_Jobs.service.UserService;
import cn.edu.neu.School_Jobs.util.Encryptor;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SchoolJobsApplicationTests {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisServer redisServer;
    @Autowired
    private ScoreService scoreService;
    @Test
    public void contextLoads() {
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
    }

    @Test
    public void encryption() {
    }

}
