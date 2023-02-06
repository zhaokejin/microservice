package com.cicoding.booti18n.common;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

/**
 * @DESC
 * @Author:Caixiaowei
 * @Date:2019/1/16
 * @Time:下午4:34
 */
@Component
public class MessageUtils {

    public static String getMessage(String key, Object...args) {
        MessageSource messageSource = SpringUtils.getBean(MessageSource.class);
        String message = messageSource.getMessage(key, args, null);
        return message;
    }
}
