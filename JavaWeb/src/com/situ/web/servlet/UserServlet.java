package com.situ.web.servlet;

import com.situ.web.pojo.User;
import com.situ.web.service.IUserService;
import com.situ.web.service.impl.UserServiceImpl;
import com.situ.web.util.JDBCUtil;
import com.situ.web.util.JSONUtil;
import com.situ.web.util.Result;

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
        String code = req.getParameter("code");
        HttpSession session = req.getSession();
        String codeInSession = (String) session.getAttribute("codeInSession");

        if (!codeInSession.equalsIgnoreCase(code)) {
            JSONUtil.toJSON(resp, Result.error("验证码错误"));
            return;
        }


        User user = userService.login(name, password);
        if (user == null) {//没有这个用户
            //http://localhost:8080/
            //resp.sendRedirect("/fail.jsp");
            JSONUtil.toJSON(resp, Result.error("登陆失败"));
        } else {//有这个用户登录成功
            //user用户信息放到sesion，作为登录的凭证
            session.setAttribute("user", user);

            //resp.sendRedirect("/");
            JSONUtil.toJSON(resp, Result.ok("登陆成功"));
        }
    }
}

