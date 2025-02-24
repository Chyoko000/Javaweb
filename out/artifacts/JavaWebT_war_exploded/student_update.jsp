<%@ page import="com.situ.web.pojo.Student" %><%--
  Created by IntelliJ IDEA.
  User: c2543
  Date: 2025/2/21
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%--这个页面的实现是先在页面请求了一次查找，关键字是id，然后返回数据到update在进行修改再请求一次修改再返回list--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--action属性指定 表单提交的目标地址。--%>
<%--蠢货忘了加参数--%>
<%--用表单提交请求不写的话默认就是get--%>
<%
    Student student=(Student) request.getAttribute("student");
    //request.getAttribute("student")：从 request 作用域中取出之前存入的属性，属性名为 "student"。
   //(Student)：将取出的对象进行类型转换，确保它是 Student 类型。
    //Student student = ...：将转换后的对象赋值给变量 student。
%>
<form action="/student?method=toUAdd" method="post">
<%--为了解决提交表单时未获取id值导致后续修改无法完成--%>
    <input type="hidden" name="id" value="<%=student.getId()%>">
    用户名：<input type="text"name="name" value="<%=student.getName()%>"><br>
    年龄：<input type="text" name="age" value="<%=student.getAge()%>"><br>
    性别：<input type="text" name="gender" value="<%=student.getGender()%>"><br>
    <input type="submit" value="确认">
    <%--提交按钮（submit）--%>
</form>

</body>
</html>
