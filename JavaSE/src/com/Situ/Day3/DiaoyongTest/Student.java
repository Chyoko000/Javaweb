package com.Situ.Day3.DiaoyongTest;

public class Student {
    private int id;
    private String name;
    private int age;
    private String gender;


    public Student() {
        System.out.println("Student.Student");
    }

    @Override
    public String toString() {
        return "student{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", age=" + age +
                '}'+"测试";
    }
}
