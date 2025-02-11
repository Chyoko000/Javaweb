package com.Situ.Day9;

import org.junit.Test;

import java.io.*;
import java.util.Arrays;

public class IODemo {

    @Test
    public void test1() {
        try {
            FileReader fileReader = new FileReader("io.txt");
            int ch1 = fileReader.read();
            System.out.println((char) ch1);//a
            int ch2 = fileReader.read();
            System.out.println((char) ch2);//b
            int ch3 = fileReader.read();
            System.out.println((char) ch3);//c
            int ch4 = fileReader.read();
            System.out.println(ch4);//-1
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        try {
            FileReader fileReader = new FileReader("io.txt");
            int ch = -1;
            while ((ch = fileReader.read()) != -1) {
                System.out.println((char)ch);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test3() {
        try {
            FileReader fileReader = new FileReader("io.txt");
            char[] buffer = new char[10];
            int length = -1;
            //length = fileReader.read(buffer);
            //System.out.println(length);//10
            //length = fileReader.read(buffer);
            //System.out.println(length);//10
            //length = fileReader.read(buffer);
            //System.out.println(length);//6
            //length = fileReader.read(buffer);
            //System.out.println(length);//-1

            while ((length = fileReader.read(buffer)) != -1) {
                System.out.println(length);
                System.out.println(Arrays.toString(buffer));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test4() {
        //声明两个变量并初始化，字符输入流和字符输出流
        FileReader fileReader = null;
        //如果 fileReader 和 fileWriter 只在 try 块内部定义，
        // 那么 finally 块中无法访问它们，会导致编译错误：
        FileWriter fileWriter = null;
        try {
            fileReader = new FileReader("io.txt");
            fileWriter = new FileWriter("io_back.txt");
//            new FileReader("io.txt")：打开 io.txt 文件，准备读取内容。
//            new FileWriter("io_back.txt")：创建或覆盖 io_back.txt，用于写入数据
            char[] buffer = new char[10];//定义一个长度为10的数组（缓冲区）
            int length = -1;//用于存储实际存储到的字符
            while ((length = fileReader.read(buffer)) != -1) {//fileReader.read(buffer)每次读取十个字符
                //length直接跳过0，为空的时候会返回-1
                System.out.println(length);//输出这次循环获得的字符数
                //可以设置一个条件比如length小于10
                System.out.println(Arrays.toString(buffer));//打印整个buffer数组
                //fileWriter.write(buffer);
                fileWriter.write(buffer, 0, length);//缓冲区 写入的起始索引，实际读取的字符数
                //这里length的值是取自循环，内容会复写
                //10[d, a, i, d, a, i, d, a, s, h]
                //6[i, x, i, o, n, g, d, a, s, h]//io为daidaidashixiong
            }
        }
        /*catch (IOException e) {
            e.printStackTrace();
        }*/
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        catch (IOException e) {
            throw new RuntimeException(e);
        }////处理所有的io异常

        //FileWriter 默认使用缓冲机制，数据不会立即写入文件，而是先存入缓冲区。
        // 只有当缓冲区满了、手动 flush()，或 close() 方法被调用时，
        // 数据才会被真正写入文件。不然数据仍在缓冲区
        finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Test//练习
    public void testio(){
        FileReader a=null;
        FileWriter b=null;//声明
        try{
            a=new FileReader("io.txt");
            b=new FileWriter("io_t.txt");//定义
            char[]buffer=new char[10];
            int lenght=-1;
            while((lenght=a.read(buffer))!=-1){
                System.out.println(lenght);
                System.out.println(Arrays.toString(buffer));
                b.write(buffer,0,lenght);
            }
        } catch (FileNotFoundException e) {//处理异常
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {//
            if (b != null) {
                try {
                    b.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (a != null) {
                try {
                    a.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    //字节流这个跟上边同理
    @Test
    public void test45() {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream("640.jpg");
            fileOutputStream = new FileOutputStream("640_back.jpg");
            byte[] buffer = new byte[9999999];
            int length = -1;
            while ((length = fileInputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, length);//不打印二是一次性生成
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }



    //对象流
    @Test
    public void testObjectOutputStream() {
        Student student = new Student();
        student.setId(1);
        student.setName("zhangsan");
        student.setAge(23);
        student.setGender("男");

        ObjectOutputStream objectOutputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream("student");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(student);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Test
    public void testObjectInputStream() {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream("student");
            objectInputStream = new ObjectInputStream(fileInputStream);
            Student student = (Student) objectInputStream.readObject();
            System.out.println(student);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
