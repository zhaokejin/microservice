package com.demo.lambda;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class JdkFunctionDemo {

    public static void main(String[] args) {
        // Supplier没有输入只有一个输出
        Supplier<String> supplier = () -> "this is supplier demo";
        System.out.println(supplier.get());

        // Consumer只有一个输入没有输出
        Consumer<String> consumer = i -> System.out.println("this is demo for " + i);
        consumer.accept("Consumer");

        // Function输入T输出R
        Function<Integer, Integer> function = i -> i * i;
        System.out.println("Function demo : " + function.apply(9));
        // Unaryoperator输出输入都是T
        UnaryOperator<Integer> unaryoperator = i -> i * i;
        System.out.println("UnaryOperator demo: " + unaryoperator.apply(9));

    }
}
