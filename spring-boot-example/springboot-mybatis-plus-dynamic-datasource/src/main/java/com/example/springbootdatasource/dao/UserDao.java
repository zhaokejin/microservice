package com.example.springbootdatasource.dao;

import com.example.springbootdatasource.model.UserDomain;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

    int insert(UserDomain record);

}
