
//现阶段的代码运行逻辑
//书写两个类
// 一个对应数据库通用
//一个工具类主要是本次引用
//写一个页面向seevlet传输信息
//在servlet里调用工具类的元素
//在接口声明能实现的功能
//最后在impl里进行运算
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
