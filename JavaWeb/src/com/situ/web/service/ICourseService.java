package com.situ.web.service;

import com.situ.web.pojo.Course;
import com.situ.web.util.PageResult;

public interface ICourseService {
    PageResult<Course> selectByPage(Integer page,Integer limit);
}
