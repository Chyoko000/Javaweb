package com.situ.web.dao.impl;

import com.situ.web.dao.ICourseDao;
import com.situ.web.pojo.Course;
import com.situ.web.pojo.query.CourseQuery;
import com.situ.web.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl implements ICourseDao {
    @Override
    public List<Course> selectByPage(CourseQuery courseQuery) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Course> list = new ArrayList<>();
        try {
            connection = JDBCUtil.getConnection();
            //String sql = "SELECT id,name,credit FROM course";
            //String sql = "SELECT id,name,credit FROM course WHERE name=?";
            //String sql = "SELECT id,name,credit FROM course WHERE credit=?";
            //String sql = "SELECT id,name,credit FROM course WHERE name=? AND credit=?";
            //有这个条件就拼接上，没有就不拼接
            //String sql = "SELECT id,name,credit FROM course WHERE 1=1";
            //String sql = "SELECT id,name,credit FROM course WHERE 1=1 AND name=?";
            //String sql = "SELECT id,name,credit FROM course WHERE 1=1 AND credit=?";
            //String sql = "SELECT id,name,credit FROM course WHERE 1=1 AND name=? AND credit=?";
            String sql = "SELECT id,name,credit FROM course WHERE 1=1";
            //构造一个List存所有的条件
            List<Object> queryList = new ArrayList<>();
            String queryName = courseQuery.getName();
            if (queryName != null && !"".equals(queryName)) {
                sql += " AND name LIKE ?";
                queryList.add("%" + queryName + "%");
            }
            String queryCredit = courseQuery.getCredit();
            if (queryCredit != null && !"".equals(queryCredit)) {
                sql += " AND credit=?";
                queryList.add(queryCredit);
            }
            sql += " LIMIT ?,?";
            int offset = (courseQuery.getPage() - 1) * courseQuery.getLimit();
            queryList.add(offset);
            queryList.add(courseQuery.getLimit());
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < queryList.size(); i++) {
                statement.setObject(i + 1, queryList.get(i));
            }
            System.out.println(statement);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {//判断下一个有没有，没有返回false，如果有返回true，并且指向下一行
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int credit = resultSet.getInt("credit");
                Course course = new Course(id, name, credit);
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
    public Integer selectTotalCount(CourseQuery courseQuery) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int totalCount = 0;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "SELECT count(*) FROM course WHERE 1=1";
            //构造一个List存所有的条件
            List<Object> queryList = new ArrayList<>();
            String queryName = courseQuery.getName();
            if (queryName != null && !"".equals(queryName)) {
                sql += " AND name LIKE ?";
                queryList.add("%" + queryName + "%");
            }
            String queryCredit = courseQuery.getCredit();
            if (queryCredit != null && !"".equals(queryCredit)) {
                sql += " AND credit=?";
                queryList.add(queryCredit);
            }
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < queryList.size(); i++) {
                statement.setObject(i + 1, queryList.get(i));
            }
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

    @Override
    public void deleteById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "DELETE FROM course WHERE id=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            System.out.println(statement);
            int count = statement.executeUpdate();
            System.out.println("count: " + count);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(connection, statement, null);
        }
    }

    @Override
    public void add(Course course) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "INSERT INTO course(name, credit) VALUES(?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, course.getName());
            statement.setInt(2, course.getCredit());
            System.out.println(statement);
            int count = statement.executeUpdate();
            System.out.println("count: " + count);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(connection, statement, null);
        }
    }

    @Override
    public Course selectById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Course course = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "SELECT id,name,credit FROM course WHERE id=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            System.out.println(statement);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {//判断下一个有没有，没有返回false，如果有返回true，并且指向下一行
                String name = resultSet.getString("name");
                int credit = resultSet.getInt("credit");
                course = new Course(id, name, credit);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(connection, statement, resultSet);
        }

        return course;
    }

    @Override
    public void update(Course course) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "UPDATE course SET name=?,credit=? WHERE id=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, course.getName());
            statement.setInt(2, course.getCredit());
            statement.setInt(3, course.getId());
            System.out.println(statement);
            int count = statement.executeUpdate();
            System.out.println("count: " + count);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(connection, statement, null);
        }
    }
}
