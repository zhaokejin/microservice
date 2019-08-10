package com.example.controller;

import com.example.dao.MongoTestDao;
import com.example.entity.Mongo;
import com.example.utils.MongodbUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private MongoTestDao mtdao;


    @GetMapping(value="/add")
    public ResponseEntity saveTest() throws Exception {
        for (int i = 0;i<=1000;i++) {
            Mongo mgtest=new Mongo();
            mgtest.setId(i);
            mgtest.setAge(i);
            mgtest.setName("ceshi" + i);
            mtdao.saveTest(mgtest);
            MongodbUtils.save(mgtest,"mgtest");
//            Thread.sleep(2000);
        }
        return ResponseEntity.ok("添加成功");
    }

    @GetMapping(value="/find")
    public Mongo findTestByName(){
        Mongo mgtest= mtdao.findTestByName("ceshi");
        System.out.println("mgtest is "+mgtest);
        return mgtest;
    }

    @GetMapping(value="/update")
    public ResponseEntity updateTest(){
        Mongo mgtest=new Mongo();
        mgtest.setId(11);
        mgtest.setAge(44);
        mgtest.setName("ceshi2");
        mtdao.updateTest(mgtest);
        return ResponseEntity.ok("修改成功");
    }

    @GetMapping(value="/delete")
    public ResponseEntity deleteTestById(){
        mtdao.deleteTestById(11);
        return ResponseEntity.ok("删除成功");
    }
}
