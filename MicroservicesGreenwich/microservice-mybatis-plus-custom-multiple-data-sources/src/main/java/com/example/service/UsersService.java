package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.User;

public interface UsersService extends IService<User> {
    User findUserByFirstDb(int id);

    User findUserBySecondDb(int id);
}
