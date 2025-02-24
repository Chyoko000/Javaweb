<%--
  Created by IntelliJ IDEA.
  User: c2543
  Date: 2025/2/20
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Title</title>
  </head>
  <body>
  <form action="/teacher?method=add" method="post">
    用户名：<input type="text"name="id"><br>
    年龄：<input type="text" name="age"><br>
    国籍：<input type="text" name="adress"><br>
    <input type="submit" value="添加"><br>
    </form>
  </body>
</html>
