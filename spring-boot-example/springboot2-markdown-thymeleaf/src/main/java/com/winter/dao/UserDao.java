package com.winter.dao;


import com.winter.model.Blog;
import com.winter.model.UserDomain;

import java.util.List;

public interface UserDao {


    int insert(UserDomain record);

    List<UserDomain> selectUsers();

	int insertBlog(Blog blog);

	Blog findBlogById(int id);
}