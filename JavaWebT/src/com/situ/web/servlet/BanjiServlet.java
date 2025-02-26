package com.situ.web.servlet;

import com.situ.web.pojo.Banji;
import com.situ.web.service.IBanjiService;
import com.situ.web.service.impl.BanjiServiceImpl;
import com.situ.web.util.PageInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/banji")
public class BanjiServlet extends HttpServlet {
    //private IBanjiDao banjiDao = new BanjiDaoImpl();
    private IBanjiService banjiService = new BanjiServiceImpl();
    //声明一个

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // /banji?method=selectAll
        // /banji?method=add
        req.setCharacterEncoding("UTF-8");



        //过滤器
//        HttpSession session = req.getSession();
//        User user = (User) session.getAttribute("user");
//        if (user == null) {
//            resp.sendRedirect("/login.jsp");
//            return;
//        }//如果没登陆就会重定向到登陆界面

        String method = req.getParameter("method");
        if (method == null || method.equals("")) {
            method = "selectByPage";//方法名没大写跳转出问题是因为跳转了默认的
        }
        switch (method) {
            case "selectAll":
                selectAll(req, resp);
                break;
            case "selectByPage":
                selectByPage(req, resp);
                break;
            case "deleteById":
                deleteById(req, resp);
                break;
            case "add":
                add(req, resp);
                break;
            case "toBanjiUpdate":
                toBanjiUpdate(req, resp);
                break;
            case "update":
                update(req, resp);
                break;
        }
    }



    private void selectByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("BanjiServlet.selectByPage");
        String pageNo = req.getParameter("pageNo");
        String pageSize = req.getParameter("pageSize");
        System.out.println("pageNo" +pageNo);
        if (pageNo == null || pageNo.equals("")) {
            pageNo = "1";
        }
        if (pageSize == null || pageSize.equals("")) {
            pageSize = "5";
        }

        PageInfo<Banji> pageInfo = banjiService.selectByPage(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
        System.out.println(pageInfo);
        req.setAttribute("pageInfo", pageInfo);
        req.getRequestDispatcher("/banji_list.jsp").forward(req, resp);
    }



    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("BanjiServlet.update");
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        Banji banji = new Banji(Integer.parseInt(id), name, address);

        banjiService.update(banji);
        resp.sendRedirect("/banji?method=selectByPage");
    }

    private void toBanjiUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("BanjiServlet.toBanjiUpdate");
        String id = req.getParameter("id");
        Banji banji = banjiService.selectById(Integer.parseInt(id));
        req.setAttribute("banji", banji);
        req.getRequestDispatcher("/banji_update.jsp").forward(req, resp);
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("BanjiServlet.add");
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        Banji banji = new Banji();
        banji.setName(name);
        banji.setAddress(address);
        banjiService.add(banji);

        resp.sendRedirect("/banji?method=selectAll");
    }

    private void deleteById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("BanjiServlet.deleteById");
        String id = req.getParameter("id");
        banjiService.deleteById(Integer.parseInt(id));

        resp.sendRedirect("/banji?method=selectAll");
    }

    protected void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("BanjiServlet.selectAll");
        List<Banji> list = banjiService.selectAll();

        req.setAttribute("list", list);
        req.getRequestDispatcher("/banji_list2.jsp").forward(req, resp);
    }

}