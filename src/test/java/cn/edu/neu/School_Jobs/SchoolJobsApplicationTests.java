package cn.edu.neu.School_Jobs;

import cn.edu.neu.School_Jobs.service.UserService;
import cn.edu.neu.School_Jobs.util.Encryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SchoolJobsApplicationTests {
    @Autowired
    private UserService userService;
    @Test
    public void contextLoads() {
        System.out.println(userService.selectAllByPhone("18689952277"));
    }

    @Test
    public void encryption() {
    }

}
