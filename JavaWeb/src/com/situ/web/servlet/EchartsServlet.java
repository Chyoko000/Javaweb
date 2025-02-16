package com.situ.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.situ.web.pojo.City;
import com.situ.web.pojo.vo.GenderCountVO;
import com.situ.web.util.JDBCUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/echarts")
public class EchartsServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("EchartsServlet.service");

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<GenderCountVO> list = new ArrayList<>();
        try {
            connection = JDBCUtil.getConnection();
            String sql = "SELECT gender AS 'name',count(*) AS 'value' FROM student GROUP BY gender";
            statement = connection.prepareStatement(sql);
            System.out.println(statement);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int value = resultSet.getInt("value");
                GenderCountVO genderCountVO = new GenderCountVO(name, value);
                list.add(genderCountVO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        resp.setContentType("text/html;charset=UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(resp.getWriter(), list);
    }
}
