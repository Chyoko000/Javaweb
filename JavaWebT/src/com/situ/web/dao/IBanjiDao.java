package com.situ.web.dao;

import com.situ.web.pojo.Banji;

import javax.annotation.processing.SupportedAnnotationTypes;
import java.util.List;

//目前，先写两个类，一个对应数据库，一个对应工具类
//写一个jsp从jsp传回数据，通过第一个类进行元素传递
//在servlet外面声明一个接口private IBanjiService banjiService = new BanjiServiceImpl()
// 然后在里面写代码就行然后可以直接封装进来;
    //接口这里写接口，然后在后面写代码
public interface IBanjiDao {
    List<Banji> selectAll();
    void deleteById(int id);
    void add(Banji banji);
    Banji selectById(int id);
    void update(Banji banji);

    List<Banji> selectByPage(int offset, int pageSize);

    int selectTotalCount();

}
