package cn.cicoding.controller;

import cn.cicoding.model.User;
import cn.cicoding.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Administrator
 * @date 2019/03/19
 */
@RestController
@RequestMapping("/redis-sentinel")
public class RedisTestController {
    @Autowired
    private RedisUtil redisUtil;
    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public void postTest(){
        testCommon();
        testHash();
        testSet();
        testList();
    }

    private void testCommon(){
        System.out.println("================================测试普通缓存==================");
        System.out.println("普通缓存,存入 key01 值为value01 到期时间为5秒");
        redisUtil.set("key01","value01",5);
        System.out.println("从redis获取key01的值："+redisUtil.get("key01"));
        System.out.println("到期时间为："+redisUtil.getExpire("key01"));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("6秒后从redis获取key01的值："+redisUtil.get("key01"));
        System.out.println("key01是否存在:"+redisUtil.hasKey("key01"));
    }

    private void testHash(){
        System.out.println("================================测试Hash缓存==================");
        System.out.println("hash缓存,存入 key03 值为{\"name\":\"zhangsan\",\"sex\":\"man\"}");
        Map<String,Object> map = new HashMap<>();
        map.put("name","zhangsan");
        map.put("sex","man");
        redisUtil.hmset("key03",map);
        System.out.println("key03:"+redisUtil.hget("key03","name")+"  "+redisUtil.hget("key03","sex"));
        redisUtil.del("key03");
    }

    private void testSet(){
        System.out.println("================================测试Set缓存==================");
        System.out.println("Set缓存,将两个User放入缓存key04");
        redisUtil.sSet("key04",new User("name1","man"),new User("name2","femal"));
        Set<Object> users = redisUtil.sGet("key04");
        for(Object o:users){
            User user = (User)o;
            System.out.println(o.toString());
        }
        System.out.println("获取Set key04的长度："+redisUtil.sGetSetSize("key04"));
        System.out.println("删除key04");
        redisUtil.del("key04");
        System.out.println("获取Set key04的长度："+redisUtil.sGetSetSize("key04"));

    }

    private void testList(){
        System.out.println("================================测试List缓存==================");
        System.out.println("List缓存key05");
        redisUtil.lSet("key05", Arrays.asList("aa","bb","cc","dd","ee","ff","gg"));
        System.out.println("List缓存key06");
        redisUtil.lSet("key06","11");
        redisUtil.lSet("key06","22");
        redisUtil.lSet("key06","33");
        redisUtil.lSet("key06","44");
        redisUtil.lSet("key06","55");
        redisUtil.lSet("key06","66");
        redisUtil.lSet("key06","77");
        System.out.println("以上两种方式的缓存是有区别的，注意看下面的长度");
        System.out.println("输出key05的长度："+redisUtil.lGetListSize("key05"));
        List<Object> list = redisUtil.lGet("key05",0,redisUtil.lGetListSize("key05"));
        System.out.println("输出key05的所有元素");
        for(Object str:list){
            System.out.println(str);
        }
        System.out.println("输出key06的长度："+redisUtil.lGetListSize("key06"));
        List<Object> list1 = redisUtil.lGet("key06",0,redisUtil.lGetListSize("key06"));
        System.out.println("输出key06的所有元素");
        for(Object str:list1){
            System.out.println(str);
        }

        System.out.println("删除key06的的55");
        redisUtil.lRemove("key06",1,"55");
        List<Object> list2 = redisUtil.lGet("key06",0,redisUtil.lGetListSize("key06"));
        System.out.println("输出key06的长度："+redisUtil.lGetListSize("key06"));
        System.out.println("输出key06的所有元素");
        for(Object str:list2){
            System.out.println(str);
        }

        redisUtil.del("key06");
        redisUtil.del("key05");

    }

    @PostMapping("/get")
    public void getKey() {
        System.out.println("从redis获取key01的值："+redisUtil.get("key03"));
    }
}