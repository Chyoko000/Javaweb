package Situ.Day3.day3;

import org.junit.jupiter.api.Test;

public class day3 {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
    @Test
    public void test1() {
        int[] array=new int [3];
        array[1]=1;
        array[2]=2;
        array[0]=4;
        System.out.println(array[1]);
        int max=getMax(array);
        System.out.println("max:"+max);
    }
    public int getMax(int[]array){
        int max=array[0];
        for(int i=0;i<array.length;i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
            return max;
    }
    @Test
    public void test2(){
        int[] soure ={1,2,3,4,5,};
        int[] dest= new int[5];
        System.arraycopy(soure,0,dest,0,soure.length);
        for (int i = 0; i < dest.length; i++) {
            System.out.println(dest[i]);
        }
    }
    @Test
    public void test3(){
        int[] source = {1, 2, 3, 4, 5};
        int[] array = source.clone();
        for (int num : array) {
            System.out.print(num + " ");
        }
    }
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
                System.out.println(array[i][j]);
            }
        }
    }

}