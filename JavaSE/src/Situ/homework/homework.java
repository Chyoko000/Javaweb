package Situ.homework;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class homework {
    @Test
    //输入月份判断天数
    public void test1(){
        System.out.println("请输入月份");
        Scanner scanner =new Scanner(System.in);
        int m=scanner.nextInt();
        if (m==2){
            System.out.println("天数为28天或29天");
        }else if(m==4||m==6||m==9||m==11){
            System.out.println("30天");
        }else if(m==1||m==3||m==5||m==7||m==8||m==10||m==12){
            System.out.println("31天");
        }else{
            System.out.println("请检查输入月份");
        }
    }



    @Test//用于复制
    public void test2(){
    }


    @Test//用计算某年是不是闰年
    public void test3(){
        System.out.println("请输入您要查询的年份");
        Scanner scanner=new Scanner(System.in);
        int y= scanner.nextInt();
        if((y%4==0&&y%100!=0)||(y%400==0)){
            System.out.println("这是闰年");
        }else{
            System.out.println("这不是闰年");
        }
    }




    @Test//输入两个数然后分别打印这里两个数，然后交换这两个数字的位置
    public void test4(){

    }






//第二次作业数组
    @Test//int[] scores={0,0,1,2,3,5,4,5,2,8,7,6,9,5,4,8,3,1,0,2,4,8,7,9,5,2,1,2,3,9};
    //求出上面数组中0-9分别出现的次数
    public void tests1(){
        //x作为数字j的出现次数，i为数字下标
        int[] arr= new int[]{0,0,1,2,3,5,4,5,2,8,7,6,9,5,4,8,3,1,0,2,4,8,7,9,5,2,1,2,3,9};
        int x=0;
        for (int j=0;j<=9;j++){
            for (int i=0; i < arr.length; i++) {
                if (arr[i]==j){
                    x++;
                }
            }
            System.out.println("数字"+j+"的出现次数是"+x);//此次循环中返回了一个x值
            x=0;//把x归零使下次循环各数字的出现次数从0开始
        }


    }
    @Test
    //题目：一个任意一个字符串，判断它是不是回文。
    //"abcba"
    //char[] array = {'a' , 'b', 'c', 'b' , 'a'};
    public void tests2(){
        char[] arr= {'a' , 'b', 'c', 'b' , 'a'};
        int a=0;
        int b=arr.length-1;
        boolean Judge=false;
        //System.out.println(b);//测试b是否正确
        for(;b>=a;a++,b--){
            if (arr[a]==arr[b]){
                Judge=true;//可以通过这个寻找
            }else{
                Judge=false;
                break;//发生一个即可跳出循环
            }
        }
        System.out.println(Judge);
        Judge=false;//使判断恢复初始值

    }


}
//放假时间1月24日上完课放假，2月5号报道是要上晚自习的;
