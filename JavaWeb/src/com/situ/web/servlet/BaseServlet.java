package com.situ.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BaseServlet extends HttpServlet {

    public void toJSON(HttpServletResponse resp, Object object) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(resp.getWriter(), object);
    }
}
