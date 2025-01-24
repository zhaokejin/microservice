package cn.cicoding.aop;

import java.lang.annotation.*;

/**
 * 防重
 *
 * @author
 * @date 2020/8/12
 * @return
 */
//标识该注解用于方法上
@Target({ElementType.METHOD})
//申明该注解为运行时注解，编译后改注解不会被遗弃
@Retention(RetentionPolicy.RUNTIME)
//javadoc工具记录
@Documented
public @interface PreventSubmit {
}