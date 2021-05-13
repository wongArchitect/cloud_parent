package com.nm;

import com.nm.dom.BoundGeneric;

public class BoundGenericDemo {

    public static void main(String[] args) {
        // TODO 自动生成的方法存根
        //使用整形数组构造泛型对象
        Integer[] intArray = {1, 2, 3, 4};
        BoundGeneric<Integer> iobj = new BoundGeneric<Integer>(intArray);
        System.out.println("iobj的和为:" + iobj.sum());

        //使用Double型数组构造泛型对象
        Double[] douArray = {1.2, 2.3, 3.4, 4.5};
        BoundGeneric<Double> dobj = new BoundGeneric<Double>(douArray);
        System.out.println("dobj的和为:" + dobj.sum());

        String[] strArray = {"str1","str2"};
        //下面的语句将会报错,String不是Number的子类
        //BoundGeneric<String> sobj = new BoundGeneric<String>(strArray);
    }
}