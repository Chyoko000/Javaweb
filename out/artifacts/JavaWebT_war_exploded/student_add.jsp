<%--
  Created by IntelliJ IDEA.
  User: c2543
  Date: 2025/2/20
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Title</title>
  </head>
  <body>
<%--action属性指定 表单提交的目标地址。--%>
<%--蠢货忘了加参数--%>
<%--用表单提交请求不写的话默认就是get--%>
  <form action="/student?method=add" method="post">
    用户名：<input type="text"name="name" ><br>
    年龄：<input type="text" name="age" ><br>
    性别：<input type="text" name="gender" ><br>
    <input type="submit" value="添加">
<%--提交按钮（submit）--%>
  </form>
  
  </body>
</html>
