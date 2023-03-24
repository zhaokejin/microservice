package com.cicoding.dao.impl;

import org.apache.ibatis.session.SqlSession;

/**
 * @author weicong
 * @version 1.0 createTime
 */
public class BaseDaoImpl {
    private final SqlSession sqlSession;

    public BaseDaoImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
    public SqlSession getSqlSession() {
        return sqlSession;
    }
}
