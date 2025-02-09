package com.Situ.Day9;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class JDBCDemo {
    @Test
    public void test1() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {

            //忘记数据库密码在查询里输入下列命令，，，ALTER USER 'root'@'localhost' IDENTIFIED BY 'MyNewPass123';

            //加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //连接对象Connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3366/study?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2b8",
                    "root", "1234");
            //sql语句
            String sql = "SELECT id,name,age,gender FROM student";
            //创建Statement
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            List<Student> list = new ArrayList<>();
            while (resultSet.next()) {//判断下一个有没有，没有返回false，如果有返回true，并且指向下一行
                //当前resultSet执向第一行
                //while每遍历一次，把这一行的每个字段的值拿出来，封装成Student对象
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                Student student = new Student(id, name, age, gender);
                list.add(student);
            }
            for (Student student : list) {
                System.out.println(student);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            //6、关闭连接
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Test
    public void test2() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3366/" +
                    "study?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2b8&allowPublicKeyRetrieval=true",
                    "root", "1234");
            String sql = "SELECT id,name,age,gender FROM student";
            //statement = connection.createStatement();
            statement = connection.prepareStatement(sql);
            //resultSet = statement.executeQuery(sql);
            resultSet = statement.executeQuery();
            List<Student> list = new ArrayList<>();
            while (resultSet.next()) {//判断下一个有没有，没有返回false，如果有返回true，并且指向下一行
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                Student student = new Student(id, name, age, gender);
                list.add(student);
            }
            for (Student student : list) {
                System.out.println(student);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            //6、关闭连接
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Test
    public void test3() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "SELECT id,name,age,gender FROM student";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            List<Student> list = new ArrayList<>();
            while (resultSet.next()) {//判断下一个有没有，没有返回false，如果有返回true，并且指向下一行
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                Student student = new Student(id, name, age, gender);
                list.add(student);
            }
            for (Student student : list) {
                System.out.println(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(connection, statement, resultSet);
        }
    }

    //插入
    @Test
    public void testInsert() {
        String sql = "INSERT INTO student(name, age, gender) VALUES(?, ?, ?)";
        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, "张三");
            statement.setInt(2, 23);
            statement.setString(3, "男");

            int count = statement.executeUpdate();
            System.out.println("成功插入 " + count + " 条数据！");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


//删除
    @Test
    public void testDelete() {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "DELETE FROM student WHERE id=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, 8);
            System.out.println(statement);
            int count = statement.executeUpdate();
            System.out.println("count: " + count);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(connection, statement, null);
        }
    }

    @Test
    public void testUpdate() {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "UPDATE student SET name=?,age=?,gender=? WHERE id=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, "张三");
            statement.setInt(2, 24);
            statement.setString(3, "女");
            statement.setInt(4, 1);
            System.out.println(statement);
            int count = statement.executeUpdate();
            System.out.println("count: " + count);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(connection, statement, null);
        }
    }


    //模糊查找
    @Test
    public void testLike() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "SELECT id,name,age,gender FROM student WHERE name LIKE ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, "%张%");
            resultSet = statement.executeQuery();
            List<Student> list = new ArrayList<>();
            while (resultSet.next()) {//判断下一个有没有，没有返回false，如果有返回true，并且指向下一行
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                Student student = new Student(id, name, age, gender);
                list.add(student);
            }
            for (Student student : list) {
                System.out.println(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(connection, statement, resultSet);
        }
    }

}