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
     * 查询全部
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/listAll")
    public Object listAll(@RequestParam(value = "page",defaultValue = "1")int page,
                          @RequestParam(value = "size",defaultValue = "10")int size){
        return userService.listAll(page, size);
    }

    /**
     * 添加数据
     * @param user
     * @return
     */
    @RequestMapping("/insert")
    public int insert (User user){
        return userService.insert(user);
    }

    /**
     * 删除
     * @param userId
     * @return
     */
    @RequestMapping("/remove")
    public int remove(Integer userId){
        return userService.remove(userId);
    }

    /**
     * 修改
     * @param user
     * @return
     */
    @RequestMapping("/update")
    public int update(User user){
        return userService.update(user);
    }

}


