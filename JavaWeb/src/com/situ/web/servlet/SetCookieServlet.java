package com.situ.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/setCookie")
public class SetCookieServlet extends HttpServlet {


    @Override
    protected void service(HttpServletRequest rep,HttpServletResponse resp) throws ServletException,IOException{
        System.out.println("SetCookieServlet.service");
        Cookie cookie1=new Cookie("goods","XIAOMI");
        Cookie cookie2=new Cookie("name","nothingphone");
        cookie1.setMaxAge(10*60);//保存十分钟容易记录比如24*60就是一整天
        resp.addCookie(cookie1);
        resp.addCookie(cookie2);
        HttpSession session= rep.getSession();
    }
}
