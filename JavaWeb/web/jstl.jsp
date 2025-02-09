<%--
  Created by IntelliJ IDEA.
  User: c2543
  Date: 2025/2/9
  Time: 23:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.situ.web.pojo.Student" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--1、向域对象放数据--%>
<%
    pageContext.setAttribute("age", 23);
    session.setAttribute("gender", "男");
%>
<c:set var="age" value="25" scope="request"></c:set>
${age}
<hr/>
<%--2、条件判断--%>
<c:if test="${gender=='男'}">
    男性
</c:if>
<c:if test="${gender=='女'}">
    女性
</c:if>
<hr/>
<%--3、多条件判断--%>
<c:set var="score" value="87" scope="request"></c:set>
<c:choose>
    <c:when test="${score>=90 && score<=100}">
        优秀
    </c:when>
    <c:when test="${score>=80 && score<90}">
        良好
    </c:when>
    <c:when test="${score>=70 && score<80}">
        一般
    </c:when>
    <c:when test="${score>=60 && score<70}">
        及格
    </c:when>
    <c:otherwise>
        不及格
    </c:otherwise>
</c:choose>
<hr/>
<%--4、集合遍历
    List<Student>
    Map<String, String>
    Map<String, Student>
--%>
<c:forEach begin="1" end="10" var="i" step="1">
    ${i}
</c:forEach>
<br>
<c:forEach begin="1" end="10" var="i" step="2">
    ${i}
</c:forEach>
<br/>
<%
    List<Student> list = new ArrayList<>();
    Student student1 = new Student(1, "zhangsan1", 23, "男");
    Student student2 = new Student(2, "zhangsan2", 23, "男");
    Student student3 = new Student(3, "zhangsan3", 23, "男");
    list.add(student1);
    list.add(student2);
    list.add(student3);
    application.setAttribute("list", list);
%>
<c:forEach items="${list}" var="student">
    ${student.id}--${student.name}--${student.age}--${student.gender}<br>
</c:forEach>
<hr/>
<%--Map<String, String>--%>
<%
    Map<String, String> map = new HashMap<>();
    map.put("cn", "中国");
    map.put("us", "美国");
    request.setAttribute("map", map);
%>
<c:forEach items="${map}" var="entry">
    ${entry.key}--${entry.value} <br>
</c:forEach>
<hr/>
<%--Map<String, Student>--%>
<%
    Map<String, Student> studentMap = new HashMap<>();
    studentMap.put("student1", student1);
    studentMap.put("student2", student2);
    studentMap.put("student3", student3);
    request.setAttribute("studentMap", studentMap);
%>
<c:forEach items="${studentMap}" var="entry">
    ${entry.key}--${entry.value}--${entry.value.name} <br>
</c:forEach>
</body>
</html>

