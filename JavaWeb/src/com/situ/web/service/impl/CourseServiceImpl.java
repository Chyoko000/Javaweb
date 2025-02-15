package com.situ.web.service.impl;

import com.situ.web.dao.ICourseDao;
import com.situ.web.dao.impl.CourseDaoImpl;
import com.situ.web.pojo.Course;
import com.situ.web.service.ICourseService;
import com.situ.web.util.PageResult;

import java.util.List;

public class CourseServiceImpl implements ICourseService {
    private ICourseDao courseDao=new CourseDaoImpl();
    //ALT+ENTER
    @Override
    public PageResult<Course> selectByPage(Integer page, Integer limit) {
        int offset=(page-1)*limit;
        List<Course>list=courseDao.selectByPage(offset,limit);
        Integer totalCount=courseDao.selectTotalCount();
        PageResult<Course> pageResult= new PageResult<>(0,"",totalCount,list);
        return pageResult;
    }
}
