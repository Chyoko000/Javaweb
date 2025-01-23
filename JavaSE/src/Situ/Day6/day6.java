package Situ.Day6;

import org.junit.jupiter.api.Test;

import java.util.*;

public class day6 {
    @Test
    public void test1(){

        String str ="123";//相当于在常量池里面new一个123的字符串
        String str1 =new String("123");//在堆里面new一个123的字符串
        String str2 ="123";//优先使用常量池里面的地址值
        System.out.println(str==str1);
        System.out.println(str1==str2);
        System.out.println(str2==str);
    }
    @Test
    public void test2() {
        String str = "java AndroidA";
        System.out.println(str.length());//13
        char[] chars = str.toCharArray();
        char ch = str.charAt(5);//A
        System.out.println(ch);
        System.out.println(str.indexOf('A'));//5返回下标
        System.out.println(str.indexOf('X'));//-1 找不到返回-1
        System.out.println(str.indexOf('A', 6));//12，直接从6开始查找
        System.out.println(str.indexOf("And"));//5
        System.out.println(str.lastIndexOf('A'));//12
        //从0开始包括空格
    }


    @Test
    public void test3(){
        String str1="java";
        String str2="Java";
        System.out.println(str1.equals(str2));//equals对比的是内容是否一样2

    }


    @Test
    public void testa(){
        Integer integer=Integer.valueOf(4);
        System.out.println(integer);

        Integer integer1=3;
        System.out.println(integer1);

        int i=integer.intValue();
        System.out.println(i);

        int num=integer1;
        System.out.println(num);
        //
    }


    @Test
    public void testb(){
        Student[] array=new Student[3];
        //长度固定所以初始化要进行如上操作。

        ArrayList<Student>list=new ArrayList<>();
        Student student1=new Student();
        Student student2=new Student();
        Student student3=new Student();
        Student student4=new Student();
        list.add(student1);
        list.add(student2);
        list.add(student3);
        list.add(student4);

        //有序可重复
        //有序：你放进去的顺序和拿出来的顺序一致
        //ArrayList<String> list1 = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        list1.add("Java");
        list1.add("UI");
        list1.add("H5");
        list1.add("H5");
        list1.add("aa");
        for (String str : list1) {
            System.out.println(str);
        }

        System.out.println("-----有序和无序的分界线-----");
        //无序不重复
        //无序：放进去顺序和拿出来的顺序可能是不一致的
        //HashSet<String> set = new HashSet<String>();
        Set<String> set = new HashSet<>();
        set.add("Java");
        set.add("UI");
        set.add("H5");
        set.add("H5");//相同的会被吃掉
        set.add("aa");
        for (String str : set) {
            System.out.println(str);
        }
    }




}
