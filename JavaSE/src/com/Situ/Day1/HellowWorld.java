package com.Situ.Day1;

import org.junit.jupiter.api.Test;

public class HellowWorld {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
    @Test
    public void test(){
        int a=11;
        int b=77;
        boolean c=(a>=7)&&(b<=80);
        System.out.println(c);
        //||短路
        System.out.println(a > 0 || a++ < b);
    }
}

