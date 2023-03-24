package com.cicoding.dao.impl;

import com.cicoding.dao.UserDao;
import com.cicoding.model.User;
import com.cicoding.model.UserDomain;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户基本信息数据层实现类
 * @author weicong
 * @version 1.0 createTime 2018-12-14 21:33:45
 */
@Component
public class UserDaoImpl extends BaseDaoImpl {

    private final String PREFIX = "com.cicoding.User.";

    public UserDaoImpl(SqlSession sqlSession) {
        super(sqlSession);
    }

}
