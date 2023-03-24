package com.cicoding.service;

import com.cicoding.model.User;
import com.github.pagehelper.PageInfo;
import com.cicoding.model.UserDomain;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {

    int addUser(UserDomain user);

    PageInfo<UserDomain> findAllUser(int pageNum, int pageSize);

    List<UserDomain> findAllUserList();

    /**
     * session的key：当前登录人
     */
    public static final String SESSION_CURRENT_USER = "CURRENT_USER";

    /**
     * 用户是否被禁用：禁用
     */
    public static final String DIS_1 = "1";

    /**
     * 用户是否被禁用：未禁用
     */
    public static final String DIS_0 = "0";

    /**
     * 用户注册
     * @param user
     */
    public void register(User user);

}
