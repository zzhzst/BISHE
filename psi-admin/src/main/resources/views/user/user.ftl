<!DOCTYPE html>
<html>
    <head>
        <title>用户管理</title>
        <#include "../common.ftl">
    </head>

    <body class="layuimini-container">
        <div class="layuimini-main">
            <@security.authorize  access="hasAnyAuthority('101003')">
                <legend>搜索信息</legend>
                <div style="margin: 10px 10px 10px 10px">
                    <form class="layui-form layui-form-pane" action="">
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label">用户姓名</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="userName" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label">真实姓名</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="trueName" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label">性别</label>
                                <div class="layui-input-block">
                                    <select id="sex" name="sex">
                                        <option value="">请选择</option>
                                        <option value="男">男</option>
                                        <option value="女">女</option>
                                    </select>
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label">手机号</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="phone" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <a class="layui-btn search_btn" data-type="reload"><i
                                        class="layui-icon">&#xe615;</i> 搜索</a>
                        </div>
                    </form>
                </div>
            </@security.authorize>
            <table id="userList" class="layui-table" lay-filter="users"></table>
            <script type="text/html" id="toolbarDemo">
                <div class="layui-btn-container">
                    <@security.authorize  access="hasAnyAuthority('101004')">
                        <a class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add">
                            <i class="layui-icon">&#xe608;</i>
                            添加用户
                        </a>
                    </@security.authorize>
                    <@security.authorize  access="hasAnyAuthority('101006')">
                        <a class="layui-btn layui-btn-sm layui-btn-danger data-delete-btn" lay-event="del">
                            <i class="layui-icon">&#xe67e;</i>
                            删除用户
                        </a>
                    </@security.authorize>
                </div>
            </script>
            <!--操作-->
            <script id="userListBar" type="text/html">
                <@security.authorize  access="hasAnyAuthority('101005')">
                    <a class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" id="edit" lay-event="edit">编辑</a>
                </@security.authorize>
                <@security.authorize  access="hasAnyAuthority('101006')">
                    <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="del">删除</a>
                </@security.authorize>
            </script>
        </div>
        <script type="text/javascript" src="${ctx.contextPath}/js/user/user.js"></script>

    </body>
</html>