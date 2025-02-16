package com.situ.web.servlet;

import com.situ.web.pojo.Course;
import com.situ.web.pojo.query.CourseQuery;
import com.situ.web.service.ICourseService;
import com.situ.web.service.impl.CourseServiceImpl;
import com.situ.web.util.JSONUtil;
import com.situ.web.util.PageResult;
import com.situ.web.util.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/course")
public class CourseServlet extends BaseServlet {
    private ICourseService courseService = new CourseServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method == null || method.equals("")) {
            method = "selectByPage";
        }

        switch (method) {
            case "selectByPage":
                selectByPage(req, resp);
                break;
            case "deleteById":
                deleteById(req, resp);
                break;
            case "add":
                add(req, resp);
                break;
            case "selectById":
                selectById(req, resp);
                break;
            case "update":
                update(req, resp);
                break;
            case "deleteAll":
                deleteAll(req, resp);
                break;
        }
    }

    private void deleteAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("CourseServlet.deleteAll");
        String[] ids = req.getParameterValues("ids[]");
        courseService.deleteAll(ids);
        JSONUtil.toJSON(resp, Result.ok("删除成功"));
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("CourseServlet.update");
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String credit = req.getParameter("credit");
        Course course = new Course(Integer.parseInt(id), name, Integer.parseInt(credit));
        courseService.update(course);

        JSONUtil.toJSON(resp, Result.ok("编辑成功"));
    }

    private void selectById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("CourseServlet.selectById");
        String id = req.getParameter("id");
        Course course = courseService.selectById(Integer.parseInt(id));

        JSONUtil.toJSON(resp, Result.ok(course));
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("CourseServlet.add");
        String name = req.getParameter("name");
        String credit = req.getParameter("credit");
        Course course = new Course(name, Integer.parseInt(credit));
        courseService.add(course);

        JSONUtil.toJSON(resp, Result.ok("添加成功"));
    }

    private void deleteById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("CourseServlet.deleteById");
        String id = req.getParameter("id");
        courseService.deleteById(Integer.parseInt(id));

        //ajax只需要返回这次请求是成功了还是失败了，具体页面跳转由前端跳转
        //code、msg、data
        Result result = new Result(0, "删除成功");
        //JSONUtil.toJSON(resp, result);
        toJSON(resp, result);
    }

    //http://localhost:8081/course?method=selectByPage&page=1&limit=10
    //http://localhost:8081/course?method=selectByPage&page=1&limit=10&name=Java&credit=3
    private void selectByPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("CourseServlet.selectByPage");
        String page = req.getParameter("page");
        String limit = req.getParameter("limit");
        String name = req.getParameter("name");
        String credit = req.getParameter("credit");

        CourseQuery courseQuery = new CourseQuery(Integer.parseInt(page), Integer.parseInt(limit), name, credit);
        //PageResult<Course> pageResult = courseService.selectByPage(Integer.parseInt(page), Integer.parseInt(limit), name, credit);
        PageResult<Course> pageResult = courseService.selectByPage(courseQuery);

        JSONUtil.toJSON(resp, pageResult);
    }
}
