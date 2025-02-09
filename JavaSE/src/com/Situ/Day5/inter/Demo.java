package com.Situ.Day5.inter;

public class Demo {
    public static void main(String[] args){
        DaYan dayan=new DaYan();
        dayan.fly();
        //站在能不能飞的接口来看
        IFly fly=new DaYan();
        fly.fly();
    }
}
