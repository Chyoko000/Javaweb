package Situ.ShiYan.HangZh;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class sacnAndprint {
    @Test
    public void test1701(){
        Scanner scanner=new Scanner(System.in);
        //这一行代码的作用是创建一个 Scanner 对象，用于从标准输入（键盘）读取数据。
        //Scanner 可以使用 nextInt() 读取整数、nextDouble() 读取小数、next()
        // 读取单个单词、nextLine() 读取整行文本等。
        int a=scanner.nextInt();//调用对象里面的方法
        int c=scanner.nextInt();
        //Scanner scanner1=new Scanner(System.in);
        char B=scanner.next().charAt(0);//读取单个字符
        System.out.println(a);
        System.out.println(c);
        System.out.println(B);
    }

}
