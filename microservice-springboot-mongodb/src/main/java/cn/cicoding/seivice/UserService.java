package cn.cicoding.seivice;

import cn.cicoding.entity.User;

import java.util.List;

public interface UserService {

    int insert(User record);

    void deleteUserById(Integer userId);

    void updateUser(User userDomain);

    List<User> selectUsers();

    User findById(Integer id);

    int saveMysqlMongoDb(User user);

    int saveMysql(User user);

    int saveMongoDb(User user);
}
