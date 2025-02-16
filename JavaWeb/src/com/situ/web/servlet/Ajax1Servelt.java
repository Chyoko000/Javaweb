package com.situ.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ajax1")
public class Ajax1Servelt extends HttpServlet {

    /*@Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Ajax1Servelt.service");
    }*/

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Ajax1Servelt.doGet");
        String name = req.getParameter("name");
        System.out.println(name);

        //转义：
        // {"name":"李四","age":23}  [{},{}]
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().write("{\"name\":\"李四1\",\"age\":24}");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Ajax1Servelt.doPost");
        String name = req.getParameter("name");
        System.out.println(name);

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().write("{\"name\":\"李四2\",\"age\":25}");
    }
}
