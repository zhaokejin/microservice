package com.example.mp.service;

import com.example.mp.entity.UserDomain;

import java.util.List;

public interface UserService {

    int insert(UserDomain record);

    void deleteUserById(Integer userId);

    void updateUser(UserDomain userDomain);

    List<UserDomain> selectUsers();

    UserDomain findById(Integer id);
}
