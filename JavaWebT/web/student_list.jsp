<%@ page import="java.util.List" %>
<%@ page import="com.situ.web.pojo.Student" %><%--
  Created by IntelliJ IDEA.
  User: c2543
  Date: 2025/2/12
  Time: 21:31
  To change this template use File | Settings | File Templates.
--%>
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
%>
<button class="btn btn-default">（默认样式）Default</button>
<button class="btn btn-primary">（首选项）Primary</button>
<button class="btn btn-success">（成功）Success</button>
<button class="btn btn-link">（链接）Link</button>
<a href="" class="btn btn-success">超链接</a>
<table class="table table-striped table-bordered table-hover table-condensed">
    <tr>
        <td>ID</td>
        <td>名字</td>
        <td>年龄</td>
        <td>性别</td>
    </tr>
    <%
        for (Student student : list) {//当list全部遍历完
    %>

    <tr>
        <td><%student.getId()%></td>
        <td><%student.getName()%></td>
        <td><%student.getAge()%></td>
        <td><%student.getGender()%></td>
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
