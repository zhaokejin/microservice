package cn.cicoding.user.controller;

import cn.cicoding.user.entity.User;
import cn.cicoding.user.feign.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/fegin")
@RestController
public class FeginController {

    @Autowired(required = false)
    private UserFeignClient userFeignClient;

    @GetMapping("/user/{id}")
    public User findById(@PathVariable Integer id) {
        return this.userFeignClient.findById(id);
    }

    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {
        userFeignClient.handleFileUpload(file);
        return "success";
    }

}
