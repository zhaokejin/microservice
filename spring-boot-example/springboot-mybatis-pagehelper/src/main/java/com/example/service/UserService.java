package com.example.service;

import com.example.model.UserDomain;
import com.github.pagehelper.PageInfo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    int insert(UserDomain record);

    void deleteUserById(Integer userId);

    void updateUser(UserDomain userDomain);

    List<UserDomain> selectUsers();

    UserDomain findById(Integer id);

    /**
     * 分页查询
     *
     * @param page
     * @param limit
     * @return
     */
    PageInfo<UserDomain> getPage(int page, int limit);
}
