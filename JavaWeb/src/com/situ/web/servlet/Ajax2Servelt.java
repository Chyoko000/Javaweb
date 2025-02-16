package com.situ.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/ajax2")
public class Ajax2Servelt extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Ajax2Servelt.doPost");
        String name = req.getParameter("name");
        Map<String, Object> map = new HashMap<>();
        if ("tom".equalsIgnoreCase(name)) {
            // {exist: true,msg: '此用户已经存在'}
            map.put("exist", true);
            map.put("msg", "此用户已经存在");
        } else {
            // {exist: false,msg: '此用户可用'}
            map.put("exist", false);
            map.put("msg", "此用户可用");
        }
        resp.setContentType("text/html;charset=UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(resp.getWriter(), map);
    }
}
