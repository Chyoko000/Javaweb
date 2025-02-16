<%@ page import="com.situ.web.pojo.Banji" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <%
    Banji banji = (Banji) request.getAttribute("banji");
  %>
  <form action="/banji?method=update" method="post">
    <input type="hidden" name="id" value="<%=banji.getId()%>">
    班级名：<input type="text" name="name"  value="<%=banji.getName()%>"><br>
    地址：<input type="text" name="address"  value="<%=banji.getAddress()%>"><br>
    <input type="submit" value="修改">
  </form>
</body>
</html>
