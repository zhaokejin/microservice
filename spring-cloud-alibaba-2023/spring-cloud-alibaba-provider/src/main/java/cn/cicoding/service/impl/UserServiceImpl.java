package cn.cicoding.service.impl;

import cn.cicoding.mapper.UserMapper;
import cn.cicoding.model.UserDomain;
import cn.cicoding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int insert(UserDomain record) {
        return userMapper.insert(record);
    }

    @Override
    public void deleteUserById(Integer userId) {
        userMapper.deleteUserById(userId);
    }

    @Override
    public void updateUser(UserDomain userDomain) {
        userMapper.updateUser(userDomain);
    }

    @Override
    public List<UserDomain> selectUsers() {
        return userMapper.selectUsers();
    }

    @Override
    public UserDomain findById(Integer id) {
        return userMapper.findById(id);
    }
}
