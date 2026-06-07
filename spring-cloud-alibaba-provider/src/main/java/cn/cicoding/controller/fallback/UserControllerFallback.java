package cn.cicoding.controller.fallback;

import cn.cicoding.model.UserDomain;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UserControllerFallback {

    public static Map<String, Object> addUserFallback(String userName, String password, String phone, Throwable e) {
        return buildErrorResponse("添加用户失败，服务被限流或熔断");
    }

    public static Map<String, Object> deleteUserFallback(Integer userId, Throwable e) {
        return buildErrorResponse("删除用户失败，服务被限流或熔断");
    }

    public static Map<String, Object> updateUserFallback(Integer userId, String userName,
                                                          String password, String phone, Throwable e) {
        return buildErrorResponse("更新用户失败，服务被限流或熔断");
    }

    public static Map<String, Object> getUsersFallback(Throwable e) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("code", 429);
        result.put("msg", "查询用户列表失败，服务被限流或熔断");
        result.put("data", Collections.emptyList());
        return result;
    }

    public static UserDomain findByIdFallback(Integer id, Throwable e) {
        return null;
    }

    public static Map<String, Object> findByIdBlockHandler(Integer id) {
        return buildErrorResponse("用户查询被限流，请稍后重试");
    }

    private static Map<String, Object> buildErrorResponse(String msg) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("code", 429);
        result.put("msg", msg);
        result.put("data", null);
        return result;
    }
}
