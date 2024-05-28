<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>基本资料</title>
        <#include "../common.ftl">
        <style>
            .layui-form-item .layui-input-company {
                width: auto;
                padding-right: 10px;
                line-height: 38px;
            }
        </style>
    </head>
    <body>
        <div class="layuimini-container">
            <div class="layuimini-main">
                <div class="layui-form layuimini-form">
                    <div class="layui-form-item">
                        <label class="layui-form-label required">管理账号(用户名)</label>
                        <div class="layui-input-block">
                            <input type="text" name="userName" lay-verify="required" lay-reqtext="管理账号不能为空"
                                   placeholder="请输入管理账号" value="${(user.username)!'xxx'}" class="layui-input">
                            <tip>填写自己管理账号（用户名）的名称。</tip>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">真实姓名</label>
                        <div class="layui-input-block">
                            <input type="text" name="trueName" placeholder="请输入真实姓名" value="${(user.trueName)!}"
                                   class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">性别</label>
                        <div class="layui-input-block">
                            <input type="radio" name="sex" value="男" title="男">
                            <input type="radio" name="sex" value="女" title="女">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">手机号</label>
                        <div class="layui-input-block">
                            <input type="text" name="bz" lay-verify="required" lay-reqtext="手机不能为空"
                                   placeholder="请输入手机号" value="${(user.phone)!}" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item layui-form-text">
                        <label class="layui-form-label">备注信息</label>
                        <div class="layui-input-block">
                            <input type="text" name="remarks" placeholder="请输入备注信息" value="${(user.remarks)!}"
                                   class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">头像</label>
                        <div class="layui-upload">
                            <button type="button" class="layui-btn" id="test1">上传图片</button>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">预览图</label>
                        <div class="layui-upload-list">
                            <img class="layui-upload-img" id="demo1" style="width: 92px;height: 92px"
                                 src="../images/${(user.tx)!}">
                            <p id="demoText"></p>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <input type="hidden" name="id" value="${(user.id)!}">
                            <input type="hidden" name="tx" id="tx" value="${(user.tx)!}">
                            <button class="layui-btn layui-btn-normal" lay-submit lay-filter="saveBtn">确认保存</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript" src="${ctx.contextPath}/js/user/setting.js"></script>
        <script>
            layui.use(['layer', 'form'], function () {
                var form = layui.form;
                var $ = layui.jquery;

                $('input[name="sex"][value="${(user.sex)!}"]').prop('checked','checked');
                form.render('radio');
            });
        </script>
    </body>
</html>