package com.Situ.Day8;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExceptionDemo {
    @Test
    public void test1(){
        String str ="123";
        // 将字符转为字符串后再转为数字
        //charAt(int index) 方法是 Java String 类中的一个方法，
        // 作用是从字符串中获取指定位置（索引处）的字符。
        System.out.println(str.charAt(1));
    }


    //cv
    @Test
    public void test45() {
        try {
            FileInputStream fileInputStream = new FileInputStream("a.txt");
            fileInputStream.read();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("finally");
        }

        //FileNotFoundException extends IOException
        try {
            FileInputStream fileInputStream = new FileInputStream("a.txt");
            fileInputStream.read();
        } catch (IOException e) {
            e.printStackTrace();
        } /*catch (FileNotFoundException e) {
        //Exception 'java.io.FileNotFoundException' has already been caught
        e.printStackTrace();
    }*/ finally {
            System.out.println("finally");
        }
    }


    @Test
    public void test1701(){
        try {
            FileInputStream fileInputStream=new FileInputStream("a.txt");
            fileInputStream.read();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            System.out.println("finally");

        }
    }

}
