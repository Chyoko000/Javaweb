package Situ.Day4;

import com.Situ.Day5.Person;
//继承要用extends声明，extends是扩展的意思
//继承之后也访问不到父类依旧要用get方法调用，此时就要改变父类里面的访问修饰符
public class Student extends Person {
//    private int id;
//    private String name;
//    private int age;
    private String className;
//快捷键alt+insert
//get方法
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getClassName() {
        return className;
    }
    //无参select none

    public Student() {
    }
    //构造canstructor
    public Student(int id, String name, int age, String className) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.className = className;
    }
}
