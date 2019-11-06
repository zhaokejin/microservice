package com.example.seivice.impl;

import com.example.dao.MongoTestDao;
import com.example.dao.UserDao;
import com.example.entity.User;
import com.example.seivice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private MongoTestDao mtdao;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveMysqlMongoDb(User user) {
        mtdao.saveTest(user);
        userDao.insert(user);

        int i = 1/0;

        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveMysql(User user) {
        userDao.insert(user);
        int i = 1/0;
        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveMongoDb(User user) {
        mtdao.saveTest(user);
        int i = 1/0;
        return 0;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(User record) {
        mtdao.saveTest(record);
        userDao.insert(record);

        int i = 1/0;

        return 0;
    }

    @Override
    public void deleteUserById(Integer userId) {
        userDao.deleteUserById(userId);
    }

    @Override
    public void updateUser(User userDomain) {
        userDao.updateUser(userDomain);
    }

    @Override
    public List<User> selectUsers() {
        return userDao.selectUsers();
    }

    @Override
    public User findById(Integer id) {
        return userDao.findById(id);
    }

}
