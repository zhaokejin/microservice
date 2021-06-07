package cn.cicoding.controller;

import cn.cicoding.service.UserService;
import cn.cicoding.model.User;
import cn.cicoding.model.UserItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public Object list() {
        return userService.list();
    }

    @GetMapping("/add")
    public Object add() {
        for (long i = 1; i < 101; i++) {
            User user = new User();
            user.setId(i);
            user.setPassword("aaaaaa");
            user.setUsername("A" + i);
            userService.add(user);

            UserItem userItem = new UserItem();
            userItem.setId(i);
            userItem.setUserId(i);
            userItem.setName("AAA" + i);
            userItem.setSex("100" + 1);
            userService.addUserItem(userItem);

        }
        return "success";
    }

    @GetMapping("/users/{id}")
    public Object get(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/users/query")
    public Object get(String name) {
        return userService.findByName(name);
    }

    @GetMapping("/clean")
    public Object clean() {
        return userService.clean();
    }
}