package com.Situ.practice;

import java.util.Scanner;

public class AandB {
    public static void main(String[] args) {
//        System.out.println("请分别输入两个数字");
        Scanner scanner= new Scanner(System.in);
        int a =scanner.nextInt();
        int b =scanner.nextInt();//使用空格间隔
//        Scanner scanner1=new Scanner(System.in);//回车一次输入一次
//        int b=scanner.nextInt();
        int c= a +b;
        System.out.println(c);
        scanner.close();//防止资源泄露
        //测试
    }
}
