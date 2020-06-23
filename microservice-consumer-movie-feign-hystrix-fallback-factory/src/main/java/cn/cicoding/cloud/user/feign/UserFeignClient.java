package cn.cicoding.cloud.user.feign;

import cn.cicoding.cloud.user.entity.User;
import com.google.gson.Gson;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author zkj
 *
 */
@FeignClient(name = "microservice-provider"/*, fallbackFactory = UserFeignClientFallbackFactory.class*/)
public interface UserFeignClient {
  @GetMapping("/user/{id}")
  User findById(@PathVariable("id") Integer id);
}

//@Component
//@Slf4j
//class UserFeignClientFallbackFactory implements FallbackFactory<UserFeignClient> {
//  @Override
//  public UserFeignClient create(Throwable throwable) {
//    return new UserFeignClient() {
//      @Override
//      public User findById(Integer id) {
//        log.error("进入回退逻辑", throwable);
//        throwable.fillInStackTrace();
//        throwable.getMessage();
//        Gson gson = new Gson();
//        gson.toJson(throwable.getMessage());
//        return new User(id, "默认用户", "默认用户", "13899966555");
//      }
//    };
//  }
//}
