package cn.cicoding.consumer.file.controller;

import cn.cicoding.consumer.file.feign.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/feign")
public class FeignFileController {

    @Autowired
    private UserFeignClient userFeignClient;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        return userFeignClient.handleFileUpload(file);
    }
}
