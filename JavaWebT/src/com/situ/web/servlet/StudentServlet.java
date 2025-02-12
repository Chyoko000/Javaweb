package com.situ.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//导入tomcat的工具包才能用HttpServlet
//浏览器可以直接访问jsp网址Servlet
@WebServlet("/student")
public class StudentServlet extends HttpServlet {
    //访问servlet默认访问service方法直接输入ser就能直接打出后面的
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("连接成功");
    }
}
