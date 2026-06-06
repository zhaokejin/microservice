package cn.cicoding.consumer.file.feign;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@FeignClient(name = "spring-cloud-alibaba-provider-file",
             configuration = MultipartSupportConfig.class)
public interface UserFeignClient {

    @PostMapping(value = "/user/upload", consumes = MULTIPART_FORM_DATA_VALUE)
    @Headers("Content-Type: multipart/form-data")
    String handleFileUpload(@RequestPart("file") MultipartFile file);
}
