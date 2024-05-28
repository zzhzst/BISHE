layui.use(['form', 'layuimini'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery;

    //监听提交
    form.on('submit(saveBtn)', function (data) {
        $.ajax({
            type: "post",
            url: ctx + "/user/updateUserInfo",
            data: data.field,
            dataType: "json",
            success: function (data) {
                layer.msg(data.message);
            }
        });
        return false;

    });
});

//文件上传
layui.use(['upload', 'element', 'layer'], function () {
    var $ = layui.jquery, element = layui.element
        , upload = layui.upload;

    //常规使用 - 普通图片上传
    var uploadInst = upload.render({
        elem: '#test1'
        , url: ctx + "/user/uploadFile"
        , before: function (obj) {
            //预读本地文件示例，不支持ie8
            obj.preview(function (index, file, result) {
                $('#demo1').attr('src', result); //图片链接（base64）
            });

            element.progress('demo', '0%'); //进度条复位
            layer.msg('上传中', {icon: 16, time: 0});
        }
        , done: function (res) {
            //如果上传失败
            if (res.code > 0) {
                return layer.msg('上传失败');
                return;
            }
            layer.msg('上传成功');
            //上传成功的一些操作
            var tx = $('#tx');
            tx.val(res.msg);
            $('#demoText').html(''); //置空上传失败的状态
        }, error: function () {
            //演示失败状态，并实现重传
            var demoText = $('#demoText');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function () {
                uploadInst.upload();
            });
        }
    });

});