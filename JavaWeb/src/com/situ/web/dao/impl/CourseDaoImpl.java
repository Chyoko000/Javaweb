package com.situ.web.dao.impl;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import com.situ.web.dao.ICourseDao;
import com.situ.web.pojo.Banji;
import com.situ.web.pojo.Course;
import com.situ.web.service.ICourseService;
import com.situ.web.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl implements ICourseDao {

    @Override
    public List<Course> selectByPage(Integer offset, Integer limit) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Course> list = new ArrayList<>();
        try {
            connection = JDBCUtil.getConnection();
            String sql = "SELECT id,name,credit FROM course LIMIT ?,?";//应为credit
            statement = connection.prepareStatement(sql);
            statement.setInt(1, offset);
            statement.setInt(2, limit);
            System.out.println(statement);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {//判断下一个有没有，没有返回false，如果有返回true，并且指向下一行
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int credit = resultSet.getInt("credit");
                Course course = new Course(id, name,credit);
                list.add(course);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(connection, statement, resultSet);
        }
        return list;
    }

    @Override
    public Integer selectTotalCount() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int totalCount = 0;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "SELECT count(*) FROM course";
            statement = connection.prepareStatement(sql);
            System.out.println(statement);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {//判断下一个有没有，没有返回false，如果有返回true，并且指向下一行
                totalCount = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(connection, statement, resultSet);
        }

        return totalCount;
    }


}
