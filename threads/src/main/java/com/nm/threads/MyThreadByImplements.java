package com.nm.threads;

public class MyThreadByImplements implements Runnable {//实现Runnable接口

    //重写run方法
    public void run() {

    }


    public static void main(String[] args) {

        //创建并启动线程
        MyThreadByImplements myThread = new MyThreadByImplements();
        Thread thread = new Thread(myThread);
        thread.start();
        //或者    new Thread(new MyThread2()).start();

    }

}