package cn.cicoding.mybatisplus.service.impl;

import cn.cicoding.mybatisplus.bean.User;
import cn.cicoding.mybatisplus.mapper.UserMapper;
import cn.cicoding.mybatisplus.service.UserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * UserServiceImpl
 *
 * @author cicoding
 * @version v1.0
 * @since 2020/3/10
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public Object listAll(int page, int size) {

        Page pageObj = new Page(page, size);
        return userMapper.selectPage(pageObj, null);
    }

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int remove(Integer userId) {
        return userMapper.deleteById(userId);
    }

    @Override
    public int update(User user) {
        return userMapper.updateById(user);
    }
}
