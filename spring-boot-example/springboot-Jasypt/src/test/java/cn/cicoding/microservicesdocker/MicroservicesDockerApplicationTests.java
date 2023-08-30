package cn.cicoding.microservicesdocker;

import cn.cicoding.jasypt.SpringBootJasyptApplication;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootJasyptApplication.class)
public class MicroservicesDockerApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    StringEncryptor jasyptStringEncryptor;

    @Test
    public void encrypt() {
        System.out.println("encrypt: " + jasyptStringEncryptor.encrypt("root"));
    }

    @Test
    public void decrypt() {
        System.out.println("decrypt: " + jasyptStringEncryptor.decrypt("o9uLVKcJV4C7SkdF9sZJzQ=="));
    }
}
