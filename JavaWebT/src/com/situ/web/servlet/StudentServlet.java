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
//继承 HttpServlet，表示它是一个 Servlet，可以处理 HTTP 请求。
@WebServlet("/student")
public class StudentServlet extends HttpServlet {
    //访问servlet默认访问service方法直接输入ser就能直接打出后面的
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //service() 方法是 HttpServlet 提供的默认方法，它会自动调用 doGet() 或 doPost()，
        // 适用于同时处理 GET 和 POST 请求。
        //req（HttpServletRequest）：用于获取请求数据，比如表单参数、URL 参数等。
        //resp（HttpServletResponse）：用于返回响应数据，比如 HTML、JSON 或跳转页面。
        System.out.println("连接成功");
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Student> list = new ArrayList<>();
        //创建一个 ArrayList，用于存放从数据库查询出来的 Student 对象。
        try {
            connection = JDBCUtil.getConnection();
            //通过jdbc链接数据库
            String sql = "SELECT id,name,age,gender FROM student";
            //编写数据库语句
            statement = connection.prepareStatement(sql);
            //执行预编译的数据库命令
            resultSet = statement.executeQuery();
            //执行 SQL 查询，返回 ResultSet 结果集。服务于这个循环
            while (resultSet.next()) {//判断下一个有没有，没有返回false，如果有返回true，并且指向下一行
                int id = resultSet.getInt("id");
                //从 ResultSet 结果集中获取 id 字段的值，并转换为 int 类型。
                String name = resultSet.getString("name");
                //获取 name 字段的值。
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                Student student = new Student(id, name, age, gender);
                //创建一个 Student 对象，并使用从数据库获取的数据进行初始化。
                list.add(student);
                //将 student 对象添加到 list 列表中。
            }
            for (Student student : list) {
                System.out.println(student);
                //遍历 list 并打印每个 Student 对象，方便调试。
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(connection, statement, resultSet);
            //finally 代码块无论是否发生异常都会执行。
            //JDBCUtil.close(connection, statement, resultSet);
            // 关闭数据库连接，释放资源，防止内存泄漏。
        }

        //requst服务端请求，response回应
        //这行代码将 list 存入 request 作用域，key 为 "list"
        req.setAttribute("list",list);
        //使用 RequestDispatcher 进行转发到student_list.jsp进行访问
        req.getRequestDispatcher("student_list.jsp").forward(req,resp);
    }

}
