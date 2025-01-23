package Situ.Day3.LeideFengzhuangD;

import org.junit.jupiter.api.Test;

public class  STest {
    @Test
    public void test1(){
        Student[] stu = new Student[3];//创建一个长度为3名字为stu的数组
        System.out.println(stu);
        //[Lcom.Situ.ShiYan.Student;@4e4aea35报错出现的为该数组对象的内存地址
        //System.out.println(stu.id);//报错原因为数组本身不包含属性id只有student对象包含id属性名


        //初始化数组中的元素
        stu[0]=new Student();//不需要再声明类型，只需要new一个对象就行，而且new的对象也必须变量同一类型名
        stu[0].name="李白";
        stu[0].id=114514;
        System.out.println(stu[0].id);
        //采用set和get方法时一定要用已经生命好的元素，不然真的会报错的哭
        stu[0].setAge(25);//不能用stu[0].id=114514此时id无法调用是报错状态

        System.out.println(stu[0].getAge());



        //创建了三个数组（长度），stu[0],stu[1],stu[2],每个数组仍需创建student对象
        //该方法的优越性，延迟初始化统一管理以及不用再次声明变量
        //打印全部内容
        System.out.println(stu[0].getInfo());//调用父类里面的方法
    }
}
