package com.winter.service;

import com.github.pagehelper.PageInfo;
import com.winter.model.UserDomain;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {

    int addUser(UserDomain user);

    PageInfo<UserDomain> findAllUser(int pageNum, int pageSize);

    List<UserDomain> findAllUserList();
}
