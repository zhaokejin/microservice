package com.cicoding.service.impl;

import com.cicoding.exception.ApplicationRuntimeException;
import com.cicoding.model.User;
import com.cicoding.util.Md5Util;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.cicoding.dao.UserDao;
import com.cicoding.model.UserDomain;
import com.cicoding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "userService")
public class UserServiceImpl implements UserService,UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    @Qualifier("userPasswordEncoder")
    private PasswordEncoder passwordEncoder;

    @Override
    public int addUser(UserDomain user) {

        return userDao.insert(user);
    }

    /**
    * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
    * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
    * pageNum 开始页数
    * pageSize 每页显示的数据条数
    * */
    @Override
    public PageInfo<UserDomain> findAllUser(int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum, pageSize);
        List<UserDomain> userDomains = userDao.selectUsers();
        PageInfo result = new PageInfo(userDomains);
        return result;
    }

    @Override
    public List<UserDomain> findAllUserList() {
        return userDao.selectUsers();
    }

    @Override
    public void register(User user) {
        // 判断用户名是否存在
        if (countByUsername(user.getUsername()) > 0) {
            throw new ApplicationRuntimeException("用户名已存在");
        }
        // 判断邮箱是否存在
        if (countByEmail(user.getEmail()) > 0) {
            throw new ApplicationRuntimeException("邮箱已存在");
        }
        // 密码加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        save(user);
    }

    @Cacheable
    public Long countByUsername(String username) {
        return userDao.countByUsername(username);
    }

    @Cacheable
    public Long countByEmail(String email) {
        return userDao.countByEmail(email);
    }

    public static Map<String, Object> success(String msg){
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", "1");
        result.put("msg", msg);
        return result;
    }

    public static Map<String, Object> fail(String msg){
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", "0");
        result.put("msg", msg);
        return result;
    }

    @CacheEvict(allEntries = true)
    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    @Cacheable
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.selectUsersName(username);
        return user;
    }
}
