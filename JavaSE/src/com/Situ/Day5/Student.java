package com.Situ.Day5;

public class Student  extends Person {
    private String className;
    private int age =23;


    public Student() {
        //new Person()
        super();
    }

    //Java new子类对象的时候首先new父类对象
    public Student(int id, String name, int age, String className) {
        //new Person(id, name, age)
        super(id, name, age);
        this.className = className;
        //this.id = id;
        //this.name = name;
        //this.age = age;
    }

    public void study() {
        //'name' has private access in 'com.situ.day5.Person'
        //System.out.println(name);
        //System.out.println(getName());
        //System.out.println(className);
        System.out.println(age);
        System.out.println(super.age);
    }
}
