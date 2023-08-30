package cn.cicoding.mybatis.mapper;

import cn.cicoding.mybatis.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * created with IntelliJ IDEA.
 * author: cicoding
 * date: 2020/03/18
 * version: 1.0
 * description:
 */
@Mapper
public interface UserMapper {


    @Select({
            "select * from user"
    })
    List<User> listAll();

    @Insert({
            "insert into user(`username`, `password`) values(#{username}, #{password})"
    })
    int insert(User user);

    @Delete({
            "delete from user where id = #{userId}"
    })
    int remove(Integer userId);

    @Update({
            "update user set username = #{username}, password = #{password} where id = #{id}"
    })
    int update(User user);

}
