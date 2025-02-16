<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/layui/css/layui.css">
</head>
<body>
    <form class="layui-form">
        课程名：
        <div class="layui-inline">
            <input type="text" name="name" class="layui-input" lay-affix="clear">
        </div>
        学分：
        <div class="layui-inline">
            <input type="text" name="credit" class="layui-input" lay-affix="clear">
        </div>
        <div class="layui-inline">
            <button class="layui-btn" lay-submit lay-filter="submitSearch">搜索</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </form>

    <table class="layui-hide" id="tableId" lay-filter="tableId"></table>

    <script type="text/html" id="toolDemo">
        <div class="layui-clear-space">
            <a class="layui-btn layui-btn-xs" lay-event="update">编辑</a>
            <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="deleteById">删除</a>
        </div>
    </script>

    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" lay-event="add">添加</button>
            <button class="layui-btn layui-btn-sm" lay-event="deleteAll">批量删除</button>
        </div>
    </script>

    <script src="/static/jquery-2.1.4.js" type="text/javascript" charset="utf-8"></script>
    <script src="/static/layui/layui.js" type="text/javascript" charset="utf-8"></script>
    <script src="/static/mylayer.js" type="text/javascript" charset="utf-8"></script>
    <script>
        layui.use(['table','layer', 'form'], function(){
            var table = layui.table;
            var layer = layui.layer;
            var form = layui.form;

            table.render({
                elem: '#tableId',
                url:'/course?method=selectByPage',
                toolbar: '#toolbarDemo',
                cols: [[
                    {type: 'checkbox', fixed: 'left'},
                    {field:'id', title: 'ID', sort: true},
                    {field:'name', title: '用户名'},
                    {field:'credit', title: '学分'},
                    {fixed: 'right', title:'操作', width: 134, minWidth: 125, templet: '#toolDemo'}
                ]],
                page: true
            });

            // 搜索提交
            form.on('submit(submitSearch)', function(data){
                var field = data.field; // 获得表单字段
                // 执行搜索重载
                //http://localhost:8081/course?method=selectByPage&page=1&limit=10&name=Java&credit=3
                table.reload('tableId', {
                    page: {
                        curr: 1 // 重新从第 1 页开始
                    },
                    where: field // 搜索的字段
                });

                return false; // 阻止默认 form 跳转
            });

            // 触发单元格工具事件
            table.on('tool(tableId)', function(obj){ // 双击 toolDouble
                var data = obj.data; // 获得当前行数据
                // {id:12,name:'Java',credit:3}
                console.log(data);
                // console.log(obj)
                if(obj.event === 'update'){
                    layer.open({
                        type: 2,
                        title: '编辑数据',
                        area: ['90%', '90%'],
                        content: '/course_update.jsp?id=' + data.id
                    });
                } else if(obj.event === 'deleteById'){
                    layer.confirm('您确认要删除么?', function () {
                        $.post(
                            '/course?method=deleteById',
                            {'id': data.id},
                            function (result) {
                                console.log(result);
                                if (result.code == 0) {
                                    mylayer.okMsg(result.msg);
                                    //删除数据之后刷新表格，展示最新数据
                                    table.reload('tableId');
                                } else {
                                    mylayer.errorMsg(result.msg);
                                }
                            },
                            'json'
                        );
                    });
                }
            });

            // 头部工具栏事件
            table.on('toolbar(tableId)', function(obj){
                var options = obj.config; // 获取当前表格属性配置项
                var checkStatus = table.checkStatus(options.id); // 获取选中行相关数据
                console.log(obj); // 查看对象所有成员


                // 根据不同的事件名进行相应的操作
                switch(obj.event){ // 对应模板元素中的 lay-event 属性值
                    case 'add':
                        layer.open({
                            type: 2,
                            title: '添加数据',
                            area: ['90%', '90%'],
                            content: '/course_add.jsp'
                        });
                        break;
                    case 'deleteAll':
                        var data = checkStatus.data;
                        //[{id: 1, name: 'Java', credit: 5}, {…}]
                        var ids = new Array();
                        $(data).each(function () {
                            ids.push(this.id);
                        })
                        //[2,3]
                        layer.confirm('您确认要删除么?', function () {
                            $.post(
                                '/course?method=deleteAll',
                                {'ids': ids},
                                function (result) {
                                    console.log(result);
                                    if (result.code == 0) {
                                        mylayer.okMsg(result.msg);
                                        //删除数据之后刷新表格，展示最新数据
                                        table.reload('tableId');
                                    } else {
                                        mylayer.errorMsg(result.msg);
                                    }
                                },
                                'json'
                            );
                        });
                        break;
                };
            });

        });
    </script>
</body>
</html>
