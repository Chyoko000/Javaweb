package com.situ.web.dao;

import com.situ.web.pojo.Course;
import com.situ.web.pojo.query.CourseQuery;

import java.util.List;

public interface ICourseDao {
    List<Course> selectByPage(CourseQuery courseQuery);
    Integer selectTotalCount(CourseQuery courseQuery);

    void deleteById(int id);

    void add(Course course);

    Course selectById(int id);

    void update(Course course);
}
