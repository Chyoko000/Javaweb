<%--
  Created by IntelliJ IDEA.
  User: Gao
  Date: 2025/1/23
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <form action="/student?method=add" method="post">
    用户名：<input type="text" name="name"><br>
    年龄：<input type="text" name="age"><br>
    性别：<input type="text" name="gender"><br>
    <input type="submit" value="添加">
  </form>
</body>
</html>
