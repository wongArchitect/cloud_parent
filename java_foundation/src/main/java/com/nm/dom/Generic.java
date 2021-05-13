package com.nm.dom;

public class Generic<T> {
    private T ob; //定义泛型成员变量
    public Generic(T ob) {
        this.ob = ob;
    }
    public T getOb() {
        return ob;
    }
    public void setOb(T ob) {
        this.ob = ob;
    }
    public void showType() {
        System.out.println("实例类型为:" + ob.getClass().getName());
    }
}
