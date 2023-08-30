package cn.cicoding.mybatis.controller;

import cn.cicoding.mybatis.entity.User;
import cn.cicoding.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaojinGG
 */
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return this.userMapper.selectById(id)
                .orElseThrow(() -> new IllegalArgumentException("This user does not exit!"));
    }
}

