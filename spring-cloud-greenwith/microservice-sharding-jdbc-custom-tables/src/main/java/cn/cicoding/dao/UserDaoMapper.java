package cn.cicoding.dao;

import cn.cicoding.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDaoMapper {
    Long addUser(User user);

    List<User> list();

    User findById(Long id);

    User findByName(String name);
}
