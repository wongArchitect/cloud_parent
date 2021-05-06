package com.nm.threads;

public class MyThreadByExtends extends Thread {//继承Thread类{

    //重写run方法
    public void run() {

    }

    public static void main(String[] args) {

        new MyThreadByExtends().start(); //创建并启动线程

    }

}