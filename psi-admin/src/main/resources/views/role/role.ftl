<!DOCTYPE html>
<html>
    <head>
        <title>角色管理</title>
        <#include "../common.ftl">
    </head>
    <body class="layuimini-container">
        <div class="layuimini-main">
            <@security.authorize  access="hasAnyAuthority('102001')">
                <legend>搜索信息</legend>
                <div style="margin: 10px 10px 10px 10px">
                    <form class="layui-form layui-form-pane" action="">
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label">角色名</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="roleName" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <a class="layui-btn search_btn" data-type="reload"><i
                                        class="layui-icon">&#xe615;</i> 搜索</a>
                        </div>
                    </form>
                </div>
            </@security.authorize>

            <table id="roleList" class="layui-table" lay-filter="roles"></table>

            <script type="text/html" id="toolbarDemo">
                <div class="layui-btn-container">
                    <@security.authorize  access="hasAnyAuthority('102002')">
                        <a class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add">
                            <i class="layui-icon">&#xe608;</i>
                            添加角色
                        </a>
                    </@security.authorize>
                    <@security.authorize  access="hasAnyAuthority('102005')">
                        <a class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="grant">
                            <i class="layui-icon">&#xe672;</i>
                            授权
                        </a>
                    </@security.authorize>
                    <@security.authorize  access="hasAnyAuthority('102004')">
                        <a class="layui-btn layui-btn-sm layui-btn-danger data-delete-btn" lay-event="del">
                            <i class="layui-icon">&#xe67e;</i>
                            删除角色
                        </a>
                    </@security.authorize>
                </div>
            </script>
            <!--操作-->
            <script id="roleListBar" type="text/html">
                <@security.authorize  access="hasAnyAuthority('102003')">
                    <a class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" id="edit" lay-event="edit">编辑</a>
                </@security.authorize>
                <@security.authorize  access="hasAnyAuthority('102004')">
                    <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="del">删除</a>
                </@security.authorize>
            </script>
        </div>
        <script type="text/javascript" src="${ctx.contextPath}/js/role/role.js"></script>

    </body>
</html>