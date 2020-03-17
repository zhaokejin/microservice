package cn.cicoding.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.cicoding.entity.User;

public interface UsersService extends IService<User> {
    User findUserByFirstDb(int id);

    User findUserBySecondDb(int id);
}
