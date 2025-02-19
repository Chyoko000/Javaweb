<%@ page import="java.util.List" %>
<%--导入包和页面设置--%>
<%@ page import="com.situ.web.pojo.Teacher" %>
<%@ page import="com.situ.web.pojo.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="static/bootstrap-3.4.1-dist/css/bootstrap.css">
</head>
<body>
<%
    //JSP可以嵌套java代码
    //可以任意写Java代码
    //只能用request和reponse
    List<Student>list=(List<Student>) request.getAttribute("list");
//    通过 request.getAttribute("list") 获取 StudentServlet 传来的学生数据列表。
%>
//
<%--<button class="btn btn-default">（默认样式）Default</button>--%>
<%--<button class="btn btn-primary">（首选项）Primary</button>--%>
<%--<button class="btn btn-success">（成功）Success</button>--%>
<%--<button class="btn btn-link">（链接）Link</button>--%>
<%--<a href="" class="btn btn-success">超链接</a>--%>
<table class="table table-striped table-bordered table-hover table-condensed">
    <tr>
        <td>ID</td>
        <td>名字</td>
        <td>年龄</td>
        <td>性别</td>
        <td>删除</td>
    </tr>
    <%
        for (Student student : list) {//当list全部遍历完
    %>

    <tr>
        <td><%=student.getId()%></td>
        <td><%=student.getName()%></td>
        <td><%=student.getAge()%></td>
        <td><%=student.getGender()%></td>
        <td><a href="/deletstudent">删除</a></td>
    </tr>
    <%
        }
    %>
</table>
<nav aria-label="Page navigation">
    <ul class="pagination">
        <li>
            <a href="#" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
            </a>
        </li>
        <li><a href="#">1</a></li>
        <li><a href="#">2</a></li>
        <li><a href="#">3</a></li>
        <li><a href="#">4</a></li>
        <li><a href="#">5</a></li>
        <li>
            <a href="#" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
            </a>
        </li>
    </ul>
</nav>
</body>
</html>
