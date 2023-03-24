package com.cicoding.dao;


import com.cicoding.model.User;
import com.cicoding.model.UserDomain;

import java.util.List;

public interface UserDao {


    int insert(UserDomain record);

    List<UserDomain> selectUsers();

    User selectUsersName(String username);

	User save(User user);

	Long countByUsername(String username);

	Long countByEmail(String email);
}