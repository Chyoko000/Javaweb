<%@ page import="com.situ.web.pojo.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--${list}--%>
<%
    //JSP页面内置对象：已经提前new好对象，名字也起好了
    //pageContext(PageContext)、request(HttpServletRequest)、session(HttpSession)、application(ServletContext)域对象，也是内置对象
    //1.普通字符串
    pageContext.setAttribute("name", "zhangsan");
    request.setAttribute("name", "lisi");
    //2.Student对象
    Student student = new Student(1, "zhansga", 23, "男");
    session.setAttribute("student", student);
    //3.List<Student>
    List<Student> list = new ArrayList<>();
    Student student1 = new Student(1, "zhangsan1", 23, "男");
    Student student2 = new Student(2, "zhangsan2", 23, "男");
    Student student3 = new Student(3, "zhangsan3", 23, "男");
    list.add(student1);
    list.add(student2);
    list.add(student3);
    application.setAttribute("list", list);
%>

<%--JSP表达式--%>
<%=pageContext.getAttribute("name")%><br/>
<%=request.getAttribute("name")%><br/>
<%
    Student stu = (Student) session.getAttribute("student");
%>
<%=stu.getName()%><br/>
<%=stu.getAge()%><br/>
<%=((Student) session.getAttribute("student")).getGender()%><br>
<%
    List<Student> stuList = (List<Student>) application.getAttribute("list");
%>
<%=stuList.get(0).getName()%>

<hr/>
<%--EL表达式--%>
${pageScope.name}<br/>
${requestScope.name}<br/>
${name}<br/>
${sessionScope.student.name}<br/>
${student.age}<br/>
${list}<br/>
${list[0].name}<br/>

</body>
</html>
