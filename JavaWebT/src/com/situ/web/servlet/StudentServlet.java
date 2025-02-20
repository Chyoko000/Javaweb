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
        String method=req.getParameter("method");
        if(method==null||method.equals("")){
            method="selectAll";
        }
        switch (method){
            case"selectAll":
                selectAll(req,resp);
                //参数添加完毕之后greate
                break;
            case "deleteById":
                deleteById(req,resp);
                break;
            case "add":
                add(req,resp);
                break;
        }
        System.out.println("连接成功");

    }//这里多删除了一个大括号

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name=req.getParameter("name");
        String age=req.getParameter("age");
        String gender=req.getParameter("gender");
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection=JDBCUtil.getConnection();
            String sql = "INSERT INTO student(name, age, gender) VALUES(?, ?, ?)";
            statement = connection.prepareStatement(sql);
            //设置 SQL 语句中的第 1 个占位符 ? 的值，即要删除的 id。
            //这里后边的改完了前边也要改
            statement.setString(1, name);
            statement.setInt(2, Integer.parseInt(age));
            statement.setString(3, gender);

            //执行 SQL 语句
            statement.executeUpdate(); //
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            // 6. 关闭资源
            JDBCUtil.close(connection, statement, null);
        }

        // 7. 数据添加成功后，重定向到学生列表页面
        resp.sendRedirect("/student?method=selectAll");
    }

    private void deleteById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //定义内部需求的数值
        String id=req.getParameter("id");
        //id=网页请求的id
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            //建立连接
            connection = JDBCUtil.getConnection();
            //定义sql语句
            String sql = "DELETE FROM student WHERE id=?";
            // 预编译 SQL 语句，返回 PreparedStatement 对象。
            statement = connection.prepareStatement(sql);
            //设置 SQL 语句中的第 1 个占位符 ? 的值，即要删除的 id。
            statement.setInt(1, Integer.parseInt(id));
            //执行 SQL 语句
            statement.executeUpdate(); // 执行删除操作！！！
            //将sql语句中的第一个占位符？替换
            //将id转化为整数
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(connection, statement, null);//关闭链接
            //关闭 ResultSet（结果集）
            //关闭 Statement（SQL 执行对象，如 PreparedStatement）sql语句
            //关闭 Connection（数据库连接）数据库链接
        }
        resp.sendRedirect("/student?method=selectAll");
        //之前是重定向到student，以为之前student只负责查找现在刷新页面需要命令
    }

    private void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        } catch (NumberFormatException e) {
            return;
        }
        catch (SQLException e) {
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





//备用
//        Connection connection = null;
//        PreparedStatement statement = null;
//        try {
//        String id=req.getParameter("id");
//        String name=req.getParameter("name");
//        String age=req.getParameter("age");
//        String gender=req.getParameter("gender");
//        connection = JDBCUtil.getConnection();
//        String sql = "UPDATE student SET name=?,age=?,gender=? WHERE id=?";
//        statement = connection.prepareStatement(sql);
//            statement.setString(1, name);
//            statement.setInt(2, Integer.parseInt(age));
//        statement.setString(3,gender);
//            statement.setInt(4, Integer.parseInt(id));
//        statement.executeUpdate();
//        } catch (SQLException e) {
//        throw new RuntimeException(e);
//        } finally {
//                JDBCUtil.close(connection, statement, null);
//        }
//                resp.sendRedirect("/student?method=selectAll");