package com.situ.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/life")
public class LifeServlet extends HttpServlet {
    //执行时机： 第一次请求 LifeServlet 时，Tomcat 会创建该 Servlet 的实例，调用构造方法。
    //执行次数： 只执行一次，Tomcat 服务器不会为每个请求重新创建 Servlet 实例，而是复用这个对象。
    public LifeServlet(){
        System.out.println("LifeServlet.LifeServlet");
    }
    //这里直接打init
    @Override
    public void init() throws ServletException {
        //执行时机： 只在 Servlet 实例被创建后 执行一次。
        //执行次数： 只执行一次，因为 Servlet 只初始化一次。
        //Servlet 被 Tomcat 加载后，Tomcat 必须调用 init() 进行初始化，否则 Servlet 不能正常工作。
        System.out.println("LifeServlet.int");
    }
    //这里直接打service就会跳提示
    //每次浏览器发出请求时候调用这个方法。调用n次。
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("life测试");
    }
    //服务器关闭（Tomcat 停止）
    //手动卸载或重新部署 Servlet
    @Override
    public void destroy() {
        System.out.println("LifeServlet.destroy");
    }
}
