package cn.cicoding.mybatisplus.service;

import cn.cicoding.mybatisplus.bean.User;

/**
 * UserService
 *
 * @author cicoding
 * @version v1.0
 * @since 2020/3/10
 */
public interface UserService {

    Object listAll(int page, int size);

    int insert(User user);

    int remove(Integer userId);

    int update(User user);

}
