package cn.cicoding.mybatisplus.service.impl;

import cn.cicoding.mybatisplus.bean.User;
import cn.cicoding.mybatisplus.mapper.UserMapper;
import cn.cicoding.mybatisplus.service.UserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * UserServiceImpl
 *
 * @author cicoding
 * @version v1.0
 * @since 2020/3/10
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * Mapper分页查询  使用BaseMapper里面的selectPage
     *
     * @param page
     * @param size
     * @return
     */
    @Override
    public Object selectPageMapper(int page, int size) {

        IPage pageObj = new Page(page, size);
        return userMapper.selectPage(pageObj, null);
    }

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int remove(Integer userId) {
        return userMapper.deleteById(userId);
    }

    @Override
    public int update(User user) {
        return userMapper.updateById(user);
    }

    /**
     * 自定义sql语句使用mp分页查询
     *
     * @param pageNum
     * @param size
     * @param userName
     * @return
     */
    @Override
    public Object selectPageCustom(int pageNum, int size, String userName) {
        IPage iPage = new Page(pageNum, size);
        IPage page = userMapper.selectPageCustom(iPage, userName);
        return page;
    }
}
