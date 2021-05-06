package com.nm.threads;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class MyThreadByImplements2 implements Callable {//实现Callable接口

    @Override
    public Object call() throws Exception {
        return null;
    }

    public static void main(String[] args) {

        MyThreadByImplements2 myThreadByImplements2 = new MyThreadByImplements2();
        //使用Lambda表达式创建Callable对象
        //使用FutureTask类来包装Callable对象
        FutureTask<Integer> future = new FutureTask<Integer>(
                (Callable<Integer>) () -> {
                    return 5;
                }
        );

        new Thread(future, "有返回值的线程").start();//实质上还是以Callable对象来创建并启动线程
        try {
            System.out.println("子线程的返回值：" + future.get());//get()方法会阻塞，直到子线程执行结束才返回
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}