package cn.edu.neu.School_Jobs;

import cn.edu.neu.School_Jobs.util.Encryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class SchoolJobsApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void encryption() {
        System.out.println("BEFORE = 123456, LLG\n After = " + Encryptor.encrypt("123456", "LLG"));
        System.out.println(Encryptor.encrypt("123456", "LLL"));
    }

}
