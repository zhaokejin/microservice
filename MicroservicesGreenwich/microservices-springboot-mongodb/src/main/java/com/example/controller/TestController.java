package com.example.controller;

import com.example.dao.MongoTestDao;
import com.example.entity.User;
import com.example.seivice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private MongoTestDao mtdao;

    @Autowired
    private UserService userService;

    @PostMapping("/mongo/saveMysql")
    public int saveMysql(@RequestBody User user) {
        return userService.saveMysql(user);

    }

    @PostMapping("/mongo/saveMongoDb")
    public int saveMongoDb(@RequestBody User user) {
        return userService.saveMongoDb(user);

    }

    @PostMapping("/mongo/saveMysqlMongoDb")
    public int saveMysqlMongoDb(@RequestBody User user) {
        return userService.saveMysqlMongoDb(user);

    }







    @GetMapping(value="/add")
    public ResponseEntity saveTest() {
        for (int i = 0;i<=1000;i++) {
            User user=new User();
            user.setUserId(i);
            user.setUserName("123");
            user.setPassword("ceshi" + i);
            mtdao.saveTest(user);
//            Thread.sleep(2000);
        }
        return ResponseEntity.ok("添加成功");
    }

    @GetMapping(value="/find")
    public User findTestByName(){
        User mgtest= mtdao.findTestByName("ceshi");
        System.out.println("mgtest is "+mgtest);
        return mgtest;
    }

    @GetMapping(value="/update")
    public ResponseEntity updateTest(){
        User user = new User();
        user.setUserId(11);
        user.setPassword("123456");
        user.setUserName("ceshi2");
        mtdao.updateTest(user);
        return ResponseEntity.ok("修改成功");
    }

    @GetMapping(value="/delete")
    public ResponseEntity deleteTestById(){
        mtdao.deleteTestById(11);
        return ResponseEntity.ok("删除成功");
    }
}
