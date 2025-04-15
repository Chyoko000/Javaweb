package com.situ.web.service.impl;

import com.situ.web.dao.ICourseDao;
import com.situ.web.dao.impl.CourseDaoImpl;
import com.situ.web.pojo.Course;
import com.situ.web.pojo.query.CourseQuery;
import com.situ.web.util.PageResult;
import com.situ.web.service.ICourseService;

import java.util.List;

public class            CourseServiceImpl implements ICourseService {
    private ICourseDao courseDao = new CourseDaoImpl();

    @Override
    public PageResult<Course> selectByPage(CourseQuery courseQuery) {
        List<Course> list = courseDao.selectByPage(courseQuery);
        Integer totalCount = courseDao.selectTotalCount(courseQuery);

        PageResult<Course> pageResult =  new PageResult<>(0, "", totalCount, list);
        return pageResult;
    }

    @Override
    public void deleteById(int id) {
        courseDao.deleteById(id);
    }

    @Override
    public void add(Course course) {
        courseDao.add(course);
    }

    @Override
    public Course selectById(int id) {
        return courseDao.selectById(id);
    }

    @Override
    public void update(Course course) {
        courseDao.update(course);
    }

    @Override
    public void deleteAll(String[] ids) {
        for (String id : ids) {
            courseDao.deleteById(Integer.parseInt(id));
        }
    }
}
