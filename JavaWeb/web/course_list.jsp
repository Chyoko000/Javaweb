<%--
  Created by IntelliJ IDEA.
  User: c2543
  Date: 2025/2/15
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="./static/layui/css/layui.css">
</head>
<body>
<table class="layui-hide"id="tableId"lay-filter="tableId"></table>
    <script src="/static/jquery-2.1.4.js" type="text/javascript" charset="utf-8"></script>
    <script src="/static/layui/layui.js" type="text/javascript" charset="utf-8"></script>
    <script src="/static/mylayer.js" type="text/javascript" charset="utf-8"></script>
    <script>
        layui.use('table', function(){
            var table = layui.table;

            table.render({
                elem: '#test'
                ,url:'/course?method=selectByPage'

                ,cols: [[
                    {type:'radio'}
                    ,{field:'id', width:80, title: 'ID', sort: true}
                    ,{field:'username', width:80, title: '用户名'}
                    ,{field:'credit', width:80, title: '学分'}
                ]]
                ,page: true
            });

            //头工具栏事件

        });
    </script>

</body>
</html>
