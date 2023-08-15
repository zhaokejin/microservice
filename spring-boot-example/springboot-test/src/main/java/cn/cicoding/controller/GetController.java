package cn.cicoding.controller;

import cn.cicoding.bean.User;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: cicoding
 * Description:  http接口之Get请求
 */
@RestController
public class GetController {


    private final Map<String, Object> params = new HashMap<String, Object>();

    @GetMapping("/abc")
    public Object testException(){
        int i = 5/0;
        return i;
    }


    /**
     * 功能描述: 测试restful 协议， 从路径中获取字段
     * @param city_id
     * @param user_id
     * @return
     */
    @GetMapping("/{city_id}/{user_id}")
    public Object findObject(@PathVariable("city_id") String city_id, @PathVariable("user_id") String user_id){
        params.clear();
        params.put("city_id", city_id);
        params.put("user_id", user_id);
        return params;
    }

    /**
     * 功能描述：测试GetMapping
     * @param from
     * @param size
     * @return
     */
    @GetMapping(value="/v1/page_user1")
    public Object pageUser(int  from, int size ){
        params.clear();
        params.put("from", from);
        params.put("size", size);

        return params;
    }

    /**
     * 功能描述：测试默认值
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value="/list_limit")
    public Object listLimit(@RequestParam(defaultValue="0",name="page") int  page,
                             @RequestParam(defaultValue = "10", name = "size") int size ){

        params.clear();
        params.put("page", page);
        params.put("size", size);
        return params;
    }

    /**
     * 功能描述：bean对象传参
     * 注意：1、注意需要指定http头为 content-type为application/json
     * 		2、使用body传输数据
     * @param user
     * @return
     */
    @GetMapping("/save_user")
    public Object saveUser(User user){
        params.clear();
        params.put("user", user);
        return params;
    }

    /**
     * 功能描述：测试获取http头信息
     * @param accessToken
     * @param id
     * @return
     */
    @GetMapping("/get_header")
    public Object getHeader(@RequestHeader("access_token") String accessToken, String id){
        params.clear();
        params.put("access_token", accessToken);
        params.put("id", id);
        return params;
    }


}
