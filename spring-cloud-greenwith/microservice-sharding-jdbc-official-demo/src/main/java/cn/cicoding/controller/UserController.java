package cn.cicoding.controller;

import cn.cicoding.model.User;
import cn.cicoding.service.UserService;
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
        String companyId;
        for (long i = 0; i < 100; i++) {
            User user = new User();
            user.setId(i);
            if (i % 2 == 0) {
                companyId = "alibaba";
            } else {
                companyId = "baidu";
            }
            user.setCompanyId(companyId);
            user.setName(i%2==0?1:2);
            userService.add(user);
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