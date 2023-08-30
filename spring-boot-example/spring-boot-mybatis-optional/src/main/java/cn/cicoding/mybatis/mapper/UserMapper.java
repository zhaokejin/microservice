package cn.cicoding.mybatis.mapper;

import cn.cicoding.mybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

/**
 * @author xiaojinGG
 */
@Mapper
public interface UserMapper {
    @Select("select * from user where id = #{id}")
    Optional<User> selectById(Long id);
}
