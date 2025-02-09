package com.Situ.Day5.inter;
//extends指继承一个父类
// 使用interface来声明一个接口。
//既可以继承父类又可以实现接口
public class DaYan extends AbstracBird implements  IFly{

    @Override//确保子类正确重写了父类方法（方法签名必须匹配）
    public void fly() {
        System.out.println("DaYan.fly");//实现了一个接口具有了一个功能
    }

    void egg(){
        System.out.println("DaYan.egg");//继承了父类
    }

    public void show(){
        return;//这是自己的方法
    }
}
