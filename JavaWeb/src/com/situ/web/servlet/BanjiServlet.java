package com.situ.web.servlet;

import com.situ.web.dao.IBanjiDao;
import com.situ.web.dao.impl.BanjiDaoImpl;
import com.situ.web.pojo.Banji;
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

@WebServlet("/banji")
public class BanjiServlet extends HttpServlet {
    private IBanjiDao banjiDao = new BanjiDaoImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // /banji?method=selectAll
        // /banji?method=add
        req.setCharacterEncoding("UTF-8");
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
        }
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("BanjiServlet.add");
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        Banji banji = new Banji();
        banji.setName(name);
        banji.setAddress(address);
        banjiDao.add(banji);

        resp.sendRedirect("/banji?method=selectAll");
    }

    private void deleteById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("BanjiServlet.deleteById");
        String id = req.getParameter("id");
        banjiDao.deleteById(Integer.parseInt(id));

        resp.sendRedirect("/banji?method=selectAll");
    }

    private void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("BanjiServlet.selectAll");
        List<Banji> list = banjiDao.selectAll();

        req.setAttribute("list", list);
        req.getRequestDispatcher("banji_list.jsp").forward(req, resp);
    }
}