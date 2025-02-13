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
    private JDBCUtil() {//默认操作//工具类（只提供静态方法的类）通常都不会被实例化，例如 Math、Collections。//规范做法就是 私有化构造方法，防止 new 出对象。
    }
    static {//静态代码块，在类加载时候只会执行一次
        try {
            // 通过类加载器读取 db.properties 配置文件
            ClassLoader classLoader = JDBCUtil.class.getClassLoader();
            //输入流
            InputStream inputStream = classLoader.getResourceAsStream("db.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            //根据key获取配置文件里面的value值
            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {//加载数据库驱动
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnection() throws SQLException {//这里 硬编码 了数据库连接信息，和 db.properties 里的 url、username、password 没有直接关联。
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3366/" +
                "study?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2b8",
                "root", "1234");
        return connection;
    }
    //依次关闭 ResultSet、Statement、Connection，防止数据库连接泄漏。
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
