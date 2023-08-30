package com.springboot.service;

import com.springboot.config.DS;
import com.springboot.dao.MoredataDao;
import com.springboot.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * service层
 */
@Service
public class MoredataService {
    @Autowired
    private MoredataDao moredataDao;

    //使用数据源1查询
   @DS("datasource1")
    public List<Map> getAllUser1(){
        return moredataDao.getAllUser();
    }
    //使用数据源2查询
    @DS("datasource2")
    public List<Map> getAllUser2(){
        return moredataDao.getAllUser();
    }

    //使用数据源1插入数据
    @DS("datasource1")
    public Long addUserGetID1(User user){
        return moredataDao.addUserGetID(user);
    }
    //使用数据源1插入数据
    @DS("datasource2")
    public Long addUserGetID2(User user){
        return moredataDao.addUserGetID(user);
    }

    //使用数据源1插入数据
    @DS("datasource1")
    public void addUser1(String name){
        moredataDao.addUser(name);
    }
    //使用数据源2插入数据
    @DS("datasource2")
    public void addUser2(String name){
        moredataDao.addUser(name);
    }
}
