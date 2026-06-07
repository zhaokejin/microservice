package cn.cicoding.service.impl;

import cn.cicoding.dao.UserDao;
import cn.cicoding.model.UserDomain;
import cn.cicoding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @CacheEvict(value = "user", allEntries = true)
    public int insert(UserDomain record) {
        return userDao.insert(record);
    }

    @Override
    @CacheEvict(value = "user", allEntries = true)
    public void deleteUserById(Integer userId) {
        userDao.deleteUserById(userId);
    }

    @Override
    @CacheEvict(value = "user", allEntries = true)
    public void updateUser(UserDomain userDomain) {
        userDao.updateUser(userDomain);
    }

    @Override
    public List<UserDomain> selectUsers() {
        return userDao.selectUsers();
    }

    @Override
    @Cacheable(value = "user", key = "'user:id:'+#id")
    public UserDomain findById(Integer id) {
        return userDao.findById(id);
    }
}
