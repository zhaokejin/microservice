package com.demo.lambda;

@FunctionalInterface
interface InterDemoA {
    void doSth();
}
// 定一个接口B
interface InterDemoB {
    void doSth();
}

public class FunctionDemo {
    void doSth(InterDemoA demo) {
        System.out.println("function A");
        demo.doSth();
    }
    //定义一个B的方法
    void doSth(InterDemoB demo) {
        System.out.println("function B");
        demo.doSth();
    }

    public static void main(String[] args) {
        FunctionDemo functionDemo = new FunctionDemo();
        // 此时Lambda表达式不能识别匹配类型,需要加上类型匹配
        functionDemo.doSth((InterDemoB) () -> System.out.println("do sth"));
    }
}
