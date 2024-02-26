package cn.cicoding.mybatis.mapper;

import cn.cicoding.mybatis.bean.UserExcel;
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
public interface ExcelUserMapper {


    @Select({
            "select * from t_user"
    })
    List<UserExcel> listAll();

    @Insert({
            "insert into t_user(`username`, `password`) values(#{username}, #{password})"
    })
    int insert(UserExcel user);

    @Delete({
            "delete from t_user where id = #{userId}"
    })
    int remove(Integer userId);

    @Update({
            "update t_user set username = #{username}, password = #{password} where id = #{id}"
    })
    int update(UserExcel user);

}
