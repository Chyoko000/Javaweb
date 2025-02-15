package com.situ.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.situ.web.util.PageResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/course")
public class CourseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method=req.getParameter("method");
        if (method == null || method.equals("")) {
            method = "selectByPage";//方法名没大写跳转出问题是因为跳转了默认的
        }
        switch (method){
            case"selectByPage":
                selectByPage(req,resp);
                break;
        }
    }

    private void selectByPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("CourseServlet.selectByPage");
        String page=req.getParameter("paga");
        String limit=req.getParameter("limit");
        PageResult<Course> pageResult=courseSercice.selectByPage(Integer.parseInt(page),Integer.parseInt(limit));

        resp.setContentType("text/html;charset=UTF-8");
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.writeValue(resp.getWriter(),pageResult);
    }
}