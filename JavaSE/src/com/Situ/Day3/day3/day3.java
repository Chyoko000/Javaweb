package com.Situ.Day3.day3;

import org.junit.jupiter.api.Test;

public class day3 {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }

    //找到数组中的最大值
    @Test
    public void test1() {
        int[] arr=new int [3];//建立一个长度为3而不是下标为3的数组
        //为数组里的每个数赋值
        arr[1]=1;
        arr[2]=2;
        arr[0]=4;
        int[]array=arr;
        //System.out.println(array[1]);//验证结果为1
        int max=getMax(arr);//定义一个变量它的值调用getmax函数,参数是整个数组
        System.out.println("max:"+max);
    }
    public int getMax(int[]array){//即int[]array=arr
        int max=array[0];
        for(int i=0;i<array.length;i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
            return max;
    }

    //arraycopy的用法
    @Test
    public void test2(){
        int[] soure ={1,2,3,4,5,};
        int[] dest= new int[5];
        System.arraycopy(soure,0,dest,0,soure.length);
        //来源数组，位置，目标数组，位置，长度（soure.length即数组的长度）
        for (int i = 0; i < dest.length; i++) {
            System.out.println(dest[i]);
        }
    }

//真服了自己写的代码自己全忘了
//直接复制一个数组
    @Test
    public void test3(){
        int[] source = {1, 2, 3, 4, 5};
        int[] array = source.clone();//直接复制一个数组
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
        }
        for (int num : array) {
            System.out.print(num + " ");//int num：每次循环时从 array 数组中取出的元素，赋值给 num 变量。
        }
    }

    //手动遍历复制
    @Test
    public void test5(){
        int[] source = {1, 2, 3, 4, 5};
        int[] dest = new int[source.length];

        for (int i = 0; i < source.length; i++) {
            dest[i] = source[i];
        }

        for (int num : dest) {
            System.out.print(num + " ");
        }
    }

//定义一个二维数组
    @Test
    public void test4(){
        int[][]array=new int[3][];
        array[0]=new int [2];
        array[1]=new int[2];
        array[2]=new int[3];
        array[0][0]=23;
        array[0][1]=12;
        array[1][0]=26;
        array[1][1]=28;
        array[2][0]=29;
        array[2][1]=20;
        array[2][1]=12;
        array[2][2]=90;
        for(int i=0;i<array.length;i++){
            for(int j=0;j<array[i].length;j++){
                System.out.print(array[i][j]+" ");//23 12 26 28 29 12 90
            }
        }
    }

}