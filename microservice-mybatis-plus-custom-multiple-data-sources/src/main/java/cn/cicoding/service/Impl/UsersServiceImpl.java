package cn.cicoding.service.Impl;

import cn.cicoding.DataSource;
import cn.cicoding.DataSourceNames;
import cn.cicoding.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.cicoding.entity.User;
import cn.cicoding.service.UsersService;
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