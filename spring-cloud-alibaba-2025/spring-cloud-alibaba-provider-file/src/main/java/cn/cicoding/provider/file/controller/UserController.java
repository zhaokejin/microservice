package cn.cicoding.provider.file.controller;

import cn.cicoding.provider.file.model.UserDomain;
import cn.cicoding.provider.file.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Value("${file.upload-dir:D:/}")
    private String uploadDir;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<String> addUser(@RequestParam String userName,
                                          @RequestParam String password,
                                          @RequestParam(required = false) String phone) {
        UserDomain userDomain = new UserDomain();
        userDomain.setUserName(userName);
        userDomain.setPassword(password);
        userDomain.setPhone(phone);
        userService.insert(userDomain);
        return ResponseEntity.ok("添加成功");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUser(@RequestParam Integer userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.ok("删除成功");
    }

    @PutMapping
    public ResponseEntity<String> updateUser(@RequestParam Integer userId,
                                             @RequestParam(required = false) String userName,
                                             @RequestParam(required = false) String password,
                                             @RequestParam(required = false) String phone) {
        UserDomain userDomain = new UserDomain();
        userDomain.setId(userId);
        userDomain.setUserName(userName);
        userDomain.setPassword(password);
        userDomain.setPhone(phone);
        userService.updateUser(userDomain);
        return ResponseEntity.ok("更新成功");
    }

    @GetMapping
    public ResponseEntity<Object> getUsers() {
        return ResponseEntity.ok(userService.selectUsers());
    }

    @GetMapping("/{id}")
    public UserDomain findById(@PathVariable Integer id) {
        return userService.findById(id);
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        File dest = new File(uploadDir + fileName);
        file.transferTo(dest);
        return "上传成功";
    }
}
