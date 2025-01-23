package Situ.Day9;

import org.junit.jupiter.api.Test;

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
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        try {
            fileReader = new FileReader("io.txt");
            fileWriter = new FileWriter("io_back.txt");
            char[] buffer = new char[10];
            int length = -1;
            while ((length = fileReader.read(buffer)) != -1) {
                System.out.println(length);
                System.out.println(Arrays.toString(buffer));
                //fileWriter.write(buffer);
                fileWriter.write(buffer, 0, length);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally { //关闭流
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

    @Test
    public void test45() {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream("bd.png");
            fileOutputStream = new FileOutputStream("bd_back.png");
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = fileInputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, length);
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
            fileOutputStream = new FileOutputStream("stu");
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
            fileInputStream = new FileInputStream("stu");
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
