package com.springboot.controller;

import com.springboot.entity.User;
import com.springboot.service.MoredataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 请求控制
 */
@RestController
@RequestMapping("/moredata")
public class MoredataController {
    @Autowired
    private MoredataService moredataService;

    @RequestMapping(value = "/getDb1AllUser")
    public List<Map> getDb1AllUser() {
        List<Map> list = moredataService.getAllUser1();
        return list;
    }

    @RequestMapping(value = "/getDb2AllUser")
    public List<Map> getDb2AllUser() {
        List<Map> list = moredataService.getAllUser2();
        return list;
    }

    @RequestMapping(value = "/addDb1User")
    public String addDb1User(String name) {
        User user = new User(name,new Date());
        Long rows = moredataService.addUserGetID1(user);//返回的是结果行数
        return "{id:"+user.getId()+"}";
    }
    @RequestMapping(value = "/addDb2User")
    public String addDb2User(String name) {
        User user = new User(name,new Date());
        Long rows = moredataService.addUserGetID2(user);
        return "{id:"+user.getId()+"}";
    }

}
