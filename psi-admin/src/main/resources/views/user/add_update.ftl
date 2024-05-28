<!DOCTYPE html>
<html>
    <head>
        <#include "../common.ftl">
    </head>

    <body class="childrenBody">
        <form class="layui-form layuimini-form" style="width:80%;">
            <input name="id" type="hidden" value="${(user.id)!}"/>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label required">用户名</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input userName"
                           lay-verify="required" name="userName" id="userName" value="${(user.username)!}"
                           placeholder="请输入用户名">
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">真实姓名</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input userName"
                           name="trueName" id="trueName" value="${(user.trueName)!}" placeholder="请输入真实姓名">
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">手机</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input"
                           name="phone" value="${(user.phone)!}"
                           id="phone"
                           placeholder="请输入手机号">
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-block">
                    <input type="radio" name="sex" value="男" title="男">
                    <input type="radio" name="sex" value="女" title="女">
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input userEmail"
                           name="remarks" value="${(user.remarks)!}" id="remarks" placeholder="请输入备注信息">
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">角色</label>
                <div class="layui-input-block">
                    <select name="roleIds" xm-select="selectId">
                    </select>
                </div>
            </div>
            <br/>
            <div class="layui-form-item layui-row layui-col-xs12">
                <div class="layui-input-block">
                    <button class="layui-btn layui-btn-lg" lay-submit lay-filter="addOrUpdateUser">确认
                    </button>
                    <a class="layui-btn layui-btn-lg layui-btn-normal" id="closeDlg" href="javascript:void(0)">取消</a>
                </div>
            </div>
        </form>
        <script type="text/javascript" src="${ctx.contextPath}/js/user/add.update.js"></script>
        <script>
            layui.use(['layer', 'form'], function () {
                var form = layui.form;
                var $ = layui.jquery;
                if("${(user.sex)!}" !=null && "${(user.sex)!}" !=""){
                    $('input[name="sex"][value="${(user.sex)!}"]').prop('checked','checked');
                    form.render('radio');
                }else{
                    $('input[name="sex"][value="女"]').prop('checked','checked');
                    form.render('radio');
                }
            });
        </script>
    </body>
</html>