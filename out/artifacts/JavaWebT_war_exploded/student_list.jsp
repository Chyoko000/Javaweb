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
    //处理这个问题是因为有null自动进行selectAll
    List<Student>list=(List<Student>) request.getAttribute("list");
//    通过 request.getAttribute("list") 获取 StudentServlet 传来的学生数据列表。
%>
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
        <td>操作</td>
    </tr>
    <%
        for (Student student : list) {//当list全部遍历完
    %>

    <tr>
        <td><%=student.getId()%></td>
        <td><%=student.getName()%></td>
        <td><%=student.getAge()%></td>
        <td><%=student.getGender()%></td>

<%--        <td><a href="/deletstudent？id=${student.id}">删除</a></td>--%>
<%--问号（?） 的作用是 分隔 URL 的路径部分和查询参数
${student.id}
相当于：
<%= student.getId() %>
当 JSP 运行时，它会自动调用 getId() 方法，并将返回的值填充到页面中。--%>

<%--使用弹窗方法删除--%>
        <td><a href="javascript:void(0)" onclick="deleteById(<%= student.getId() %>)">删除</a></td>
<%--onclick="..." 绑定点击事件，当用户点击“删除”时会触发 deleteById() 函数。
javascript:void(0) 让链接点击后不跳转任何页面，防止空链接影响页面行为。--%>
    </tr>
    <%
        }
    %>
</table>
<%--加个弹窗--%>
<script>
    function deleteById(id){
        var isDelete=confirm('(´～`)您真的要删除嘛')
        // confirm() 是 JavaScript 内置函数，会弹出一个对话框，显示 "(´～)您真的要删除嘛"`
        if(isDelete){
            //如果用户确认删除（isDelete 为 true），执行删除操作。
            location.href="/student?method=deleteById&id="+id;
            //用于跳转到新的 URL，相当于让浏览器访问：+id 变量 id 的值拼接到 URL 中
            //两个参数
        }
    }
</script>

<%--页码--%>
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
