package com.abc.function;

import org.junit.Test;

import java.util.function.Supplier;

public class Test3 {

    @Test
    public void test01() {
        Supplier<String> supp = () -> "Lambda";
        System.out.println(supp.get());
    }

}
