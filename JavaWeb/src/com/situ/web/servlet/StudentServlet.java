package com.situ.web.servlet;

import com.situ.web.dao.IBanjiDao;
import com.situ.web.dao.impl.BanjiDaoImpl;
import com.situ.web.pojo.Student;
import com.situ.web.pojo.User;
import com.situ.web.util.JDBCUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



@WebServlet("/student")
public class StudentServlet extends HttpServlet {
    private IBanjiDao banjiDao = new BanjiDaoImpl();

    //访问Servlet默认访问service方法
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("StudentServlet.service");
        req.setCharacterEncoding("UTF-8");
        // http://localhost:8080/JavaWeb/student?method=selectAll
        // http://localhost:8080/JavaWeb/student?method=deleteById&id=1
        // http://localhost:8080/JavaWeb/student?method=add


        //过滤器
//        HttpSession session = req.getSession();
//        User user = (User) session.getAttribute("user");
//        if (user == null) {
//            resp.sendRedirect("/login.jsp");
//            return;
//        }//如果没登陆就会重定向到登陆界面


        String method = req.getParameter("method");
        if (method == null || method.equals("")) {
            method = "selectAll";
        }
        switch (method) {
            case "selectAll":
                selectAll(req, resp);
                break;
            case "deleteById":
                deleteById(req, resp);
                break;
            case "add":
                add(req, resp);
                break;
            case "toStudentAdd":
                toStudentAdd(req, resp);
                break;
            case "toStudentUpdate":
                toStudentUpdate(req, resp);
                break;
            case "update":
                update(req, resp);
                break;
        }


    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("StudentServlet.update");
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        String gender = req.getParameter("gender");
        System.out.println("name: " + name);
        System.out.println("age: " + age);
        System.out.println("gender: " + gender);

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "UPDATE student SET name=?,age=?,gender=? WHERE id=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setInt(2, Integer.parseInt(age));
            statement.setString(3, gender);
            statement.setInt(4, Integer.parseInt(id));
            System.out.println(statement);
            int count = statement.executeUpdate();
            System.out.println("count: " + count);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(connection, statement, null);
        }

        //更新之后，重定向
        resp.sendRedirect("/student?method=selectAll");
    }

    private void toStudentUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("StudentServlet.toStudentUpdate");
        String id = req.getParameter("id");

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Student student = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "SELECT id,name,age,gender FROM student WHERE id=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(id));
            System.out.println(statement);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {//判断下一个有没有，没有返回false，如果有返回true，并且指向下一行
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                student = new Student(Integer.parseInt(id), name, age, gender);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(connection, statement, resultSet);
        }

        req.setAttribute("student", student);
        req.getRequestDispatcher("student_update.jsp").forward(req, resp);
    }

    private void toStudentAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("StudentServlet.toStudentAdd");
        req.getRequestDispatcher("student_add.jsp").forward(req, resp);
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("StudentServlet.add");
        //不管前台是get还是post请求的参数，都是使用req.getParameter()来获取数据
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        String gender = req.getParameter("gender");
        System.out.println("name: " + name);
        System.out.println("age: " + age);
        System.out.println("gender: " + gender);

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "INSERT INTO student(name,age,gender) VALUES(?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setInt(2, Integer.parseInt(age));
            statement.setString(3, gender);
            System.out.println(statement);
            int count = statement.executeUpdate();
            System.out.println("count: " + count);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(connection, statement, null);
        }

        //添加之后，重定向
        resp.sendRedirect("/student?method=selectAll");
    }

    private void deleteById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //http://localhost:8080/deleteStudent?id=1
        String id = req.getParameter("id");

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "DELETE FROM student WHERE id=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(id));
            System.out.println(statement);
            int count = statement.executeUpdate();
            System.out.println("count: " + count);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(connection, statement, null);
        }

        //删除之后，重定向
        resp.sendRedirect("/student?method=selectAll");
    }

    private void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Student> list = new ArrayList<>();
        try {
            connection = JDBCUtil.getConnection();
            String sql = "SELECT id,name,age,gender FROM student";
            statement = connection.prepareStatement(sql);
            System.out.println(statement);
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


        //把list数据交给jsp页面进行展示
        //把list数据当道req里面
        req.setAttribute("list", list);
        //转发到student_list.jsp页面进行展示
        req.getRequestDispatcher("student_list.jsp").forward(req, resp);
    }
}
