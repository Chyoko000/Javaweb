package com.Situ.Day1;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

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
    @Test
    public void tiwen() {
        Scanner scanner = new Scanner(System.in);
        String lijihao = scanner.nextLine();//获得输入
        while (lijihao == "李继浩在") {
            System.out.println("提问李继浩");
        }
        System.out.println("谁也不提问");
    }

}

