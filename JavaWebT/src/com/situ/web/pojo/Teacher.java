package com.situ.web.pojo;

import java.io.Serializable;
//implements Serializable
//选择 Constructor 生成构造方法。
//选择 Getter/Setter 生成 get/set 方法。
//Alt + Insert（Windows/Linux）或 Command + N（Mac），选择 toString()。
//List<Teacher> list = new ArrayList<>();
public class Teacher implements Serializable {
    private Integer id;
    private String name;
    private Integer age;
    private String address;
    //选择 Constructor 生成构造方法。
    //选择 Getter/Setter 生成 get/set 方法。
    //Alt + Insert（Windows/Linux）或 Command + N（Mac），选择 toString()。
    public Teacher(Integer id, String name, Integer age, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }

    public Teacher(){
    }



}
