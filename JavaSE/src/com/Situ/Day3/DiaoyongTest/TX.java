package com.Situ.Day3.DiaoyongTest;

import org.junit.jupiter.api.Test;

public class TX {
    @Test
    public void test(){
        Student student = new Student();
        System.out.println(student);//听迷糊了，这里无论有参没参都会默认调用tostring
    }
}
