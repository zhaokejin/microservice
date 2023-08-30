package com.example.springbootdatasource.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.example.springbootdatasource.dao.UserDao;
import com.example.springbootdatasource.model.UserDomain;
import com.example.springbootdatasource.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@DS("master")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public int insert(UserDomain record) {
        return userDao.insert(record);
    }

}
