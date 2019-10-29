package cn.cicoding.service.impl;

import cn.cicoding.mapper.TestMapper;
import cn.cicoding.service.TestService;
import cn.cicoding.vo.User;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@DS("slave_1")
public class TestServiceImpl implements TestService {

    @Autowired
    TestMapper testMapper;
    @Override
    public int insert(User user) {
        return testMapper.insert(user);
    }

    @Override
    public int add(User user) {
        return testMapper.add(user);
    }

    @Override
    public void deleteUserById(Integer userId) {
        testMapper.deleteById(userId);
    }

    @Override
    public List<User> selectUsers() {
        return testMapper.selectList(null);
    }

    @Override
    @DS("master")
    public User findById(Integer userId) {
        return testMapper.selectById(userId);
    }


}
