package com.example.test.controller;

import com.example.test.service.TestService;
import com.example.test.vo.User;
import com.example.utils.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class TestMapperController {


    @Autowired
    private TestService userService;
    @PostMapping("insert")
    public ResponseEntity addUser(@RequestParam(value = "userName", required = true) String userName, @RequestParam(value = "password", required = true)
            String password, @RequestParam(value = "phone", required = false) String phone){
        User userDomain = new User();
        userDomain.setUserName(userName);
        userDomain.setPassword(password);
        userDomain.setPhone(phone);
        userService.insert(userDomain);
        return ResponseEntity.ok("添加成功");
    }


    @PostMapping("add")
    public ResponseEntity add(@RequestParam(value = "userName", required = true) String userName, @RequestParam(value = "password", required = true)
            String password, @RequestParam(value = "phone", required = false) String phone){
        User userDomain = new User();
        userDomain.setUserName(userName);
        userDomain.setPassword(password);
        userDomain.setPhone(phone);
        userService.add(userDomain);
        return ResponseEntity.ok("添加成功");
    }

    @DeleteMapping("")
    public ResponseEntity deleteUser(@RequestParam(value = "userId", required = true) Integer userId){

        userService.deleteUserById(userId);
        return ResponseEntity.ok("删除成功");
    }

    @GetMapping("")
    public ResponseEntity getUsers(){
        return ResponseEntity.ok(userService.selectUsers());
    }

    @GetMapping("/{userId}")
    public User findById(@PathVariable Integer userId) {
        return this.userService.findById(userId);
    }

    @PostMapping("/selectCapitalPageList")
    public ResultSet selectCapitalPageList(@RequestBody User user){
        return this.userService.selectUsersPages(user);
    }
}