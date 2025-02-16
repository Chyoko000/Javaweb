
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/layui/css/layui.css">
</head>
<body>
<form class="layui-form layui-form-pane" action="">
    <div class="layui-form-item">
        <label class="layui-form-label">课程名</label>
        <div class="layui-input-block">
            <input type="text" name="name" autocomplete="off" placeholder="请输入" lay-verify="required"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">学分</label>
        <div class="layui-input-block">
            <input type="text" name="credit" autocomplete="off" placeholder="请输入" lay-verify="required"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="submit" class="layui-btn" lay-submit lay-filter="submitForm">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

<script src="/static/jquery-2.1.4.js" type="text/javascript" charset="utf-8"></script>
<script src="/static/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script src="/static/mylayer.js" type="text/javascript" charset="utf-8"></script>
<script>
    layui.use(['form'], function () {
        var form = layui.form;
        var layer = layui.layer;

        // 提交事件
        form.on('submit(submitForm)', function (data) {
            var field = data.field; // 获取表单字段值
            //{"name":"UI","credit":"12"}
            // 此处可执行 Ajax 等操作
            $.post(
                '/course?method=add',
                data.field,
                function (result) {
                    console.log(result);
                    if (result.code == 0) {
                        mylayer.okMsg(result.msg);
                        //table.reload('tableId');
                        setInterval(function () {
                            //关闭弹出层
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                            //刷新父页面
                            window.parent.location.reload();
                        }, 2000);
                    }
                },
                'json'
            );

            return false; // 阻止默认 form 跳转
        });
    });
</script>
</body>
</html>
