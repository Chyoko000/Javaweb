package com.Situ.Day9;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.Statement;

public class JDBCUtil {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;


    private JDBCUtil() {
    }

    //静态代码块，在类加载时候只会执行一次
    static {
        try {
            //通过当前类获得类加载器
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


        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3366/" +
                "study?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2b8",
                "root", "1234");
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
