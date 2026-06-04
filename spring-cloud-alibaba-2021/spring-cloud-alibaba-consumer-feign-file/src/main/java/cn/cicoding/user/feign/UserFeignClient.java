package cn.cicoding.user.feign;

import cn.cicoding.user.entity.User;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "spring-cloud-alibaba-provider-file", configuration = UserFeignClient.MultipartSupportConfig.class)
public interface UserFeignClient {

    @GetMapping("/user/{id}")
    User findById(@PathVariable("id") Integer id);

    @RequestMapping(value = "/user/upload", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    String handleFileUpload(@RequestPart(value = "file") MultipartFile file);

    class MultipartSupportConfig {
        @Bean
        public Encoder feignFormEncoder() {
            return new SpringFormEncoder();
        }
    }


}
