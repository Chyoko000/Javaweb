package com.situ.web.servlet;

//导入 Java 标准库、第三方库或自定义包中的类。
import com.situ.web.pojo.Student;
import com.situ.web.util.JDBCUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//导入tomcat的工具包才能用HttpServlet
//浏览器可以直接访问jsp网址Servlet
@WebServlet("/student")
public class StudentServlet extends HttpServlet {
    //访问servlet默认访问service方法直接输入ser就能直接打出后面的
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("连接成功");
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Student> list = new ArrayList<>();
        try {
            connection = JDBCUtil.getConnection();
            String sql = "SELECT id,name,age,gender FROM student";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
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

        //requst服务端请求，response回应
        //这行代码将 list 存入 request 作用域，key 为 "list"
        req.setAttribute("list",list);
        //转发到student_list.jsp进行访问
        req.getRequestDispatcher("student_list.jsp").forward(req,resp);
    }

}
