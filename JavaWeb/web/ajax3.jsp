
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    省份：
    <select id="provinceId">
        <option>---请选择省份--</option>
    </select>
    城市：
    <select id="cityId">
        <option>---请选择城市--</option>
    </select>
    区县：
    <select id="areaId">
        <option>---请选择区县--</option>
    </select>

    <script src="/static/jquery-2.1.4.js" type="text/javascript" charset="utf-8"></script>
    <script>
        $(function () {
            $.post(
                '/ajax3?method=selectProvince',
                function (jsonObj) {
                    //[{id: 110000, name: '北京市'},{id: 120000, name: '天津市'}]
                    console.log(jsonObj);
                    $(jsonObj).each(function () {
                        // <option value="111">山东省</option>
                        // this : {id: 110000, name: '北京市'}
                        //$('#provinceId').append('<option value="111">山东省</option>');
                        $('#provinceId').append('<option value="'+this.id+'">'+this.name+'</option>');
                    })
                },
                'json'
            );

            //给省份下拉框绑定change事件
            $('#provinceId').change(function () {
                //this
                $.post(
                    '/ajax3?method=selectCity',
                    {'provinceId': $(this).val()},
                    function (jsonObj) {
                        console.log(jsonObj);
                        //删除掉原来的数据
                        //$('#cityId option:gt(0)').remove();
                        $('#cityId option:not(:first)').remove();
                        $(jsonObj).each(function () {
                            $('#cityId').append('<option value="'+this.id+'">'+this.name+'</option>');
                        })
                    },
                    'json'
                );
            });


        })
    </script>
</body>
</html>
