package cn.cicoding;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class Test {
    public static void main(String[] args) throws Exception {
        Method method = Test.class.getMethod("testMethod", String.class);
        for (Parameter parameter : method.getParameters()) {
            System.out.println(parameter.getName());
        }
    }

    public void testMethod(String myParameter) {
        // 方法体
    }
}