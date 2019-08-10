package com.example.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.DataSource;
import com.example.DataSourceNames;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.service.UsersService;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl extends ServiceImpl<UserMapper, User> implements UsersService {

    @Override
    public User findUserByFirstDb(int id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    @DataSource(name = DataSourceNames.SECOND)
    public User findUserBySecondDb(int id) {
        return this.baseMapper.selectById(id);
    }
}