package com.situ.web.dao;

import com.situ.web.pojo.Course;

import java.util.List;

public interface ICourseDao {
    List<Course>selectByPage(Integer offset,Integer limit);
    Integer selectTotalCount();
}
