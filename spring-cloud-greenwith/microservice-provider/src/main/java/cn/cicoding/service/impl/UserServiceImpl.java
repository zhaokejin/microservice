package cn.cicoding.service.impl;

import cn.cicoding.dao.UserDao;
import cn.cicoding.model.UserDomain;
import cn.cicoding.service.UserService;
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
        throw new RuntimeException("用户信息被锁定！");
//        return userDao.findById(id);
    }
}
