package com.situ.web.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JSONUtil {
    private JSONUtil() {
    }

    public static void toJSON(HttpServletResponse resp, Object object) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(resp.getWriter(), object);
    }
}
