package com.situ.web.dao;

import com.situ.web.pojo.Banji;

import java.util.List;

/**
 * 接口描述的是能提供的功能的清单
 */
public interface IBanjiDao {
    List<Banji> selectAll();
    void deleteById(int id);
    void add(Banji banji);
    Banji selectById(int id);
    void update(Banji banji);
}