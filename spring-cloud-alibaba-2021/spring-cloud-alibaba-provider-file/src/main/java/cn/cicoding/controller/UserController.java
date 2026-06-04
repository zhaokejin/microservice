package cn.cicoding.controller;

import cn.cicoding.model.UserDomain;
import cn.cicoding.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;


    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }

        String fileName = file.getOriginalFilename();
        String filePath = "D:/";
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            logger.info("上传成功");
            return "上传成功";
        } catch (IOException e) {
            logger.error(e.toString(), e);
        }
        return "上传失败！";
    }


    @PostMapping("")
    public ResponseEntity addUser(@RequestParam(value = "userName", required = true) String userName, @RequestParam(value = "password", required = true)
            String password, @RequestParam(value = "phone", required = false) String phone){
        UserDomain userDomain = new UserDomain();
        userDomain.setUserName(userName);
        userDomain.setPassword(password);
        userDomain.setPhone(phone);
        userService.insert(userDomain);
        return ResponseEntity.ok("添加成功");
    }

    @DeleteMapping("")
    public ResponseEntity deleteUser(@RequestParam(value = "userId", required = true) Integer userId){

        userService.deleteUserById(userId);
        return ResponseEntity.ok("删除成功");
    }

    @PutMapping("")
    public ResponseEntity updateUser(@RequestParam(value = "userId", required = true) Integer userId, @RequestParam(value = "userName", required = false) String userName,
            @RequestParam(value = "password", required = false) String password, @RequestParam(value = "phone", required = false) String phone ){
        UserDomain userDomain = new UserDomain();
        userDomain.setUserId(userId);
        userDomain.setUserName(userName);
        userDomain.setPassword(password);
        userDomain.setPhone(phone);
        userService.updateUser(userDomain);
        return ResponseEntity.ok("更新成功");
    }

    @GetMapping("")
    public ResponseEntity getUsers(){
        return ResponseEntity.ok(userService.selectUsers());
    }

    @GetMapping("/{id}")
    public UserDomain findById(@PathVariable Integer id) {
        return this.userService.findById(id);
    }

}
