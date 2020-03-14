package cn.cicoding.service.impl;

import cn.cicoding.dao.UserDao;
import cn.cicoding.model.UserDomain;
import cn.cicoding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
    @CacheEvict(value = "db0", key ="T(String).valueOf(#userId)")                           //写法一
    public void deleteUserById(Integer userId) {
        userDao.deleteUserById(userId);
    }

    @Override
//    @CachePut(value = "db0", key ="T(String).valueOf(#userDomain.userId)")                //写法一
    @CachePut(value = "db0", key = "#userDomain.userId")                                    //写法二
    public UserDomain updateUser(UserDomain userDomain) {
        userDao.updateUser(userDomain);
        UserDomain user = userDao.findById(userDomain.getUserId());
        return user;
    }

    @Override
//    @Cacheable("users")                               //缓存users
//    @CacheEvict("users")                              //删除users
    public List<UserDomain> selectUsers() {
        return userDao.selectUsers();
    }

    @Override
    @Cacheable("#id")                                                                       //写法二
//    @Cacheable(value="db0",key="T(String).valueOf(#id)")//把id转换为string类型的         //写法一
    public UserDomain findById(Integer id) {
        return userDao.findById(id);
    }
}
