package cn.cicoding.aop;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubmitController {

    @PostMapping("submit")
    @PreventSubmit
    public Object submit(@RequestBody UserBean userBean) {
        return new ApiResult(200, "成功", userBean.getUserId());
    }

}