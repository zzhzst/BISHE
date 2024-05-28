<!DOCTYPE html>
<html>
    <head>
        <title>商品管理</title>
        <#include "../common.ftl">
        <link rel="stylesheet" href="${ctx.contextPath}/js/zTree_v3-3.5.32/css/zTreeStyle/zTreeStyle.css"
              type="text/css">
        <script type="text/javascript" src="${ctx.contextPath}/lib/jquery-3.4.1/jquery-3.4.1.min.js"></script>
        <script type="text/javascript" src="${ctx.contextPath}/js/zTree_v3-3.5.32/js/jquery.ztree.core.js"></script>
    </head>

    <body class="layuimini-container">
        <div class="layui-fluid">
            <div class="layui-row layui-col-space15">
                <!-- 左树 -->
                <@security.authorize  access="hasAnyAuthority('2030001')">
                    <div class="layui-col-sm12 layui-col-md4 layui-col-lg3" style="width: 12%">
                        <fieldset class="layui-elem-field layui-field-title">
                            <legend style="font-size: 15px">商品类别</legend>
                        </fieldset>

                        <div id="goodsTypeTree" class="ztree">
                        </div>
                        <input type="hidden" name="typeId"/>
                    </div>
                </@security.authorize>
                <!-- 右表 -->
                <div class="layui-col-sm12 layui-col-md8 layui-col-lg9" style="width: 88%">
                    <@security.authorize  access="hasAnyAuthority('2030001')">
                        <legend>搜索信息</legend>
                        <div style="margin: 10px 10px 10px 10px">
                            <form class="layui-form layui-form-pane" action="">
                                <div class="layui-form-item">
                                    <div class="layui-inline">
                                        <label class="layui-form-label">商品名称</label>
                                        <div class="layui-input-inline">
                                            <input type="text" name="goodsName" autocomplete="off"
                                                   class="layui-input">
                                        </div>
                                    </div>
                                    <a class="layui-btn search_btn" data-type="reload"><i
                                                class="layui-icon">&#xe615;</i> 搜索</a>
                                </div>
                            </form>
                        </div>
                    </@security.authorize>
                    <table id="goodsList" class="layui-table" lay-filter="goods"></table>

                    <script type="text/html" id="toolbarDemo">
                        <div class="layui-btn-container">
                            <@security.authorize  access="hasAnyAuthority('2030002')">
                                <a class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add">
                                    <i class="layui-icon">&#xe608;</i>
                                    添加商品
                                </a>
                            </@security.authorize>
                        </div>
                    </script>
                    <#--操作-->
                    <script id="goodsListBar" type="text/html">
                        <@security.authorize  access="hasAnyAuthority('2030003')">
                            <a class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" id="edit"
                               lay-event="edit">编辑</a>
                        </@security.authorize>
                        <@security.authorize  access="hasAnyAuthority('2030004')">
                            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete"
                               lay-event="del">删除</a>
                        </@security.authorize>
                    </script>
                </div>
            </div>
        </div>
        </div>


        <script type="text/javascript" src="${ctx.contextPath}/js/goods/goods.js"></script>
    </body>
</html>