package cn.cicoding.mybatisplus.controller;

import cn.cicoding.mybatisplus.bean.User;
import cn.cicoding.mybatisplus.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * UserController
 *
 * @author cicoding
 * @version v1.0
 * @since 2020/3/10 1:30
 */
@RestController
public class UserController {

    @Resource
    private UserService userService;

    /**
     * Mapper分页查询
     *
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/selectPageMapper")
    public Object selectPageMapper(@RequestParam(value = "page", defaultValue = "1") int page,
                                   @RequestParam(value = "size", defaultValue = "10") int size) {
        return userService.selectPageMapper(page, size);
    }

    /**
     * 自定义sql语句使用mp分页查询
     *
     * @param pageNum
     * @param size
     * @param userName
     * @return
     */
    @RequestMapping("/selectPageCustom")
    public Object selectPage(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                             @RequestParam(value = "size", defaultValue = "10") int size,
                             @RequestParam(value = "userName") String userName) {
        return userService.selectPageCustom(pageNum, size, userName);
    }

    /**
     * 添加数据
     *
     * @param user
     * @return
     */
    @RequestMapping("/insert")
    public int insert(User user) {
        return userService.insert(user);
    }

    /**
     * 删除
     *
     * @param userId
     * @return
     */
    @RequestMapping("/remove")
    public int remove(Integer userId) {
        return userService.remove(userId);
    }

    /**
     * 修改
     *
     * @param user
     * @return
     */
    @RequestMapping("/update")
    public int update(User user) {
        return userService.update(user);
    }

}


