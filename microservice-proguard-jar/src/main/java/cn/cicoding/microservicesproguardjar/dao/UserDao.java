package cn.cicoding.microservicesproguardjar.dao;

import cn.cicoding.microservicesproguardjar.model.UserDomain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {

    int insert(UserDomain record);

    void deleteUserById(@Param("userId") Integer userId);

    void updateUser(UserDomain userDomain);

    List<UserDomain> selectUsers();

    UserDomain findById(Integer id);
}
