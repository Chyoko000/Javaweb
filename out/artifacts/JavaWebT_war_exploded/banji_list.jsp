<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.situ.web.pojo.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="com.situ.web.pojo.Banji" %>
<%@ page import="com.situ.web.util.PageInfo" %>
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
    //List<Banji> list = (List<Banji>) request.getAttribute("list");
    PageInfo<Banji> pageInfo = (PageInfo<Banji>) request.getAttribute("pageInfo");
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
    <c:forEach items="${pageInfo.list}" var="banji">
        <tr>
            <td>${banji.id}</td>
            <td>${banji.name}</td>
            <td>${banji.address}</td>
            <td><a href="javascript:void(0)" onclick="deleteById(${banji.id})">删除</a></td>
            <td><a href="/banji?method=toBanjiUpdate&id=${banji.id}" >编辑</a></td>
        </tr>
    </c:forEach>
</table>
<nav aria-label="Page navigation">
    <ul class="pagination">
        <c:if test="${pageInfo.pageNo==1}">
            <li class="disabled">
                <a href="#" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
        </c:if>
        <c:if test="${pageInfo.pageNo!=1}">
            <li>
                <a href="/banji?method=selectByPage&pageNo=${pageInfo.pageNo-1}" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
        </c:if>

        <c:forEach begin="1" end="${pageInfo.totalPage}" step="1" var="i">
            <c:if test="${i==pageInfo.pageNo}">
                <li class="active"><a href="#">${i}</a></li>
            </c:if>
            <c:if test="${i!=pageInfo.pageNo}">
                <li><a href="/banji?method=selectByPage&pageNo=${i}&pageSize=5">${i}</a></li>
            </c:if>
        </c:forEach>

        <%--<%
            for (int i = 1; i <= pageInfo.getTotalPage(); i++) {
                if (i == pageInfo.getPageNo()) {
        %>
                     <li class="active"><a href="#"><%=i%></a></li>
        <%
                } else {
        %>
                    <li><a href="/banji?method=selectByPage&pageNo=<%=i%>&pageSize=5"><%=i%></a></li>
        <%
                }
            }
        %>--%>

        <li>
            <a href="/banji?method=selectByPage&pageNo=<%=pageInfo.getPageNo()+1%>" aria-label="Next">
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

