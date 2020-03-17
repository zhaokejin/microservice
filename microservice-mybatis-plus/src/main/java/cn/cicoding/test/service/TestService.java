package cn.cicoding.test.service;


import cn.cicoding.test.vo.User;
import cn.cicoding.utils.ResultSet;

import java.util.List;

public interface TestService {
    int insert(User record);

    int add(User userDomain);

    void deleteUserById(Integer userId);

    List<User> selectUsers();

    User findById(Integer userId);

    public ResultSet selectUsersPages(User user);
}
