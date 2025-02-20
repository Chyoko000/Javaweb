<%@ page import="java.util.List" %>
<%@ page import="com.situ.web.pojo.Teacher" %><%--
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
    List<Teacher>list=(List<Teacher>) request.getAttribute("list");
%>
<table class="table table-striped table-bordered table-hover table-condensed">
    <tr>
        <td>ID</td>
        <td>名字</td>
        <td>年龄</td>
        <td>国籍</td>
        <td>操作</td>
    </tr>
    <%
        for (Teacher teacher : list) {//当list全部遍历完
    %>
    <%--java循环查询arraylist里面的techer信息--%>
    <tr>
        <td><%=teacher.getId()%></td>
        <td><%=teacher.getName()%></td>
        <td><%=teacher.getAge()%></td>
        <td><%=teacher.getAddress()%></td>
        <td><a href="javascript:void(0)" onclick="deleteById(<%= teacher.getId() %>)">删除</a></td>
    </tr>
    <%
        }
    %>
</table>
<script>
    function deleteById(id){
        var isDelete=confirm('(☉д⊙)您真的要删除嘛')
        // confirm() 是 JavaScript 内置函数，会弹出一个对话框，显示 "(´～)您真的要删除嘛"`
        if(isDelete){
            //如果用户确认删除（isDelete 为 true），执行删除操作。
            location.href="/teacher?method=deleteById&id="+id;
            //用于跳转到新的 URL，相当于让浏览器访问：
        }
    }
</script>




<%--分页导航--%>
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
