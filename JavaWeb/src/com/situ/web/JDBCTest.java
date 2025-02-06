package com.situ.web;

public class JDBCTest {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL JDBC 驱动加载成功！");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC 驱动加载失败！");
            e.printStackTrace();
        }
    }
}
