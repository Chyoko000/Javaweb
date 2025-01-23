package Situ.Day2;

import org.junit.jupiter.api.Test;

public class A {
    @Test
    public void test55() {
        int num1 = 13;
        int num2 = 5;
        int max = 0;
        if (num1 >= num2) {
            max = num1;
        } else {
            max = num2;
        }
        System.out.println(max);

        int max1 = num1 >= num2 ? num1 : num2;
        //整型max1等于   判断num1和num2的大小ture则为赋值左边，false则赋值右边
        System.out.println(max1);
    }
}
