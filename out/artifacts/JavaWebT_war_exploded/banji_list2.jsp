<%@ page import="com.situ.web.pojo.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="com.situ.web.pojo.Banji" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="static/bootstrap-3.4.1-dist/css/bootstrap.css">
</head>
<body>
<%--${list}--%>
<%
    //JSP页面可以嵌套java代码
    //JSP脚本：在这里可以任意写java代码
    //request、response Jsp页面的内置对象
    List<Banji> list = (List<Banji>) request.getAttribute("list");
%>
<a class="btn btn-success" href="banji_add.jsp">添加</a>
<a class="btn btn-success" href="/banji?method=toBanjiAdd">添加</a>
<table class="table table-striped table-bordered table-hover table-condensed">
    <tr>
        <td>ID</td>
        <td>名字</td>
        <td>地址</td>
        <td>删除</td>
        <td>编辑</td>
    </tr>
    <%
        for (Banji banji : list) {
    %>
    <tr>
        <td><%=banji.getId()%></td>
        <td><%=banji.getName()%></td>
        <td><%=banji.getAddress()%></td>
        <%--<td><a href="/deleteStudent?id=<%=student.getId()%>">删除</a></td>--%>
        <td><a href="javascript:void(0)" onclick="deleteById(<%=banji.getId()%>)">删除</a></td>
        <td><a href="/banji?method=toBanjiUpdate&id=<%=banji.getId()%>" >编辑</a></td>
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
<script>
    // var: variable变量
    function deleteById(id) {
        var isDelete = confirm('您真的要删除么？');
        if (isDelete) {
            location.href = "/banji?method=deleteById&id=" + id;
        }
    }
</script>
</body>
</html>