package cn.cicoding.service;


import cn.cicoding.vo.User;

import java.util.List;

public interface TestService {
    int insert(User record);

    int add(User userDomain);

    void deleteUserById(Integer userId);

    List<User> selectUsers();

    User findById(Integer userId);

}
