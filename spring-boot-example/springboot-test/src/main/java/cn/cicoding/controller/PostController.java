package cn.cicoding.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: cicoding
 * Description:
 */
@RestController
public class PostController {

    private final Map<String, Object> params = new HashMap<String, Object>();

    /**
     * 功能描述：测试Post请求
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public Object login(String username, String password){
        params.clear();
        params.put("username", username);
        params.put("password", password);
        return params;
    }

}
