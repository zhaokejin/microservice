package com.springboot.dao;

import com.springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * dao层
 *
 */
@Mapper
public interface MoredataDao {
    //使用xml配置形式查询
    public List<Map> getAllUser();
    public Long addUserGetID(User user);
    public void addUser(@Param("name") String name);
}
