package Situ.ShiYan.HangZh;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class sacnAndprint {
    @Test
    public void test1701(){
        Scanner scanner=new Scanner(System.in);
        int a=scanner.nextInt();
        Scanner scanner1=new Scanner(System.in);
        char B=scanner.next().charAt(0);//读取单个字符
        System.out.println(a);
        System.out.println(B);
    }

}
