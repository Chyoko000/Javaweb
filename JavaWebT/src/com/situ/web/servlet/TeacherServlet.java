package com.situ.web.servlet;

import com.situ.web.pojo.Teacher;
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

//extends HttpServlet
//str生成
//声明空变量
//try和catch
//构建数据库连接和命令
//写循环提取信息
//将信息加进list
//写finally进行数据流关闭
//转发到jsp
//别忘了数据库语句也要改

//String method=req.getParameter("method");
//        switch (method){}输入各个功能
//然后greate，再将代码写进里面并addthorw
//为了使第一次的页面不报空若输入的量为null直接查找数据库
//if(method==null||method.equals("")){
//            method="selectAll";
//        }
@WebServlet("/teacher")
public class TeacherServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method=req.getParameter("method");
        if (method==null||method.equals("")){
            method="selectAll";
        }switch (method){
            case"selectAll":
                selectAll(req,resp);
                break;
        }switch (method){
            case"deleteById":
                deleteById(req,resp);
                break;
        }
    }

    private void deleteById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id=req.getParameter("id");
        //id=网页请求的id
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            //建立连接
            connection = JDBCUtil.getConnection();
            //定义sql语句
            String sql = "DELETE FROM teacher WHERE id=?";
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
        resp.sendRedirect("/teacher?method=selectAll");
        //之前是重定向到student，以为之前student只负责查找现在刷新页面需要命令






    }

    private void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("teacher链接成功");
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet = null;
        List<Teacher> list = new ArrayList<>();
        try{
            connection= JDBCUtil.getConnection();
            String sql = "SELECT id,name,age,address FROM teacher";
            statement=connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {//判断下一个有没有，没有返回false，如果有返回true，并且指向下一行
                int id = resultSet.getInt("id");
                //从 ResultSet 结果集中获取 id 字段的值，并转换为 int 类型。
                String name = resultSet.getString("name");
                //获取 name 字段的值。
                int age = resultSet.getInt("age");
                String address = resultSet.getString("address");
                Teacher teacher = new Teacher(id, name, age,address);
                //创建一个 Student 对象，并使用从数据库获取的数据进行初始化。
                list.add(teacher);
                //将 student 对象添加到 list 列表中。
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtil.close(connection, statement, resultSet);
            //finally 代码块无论是否发生异常都会执行。
            //JDBCUtil.close(connection, statement, resultSet);
            // 关闭数据库连接，释放资源，防止内存泄漏。
        }
        //requst服务端请求，response回应
        //这行代码将 list 存入 request 作用域，key 为 "list"
        req.setAttribute("list",list);
        //使用 RequestDispatcher 进行转发到student_list.jsp进行访问
        req.getRequestDispatcher("teacher_list.jsp").forward(req,resp);
    }
}
