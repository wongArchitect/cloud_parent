package com.nm;

import java.util.ArrayList;

public class Test {

    // ArrayList<? extends Number> 表示泛型类型具体是什么不知道，但是具体类型必须是 Number 及其子类类型。例如：ArrayList<Number>，ArrayList<Integer>，ArrayList<Double> 等。
    // List<? extends T>必须是T类型极其子类；可以接受任何继承自T的类型的List，
    // List<? super T> 必须是某个类型的父类；可以接受任何T的父类构成的List。
    // List<?> 通配任意一种类型
    public void test2(ArrayList<? extends Number> list){

    }
}
