package com.Situ.Day9;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class  JDBCDemo {
    @Test
    public void test1() {
        //也是先声明后定义
        Connection connection = null;//负责建立Java程序和sql的链接
        Statement statement = null;//执行sql语句对象
        ResultSet resultSet = null;//存储sql查询结果
        try {
            //忘记数据库密码在查询里输入下列命令，，，
            // ALTER USER 'root'@'localhost' IDENTIFIED BY 'MyNewPass123';
            //加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //获取数据库信息
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3366/study?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2b8", "root", "1234");
            //sql语句
            String sql = "SELECT id,name,age,gender FROM student";
            //创建Statement
            statement = connection.createStatement();
            //执行查询获取结果集
            resultSet = statement.executeQuery(sql);
            //遍历结果集
            List<Student> list = new ArrayList<>();
            //Student 是列表中存储的元素类型，意味着 list 只能存放 Student 类型的对象。
            //ArrayList 是 List 接口的实现类，表示一个动态数组，可以存放多个 Student 对象。
            //new ArrayList<>() 创建了一个新的空列表，没有指定初始大小，默认容量为 10（Java 8 及以后）。
            while (resultSet.next()) {
                //指针移动：默认情况下，ResultSet 指向第一行数据之前，调用 next() 向下移动一行，
                // 如果有数据返回 true，否则返回 false。通过这个判断结束条件
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                //封装成student对象
                Student student = new Student(id, name, age, gender);
                list.add(student);//list 是一个 ArrayList<Student>，用于存储查询到的 Student 对象。
                //把当前 Student 对象添加到 list 列表中，形成一个学生对象的列表。
            }
            for (Student student : list) {//打印查询的结果
                System.out.println(student);// Java 的 增强 for 循环（for-each 循环
            }
            //for (int i = 0; i < list.size(); i++) {
            //    Student student = list.get(i);
            //    System.out.println(student);
            //}
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
            //这三个是填补问号
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
            //预编译sql语句? 是 占位符，防止 SQL 注入
            String sql = "DELETE FROM student WHERE id=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, 8);//将sql语句中的第一个占位符？替换为8
            //setXXX(index, value) 用于设置不同类型的参数：
            //setString(int index, String value)：设置字符串
            //setDouble(int index, double value)：设置小数
            //setDate(int index, java.sql.Date value)：设置日期
            System.out.println(statement);
            int count = statement.executeUpdate();
            //executeUpdate() 用于执行 INSERT、UPDATE、DELETE 语句，返回 影响的行数。
            System.out.println("count: " + count);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(connection, statement, null);//关闭链接
            //关闭 ResultSet（结果集）
            //关闭 Statement（SQL 执行对象，如 PreparedStatement）sql语句
            //关闭 Connection（数据库连接）数据库链接
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