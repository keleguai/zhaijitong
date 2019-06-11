package cn.edu.neu.School_Jobs;

import cn.edu.neu.School_Jobs.service.UserService;
import cn.edu.neu.School_Jobs.util.Encryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.Date;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class SchoolJobsApplicationTests {
    @Autowired
    private UserService userService;
    @Test
    public void contextLoads() {
        Jedis jedis = new Jedis("localhost");
        System.out.println(jedis.getClient().getPort());
        System.out.println("连接本地的Redis服务器成功");
        //查看服务是否运行
        System.out.println("服务正在运行：" + jedis.ping());

//        System.out.println(userService.selectAllByPhone("18689952277"));
    }

    @Test
    public void encryption() {
    }

}
