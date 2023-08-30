package com.demo.lambda;

public class LambdaDemo {

    public static void main(String[] args) {

        // 正常创建线程的方式
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Normal创建一个线程");
            }
        }).start();

        // lambda方式创建线程
        new Thread(() -> System.out.println("Lambda创建一个线程")).start();
    }
}
