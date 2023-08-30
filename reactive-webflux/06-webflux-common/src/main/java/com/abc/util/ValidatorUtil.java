package com.abc.util;

import com.abc.exception.StudentException;

import java.util.stream.Stream;

// 参数校验工具类
public class ValidatorUtil {
    // 无效姓名列表
    private static final String[] INVALIDE_NAMES = {"admin", "administrator"};

    // 校验姓名
    public static void validateName(String name) {
        Stream.of(INVALIDE_NAMES)   // Stream，元素为"admin", "administrator"
                // 若name与invlideNmae相等，则通过过滤，保留在Stream中
                .filter(invalideName -> name.equalsIgnoreCase(invalideName))
                .findAny()   // Optional
                // 只要Optional中封装的对象不空，则说明当前name为无效姓名
                .ifPresent(invalideName -> {
                    throw new StudentException("使用了非法姓名", "name", invalideName);
                });
    }
}
