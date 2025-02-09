package com.Situ.Day3.LeideFengzhuangD;
//此为类的内部
public class Student {
    public String name;
    int id;//只有类的内部可以访问外部无法访问，若用这个则STest就会报错，因为无法读取id
    private  int age;


//get和set
    //此时为了获取id可以通过get和set的方法间接获取信息
    public  void setAge (int age){//方法的参数必须指定类型  void不会返回值只是会进行操作
        //id=id;//错误的因为就近原则导致；两就会形成自己给自己赋值的现象
        if(age>0&age<999999){
            this.age=age;//前为类里面的id，后边的为进来的参数
        }
        return;//抛出异常
    }
    //方法签名         //括号里是参数
    public int getAge(){//返回的值为int类型//方法体
        return this.age;
    }
//同理也可以用快速生成



//拼接
    public String getInfo(){
        String str=(this.id+name);
        return str;
    }
    //快速生成如上函数右键generate-->tostring
//    @Override
//    public String toString() {
//        return "Student{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                '}';
//    }
}
