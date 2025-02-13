package com.situ.web.filter;

import com.situ.web.pojo.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//     /* 代表拦截所有
//第十四行注释掉这个过滤器就不拦截了
//@WebFilter(filterName = "login", urlPatterns = "/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        System.out.println("LoginFilter.init");
    }

    //service
    //HttpServletRequest extends ServletReques
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("LoginFilter.doFilter");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();
        System.out.println("uri: " + uri);

        // uri: /user
        // /user?method=login
        //request.setCharacterEncoding("UTF-8");
        // /user
        // method.equals("login")   null.equals()
        // "login".equals(method)   对象.equals(null)
        String method = request.getParameter("method");
        //如果这个请求是要去完成登录的，就不需要执行后面的验证是否已经登录的流程
        if (uri.startsWith("/static")  // 访问/static都放行
                || uri.equals("/login.jsp")
                || uri.equals("/fail.jsp")
                || (uri.equals("/user") && "login".equals(method))) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        //验证是否已经登录
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("/login.jsp");
            return;
        }

        //继续往后执行
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
        System.out.println("LoginFilter.destroy");
    }
}

