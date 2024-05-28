<!DOCTYPE html>
<html>
    <head>
        <title>商品类别管理</title>
        <#include "../common.ftl">
    </head>
    <body class="layuimini-container">
        <table id="goods-type-table" class="layui-table" lay-filter="goods-type-table"></table>

        <!-- 操作列 -->
        <script type="text/html" id="auth-state">
            <@security.authorize  access="hasAnyAuthority('2040002')">
                <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="add">子类别</a>
            </@security.authorize>
            <@security.authorize  access="hasAnyAuthority('2030003')">
                <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
            </@security.authorize>
        </script>


        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <@security.authorize  access="hasAnyAuthority('2040001')">
                    <a class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="expand">
                        <i class="layui-icon">&#xe608;</i>
                        全部展开
                    </a>
                    <a class="layui-btn layui-btn-sm layui-btn-danger data-delete-btn" lay-event="fold">
                        <i class="layui-icon">&#xe67e;</i>
                        全部折叠
                    </a>
                </@security.authorize>
            </div>
        </script>

        <script type="text/javascript" src="${ctx.contextPath}/js/goodsType/goods.type.js"></script>
    </body>
</html>