package com.situ.web.dao.impl;

import com.situ.web.dao.IBanjiDao;
import com.situ.web.pojo.Banji;
import com.situ.web.pojo.Student;
import com.situ.web.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BanjiDaoImpl implements IBanjiDao {
    @Override
    public List<Banji> selectAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Banji> list = new ArrayList<>();
        try {
            connection = JDBCUtil.getConnection();
            String sql = "SELECT id,name,address FROM banji";
            statement = connection.prepareStatement(sql);
            System.out.println(statement);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {//判断下一个有没有，没有返回false，如果有返回true，并且指向下一行
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                Banji banji = new Banji(id, name, address);
                list.add(banji);
            }
            for (Banji banji : list) {
                System.out.println(banji);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(connection, statement, resultSet);
        }
        return list;
    }

    @Override
    public void deleteById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "DELETE FROM banji WHERE id=?";
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
    public void add(Banji banji) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "INSERT INTO banji(name, address) VALUES(?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, banji.getName());
            statement.setString(2, banji.getAddress());
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
    public Banji selectById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Banji banji = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "SELECT id,name,address FROM banji WHERE id=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            System.out.println(statement);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {//判断下一个有没有，没有返回false，如果有返回true，并且指向下一行
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                banji = new Banji(id, name, address);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(connection, statement, resultSet);
        }

        return banji;
    }

    @Override
    public void update(Banji banji) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "UPDATE banji SET name=?,address=? WHERE id=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, banji.getName());
            statement.setString(2, banji.getAddress());
            statement.setInt(3, banji.getId());
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
    public List<Banji> selectByPage(int offset, int pageSize) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Banji> list = new ArrayList<>();
        try {
            connection = JDBCUtil.getConnection();
            String sql = "SELECT id,name,address FROM banji LIMIT ?,?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, offset);
            statement.setInt(2, pageSize);
            System.out.println(statement);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {//判断下一个有没有，没有返回false，如果有返回true，并且指向下一行
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                Banji banji = new Banji(id, name, address);
                list.add(banji);
            }
            for (Banji banji : list) {
                System.out.println(banji);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(connection, statement, resultSet);
        }
        return list;
    }

    @Override
    public int selectTotalCount() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int totalCount = 0;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "SELECT count(*) FROM banji";
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
