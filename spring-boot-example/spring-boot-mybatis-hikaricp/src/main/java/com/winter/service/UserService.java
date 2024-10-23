package com.winter.service;

import com.winter.model.UserDomain;

import java.util.List;

/**
 * @author cicoding.cn
 */
public interface UserService {

    int insert(UserDomain record);

    void deleteUserById(Integer userId);

    void updateUser(UserDomain userDomain);

    List<UserDomain> selectUsers();

}
