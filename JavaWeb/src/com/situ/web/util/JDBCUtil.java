package com.situ.web.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtil {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;


    private JDBCUtil() {
    }

    //静态代码块，在类加载时候只会执行一次
//    static {
//        try {
//            //1.通过当前类获得类加载器
//            ClassLoader classLoader = JDBCUtil.class.getClassLoader();
//            //2.通过类加载器获取输入流
//            InputStream inputStream = classLoader.getResourceAsStream("db.properties");
//            //3.创建一个Properties对象
//            if (inputStream == null) {
//                throw new RuntimeException("无法加载 db.properties，请检查文件路径！");
//            }
//            Properties properties = new Properties();
//            properties.load(inputStream);
//            //4.根据key获取配置文件里面的value值
//            driver = properties.getProperty("driver");
//            url = properties.getProperty("url");
//            username = properties.getProperty("username");
//            password = properties.getProperty("password");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            Class.forName(driver);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }

    //自写的静态代码块测试db是否正常加载
    static {
        try {
            // 1. 通过当前类获取类加载器
            ClassLoader classLoader = JDBCUtil.class.getClassLoader();
            System.out.println("当前 classpath 路径：" + classLoader.getResource(""));

            // 2. 获取 db.properties
            InputStream inputStream = classLoader.getResourceAsStream("db.properties");

            if (inputStream == null) {
                throw new RuntimeException("ERROR: db.properties 文件未找到！");
            } else {
                System.out.println("SUCCESS: db.properties 文件已找到！");
            }

            Properties properties = new Properties();
            properties.load(inputStream);

            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");

            System.out.println("数据库配置加载成功: driver=" + driver + ", url=" + url);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



    public static Connection getConnection() throws SQLException {
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3366" +
//                "/study?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2b8",
//                "root", "1234");
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }

    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
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
//如果你使用的是 IntelliJ IDEA 内置的 Tomcat，可以这样操作：
//
//打开 IntelliJ IDEA
//点击 "Run" → "Edit Configurations"
//选择你的 Tomcat 服务器
//勾选 "Deploy applications configured in Tomcat instance"
//点击 "Clean" → "Apply"
//重新运行 Tomcat