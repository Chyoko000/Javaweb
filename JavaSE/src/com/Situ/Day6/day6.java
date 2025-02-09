package com.Situ.Day6;

import org.junit.jupiter.api.Test;

import java.util.*;

public class day6 {
    //数值对比
    @Test
    public void test1(){
//双等号是地址值的对比
        String str ="123";//相当于在常量池里面new一个123的字符串
        String str1 =new String("123");//在堆里面new一个123的字符串
        String str2 ="123";//优先使用常量池里面的地址值
        String str3=new String("123");//再次尝试在栈里面new一个新的字符串
//        System.out.println(str==str1);
//        System.out.println(str1==str2);
//        System.out.println(str2==str);
        System.out.println(str2==str1);//在栈里无论new多少个他都不一样
        //equals是对比数值的
        System.out.println(str1.equals(str2));
    }

    //查找
    @Test
    public void test2() {
        String str = "java AndroidA";
        System.out.println(str.length());//13
//        char[] chars = str.toCharArray();//此时没用，toCharArray() 方法将字符串转换为 char 数组：
        char ch = str.charAt(5);//A
        System.out.println(ch);
        System.out.println(str.indexOf('A'));//5返回下标
        System.out.println(str.indexOf('X'));//-1 找不到返回-1
        System.out.println(str.indexOf('A', 6));//12，直接从6开始查找
        System.out.println(str.indexOf("And"));//5
        System.out.println(str.lastIndexOf("And"));//5
        System.out.println(str.lastIndexOf('A'));//12
        System.out.println(str.indexOf("A",6));
        //正向搜索从下标6开始
        //从0开始包括空格
    }

//对比
    @Test
    public void test3(){
        //String str1=new String();
        String str1="java";
        String str2="Java";
        String str3="";
        System.out.println(str1.equals(str2));//equals对比的是内容是否一样2
        System.out.println(str1.equalsIgnoreCase(str2));//不考虑大小写
        System.out.println(str2.startsWith("j"));//判断开头是否为j
        System.out.println(str2.startsWith("a",1));//判断下标1是否为a
        System.out.println(str2.endsWith("a"));//判断结尾是否为a
        System.out.println(str3.isEmpty());//只有对应字符串为空时才返回f其他时候返回t

    }



    //转换
    @Test
    public void testlz() {
        char[] t = {'1', '2', '3'}; // 必须用单引号表示字符
        String str = new String(t); // 使用字符数组构造字符串
        char[] a = str.toCharArray(); // 将字符串转换回字符数组
        //两者在查找时会出现差别
        System.out.println(t); // 输出字符数组 t
        System.out.println(a); // 输出字符数组 a
    }

    //替换将A替换为B
    @Test
    public void test112(){
        String str1="java JAVA";
        String A= str1.replace('A','B');
        System.out.println(str1);//并没有改变原字符串
        System.out.println(A);//JBVB
    }


    @Test//以括号里的为分隔符形成一个新数组
    public void test101(){
        String str="java android python";
        String[] arr=str.split(" ");
        String[] arr2=str.split("a");
        System.out.println(Arrays.toString(arr));//将数组 arr 转换为一个格式化的字符串
        System.out.println(Arrays.toString(arr2));
    }

    @Test//切割
    public void test911(){
        String str="java android python";
        System.out.println(str.substring(5));//android python
        System.out.println(str.substring(5,8));//and
        //以括号里的为下标进行切割
        //下标是点故此地址值不包括后边的
    }


    @Test//去掉前后空格
    public void test912(){
        String str="     java android python     ";
        String str2=str.trim();
        System.out.println(str);
        System.out.println(str2);
    }




    @Test
    public void testa(){
        int num=3;
        System.out.println(num);//输出为3
//实际上是下边的形式只不过默认引用了；
        Integer integer=Integer.valueOf(4);
        System.out.println(integer);

        Integer integer1=3;
        System.out.println(integer1);

        int i=integer.intValue();
        System.out.println(i);

//        int num=integer1;
//        System.out.println(num);
    }



    @Test
    public void test9(){
        String str="23";
        String str2="3.14";
        int Put1;
        Put1=Integer.parseInt(str);//转换为int，涉及到装箱和拆箱parse（解析）
        System.out.println(Put1);
        double Put2=Double.parseDouble(str2);//转换为double类型
        System.out.println(Put1);
        //应用场景为传回字符串然后进行解析
        //同理Boolean也行；
    }

//Day7
    @Test
    public void testb(){
        Student[] array=new Student[3];
        //长度固定所以初始化要进行如上操作。
        //只能存放Student类的数据
        int[] arr=new int[3];
        arr[1]=1;
        //这种情况才可以输入int类


        //这是模板，下边是代码
        ArrayList<Student>list=new ArrayList<>();//arrraylist插入和输出一样
        Student student1=new Student();
        Student student2=new Student();
        Student student3=new Student();
        Student student4=new Student();
        array[1]=student1;//array这个只能放这个类
        list.add(student1);
        list.add(student2);
        list.add(student3);
        list.add(student4);

        //有序可重复
        //有序：你放进去的顺序和拿出来的顺序一致
        //ArrayList<String> list1 = new ArrayList<>();
        List<String> list1 = new ArrayList<>();//两种写法都可以
        list1.add("Java");
        list1.add("UI");
        list1.add("H5");
        list1.add("H5");//相同的不会被吃掉
        list1.add("aa");
        for (String str : list1) {
            System.out.println(str);
        }

        System.out.println("-----有序和无序的分界线-----");
        //无序不重复
        //无序：放进去顺序和拿出来的顺序可能是不一致的
        //HashSet<String> set = new HashSet<String>();
        Set<String> set = new HashSet<>();//同理
        set.add("Java");
        set.add("UI");
        set.add("H5");
        set.add("H5");//相同的会被吃掉
        set.add("aa");
        for (String str : set) {
            System.out.println(str);
        }
    }

//无序map
    @Test
    public void test45(){
        HashMap<String, String> map = new HashMap<>();//无序
        map.put("cn", "中国");
        map.put("us", "美国");
        map.put("uk", "英国");
        Set<Map.Entry<String, String>> entrySet = map.entrySet();
        //map.entrySet() 方法返回一个 包含 Map 所有键值对 (key-value) 的 Set 集合，
        // 其中每个元素都是 Map.Entry<K, V> 类型的对象。
        for (Map.Entry<String, String> entry : entrySet) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }//通过entry打印
        System.out.println("----------------");
        //通过key来打印
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            System.out.println(key + " : " + map.get(key));
        }
        //笔记11那里讲的很清楚
        System.out.println("------------");
        String value = map.remove("uk");
        //map里面k为uk的v
        System.out.println(value);

    }



}
