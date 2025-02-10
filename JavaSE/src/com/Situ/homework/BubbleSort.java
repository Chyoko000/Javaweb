
package com.Situ.homework;
//冒泡函数
public class BubbleSort {



    //主函数
    //String[] args 是 Java 程序的入口方法所必须的。它允许程序从命令行接收参数，
    // 但如果不需要，可以留空，不影响程序运行。如果不定义为 String[] args，JVM 将无法识别主方法。
    public static void main(String[] args){
        int[] arr={64, 34, 25, 12, 22, 11, 90};
        System.out.print("未进行冒泡排序时数组的顺序:");
        printarr(arr);
        System.out.print("进行冒泡排序之后的数组顺序:");
        bubblesort(arr);
    }


    //打印函数
    public static void printarr(int[] arr){
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");//print是不换行
        }
        System.out.println();//println是默认换行
    }


    //算法函数
    public static void bubblesort(int[] arr){
        //每次都要从零开始
        for (int j = 1; j < arr.length; j++) {
            for(int i=0;i<arr.length-1;i++){//应为小于最后一位-1即最后一位-2，但是不能用==，用等于相当于没进行，这是能运行的范围。
            //for (int i = 0; i < arr.length - 1 - j; i++){//可以优化一下，降低内循环次数。
                if(arr[i]>arr[i+1]){//交换
                    int temp=arr[i];
                    arr[i]=arr[i+1];
                    arr[i+1]=temp;
                }
            }
        }
        printarr(arr);
    }
}
//双重for循环，先用第一个元素和他后一个元素进行比较，谁比较大就放在后面，（若前者比较大就通过temp进行交换），
// 若后者大则不需要交换，循环这个过程，直到找到最大的放在最后一位
//第二重循环是由于要寻找倒数第二大的，这个过程要持续arr.length-1次