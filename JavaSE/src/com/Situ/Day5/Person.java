package com.Situ.Day5;

//一个类不继承任何一个类，默认继承Object
public class Person/* extends Object*/{
    //希望子类继承的使用protected
    protected int id;
    protected String name;
    protected int age;
    //private List list;

    public Person() {
        super();
    }

    public Person(int id, String name, int age) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public void show() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

