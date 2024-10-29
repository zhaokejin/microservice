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

    /**
     * Mapper分页查询
     * @param page
     * @param size
     * @return
     */
    Object selectPageMapper(int page, int size);

    int insert(User user);

    int remove(Integer userId);

    int update(User user);

    /**
     * 自定义sql语句使用mp分页查询
     * @param pageNum
     * @param size
     * @param userName
     * @return
     */
    Object selectPageCustom(int pageNum, int size, String userName);
}
