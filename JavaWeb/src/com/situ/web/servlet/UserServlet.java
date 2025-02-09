package com.situ.web.servlet;

import com.situ.web.pojo.User;
import com.situ.web.service.IUserService;
import com.situ.web.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private IUserService userService = new UserServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //req.setCharacterEncoding("UTF-8");
        String method = req.getParameter("method");
        if (method == null || "".equals(method)) {
            method = "selectByPage";
        }
        switch (method) {
            case "login":
                login(req, resp);
                break;
            case "logout":
                logout(req, resp);
                break;
        }
    }

    private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("UserServlet.logout");
        HttpSession session = req.getSession();
        session.invalidate();
        //session.removeAttribute("user");
        resp.sendRedirect("/login.jsp");
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("UserServlet.login");
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        User user = userService.login(name, password);
        if (user == null) {//没有这个用户
            //http://localhost:8080/
            resp.sendRedirect("/fail.jsp");
        } else {//有这个用户登录成功
            //user用户信息放到sesion，作为登录的凭证
            HttpSession session = req.getSession();
            session.setAttribute("user", user);

            resp.sendRedirect("/");
        }
    }
}

