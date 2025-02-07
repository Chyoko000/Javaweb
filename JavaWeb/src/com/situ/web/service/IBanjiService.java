package com.situ.web.service;

import com.situ.web.pojo.Banji;
import com.situ.web.util.PageInfo;

import java.util.List;

public interface IBanjiService {
    List<Banji> selectAll();
    void deleteById(int id);
    void add(Banji banji);
    Banji selectById(int id);
    void update(Banji banji);

    PageInfo<Banji> selectByPage(int pageNo, int pageSize);
}
