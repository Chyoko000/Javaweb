package com.Situ.Day6;
//未完成，需要对照代码云完善
import org.junit.jupiter.api.Test;

public class StringDemo {
    @Test
    public void test1(){
        String str ="123";//双引号直接括起来是字符串常量
        String str1=new String("123");
        String str2=new String("123");
        System.out.println(str);
        System.out.println(str1);
        //双等号比的是地址值
        //直接赋值其内容都在常量池，再次赋值同样的他的内容也优先会在常量池里寻找，没有的话再在常量池里面创建
        //new出来的他在堆里面有自己的地址(内存)
        System.out.println(str==str1);
        System.out.println(str2==str1);
        Student student1=new Student();
        //equals是对比字符串内容
        System.out.println(str.equals(str1));
        //new的string类调用里面的equals方法；
    }



    @Test
    public void test2(){
        String str="hololw java";
        char a=str.charAt(7);//a为字符串str的下标为7的元素
        System.out.println(a);//打印出来是j
        System.out.println(str.length());//打印长度（字符的个数）
        char[]array=str.toCharArray();//将字符串转换为数组
        System.out.println(array.getClass());//查询数据的类型
        System.out.println(str.getClass());// [C 表示字符（char）数组，
        // 其中：[ 表示数组 C 表示 char 类型（基于 Java 的内部类型编码方式）
        System.out.println(array);
        System.out.println(str);
        System.out.println(str.indexOf("j"));//从左开始查找位置
        System.out.println(str.lastIndexOf("j"));//从最右开始查找位置
        //查找到对应内容后停止查找返回下标
        System.out.println(str.lastIndexOf("ol"));
        System.out.println(str.indexOf("ol"));

    }
}
