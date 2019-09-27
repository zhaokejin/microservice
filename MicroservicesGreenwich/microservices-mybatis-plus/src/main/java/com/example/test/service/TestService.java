package com.example.test.service;


import com.example.test.vo.User;
import com.example.utils.ResultSet;

import java.util.List;

public interface TestService {
    int insert(User record);

    int add(User userDomain);

    void deleteUserById(Integer userId);

    List<User> selectUsers();

    User findById(Integer userId);

    public ResultSet selectUsersPages(User user);
}
