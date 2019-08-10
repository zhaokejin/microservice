package com.example;

import com.example.entity.User;
import com.example.service.UsersService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MicroserviceMybatisPlusCustomMultipleDataSourcesApplicationTests {

    @Autowired
    UsersService userService;

    @Test
    public void contextLoads() {

        User user = userService.getById(1);
        System.out.println(user.toString());

    }

    @Test
    public void test() {
        User user = userService.findUserByFirstDb(1);
        System.out.println("第一个数据库---------》" + user.toString());


        User user2 = userService.findUserBySecondDb(1);
        System.out.println("第二个数据库---------》" + user2.toString());
    }

}