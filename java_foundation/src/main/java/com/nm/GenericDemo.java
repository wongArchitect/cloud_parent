package com.nm;

import com.nm.dom.Generic;

public class GenericDemo {

    public static void main(String[] args) {
        // TODO 自动生成的方法存根
        //定义泛型Generic的一个Integer的版本
        Generic<Integer> intob = new Generic<Integer>(88);
        intob.showType();
        int i = intob.getOb();
        System.out.println("value=" + i);
        System.out.println("----------------------");
        //定义泛型Generic的一个String版本
        Generic<String> strob = new Generic<String>("hello");
        strob.showType();
        String s = strob.getOb();
        System.out.println("value=" + s);
    }
}