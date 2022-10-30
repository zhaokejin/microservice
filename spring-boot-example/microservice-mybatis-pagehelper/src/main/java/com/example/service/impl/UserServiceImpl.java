package com.example.service.impl;

import com.example.dao.UserDao;
import com.example.model.UserDomain;
import com.example.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public int insert(UserDomain record) {
        return userDao.insert(record);
    }

    @Override
    public void deleteUserById(Integer userId) {
        userDao.deleteUserById(userId);
    }

    @Override
    public void updateUser(UserDomain userDomain) {
        userDao.updateUser(userDomain);
    }

    @Override
    public List<UserDomain> selectUsers() {
        return userDao.selectUsers();
    }

    @Override
    public UserDomain findById(Integer id) {
        return userDao.findById(id);
    }

    @Override
    public PageInfo<UserDomain> getPage(Object o, int page, int limit) {
        PageHelper.startPage(page, limit);
        List<UserDomain> users = userDao.selectUsers();
        return new PageInfo<>(users);
    }
}
