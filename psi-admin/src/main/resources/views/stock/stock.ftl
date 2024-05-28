<!DOCTYPE html>
<html>
    <head>
        <title>期初库存</title>
        <#include "../common.ftl">
    </head>

    <body class="layuimini-container">
        <div class="layui-fluid">
            <div class="layui-row layui-col-space15">
                <!-- 左表 -->
                <div class="layui-col-sm12 layui-col-md4 layui-col-lg3" style="width: 45%">
                    <@security.authorize  access="hasAnyAuthority('2060001')">
                        <legend>商品信息---搜索信息</legend>
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
                    <table id="goodsList01" class="layui-table" lay-filter="goods01"></table>

                    <script id="goodsListBar01" type="text/html">
                        <@security.authorize  access="hasAnyAuthority('2060002')">
                            <a class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" id="edit"
                               lay-event="edit">添加商品到仓库</a>
                        </@security.authorize>
                    </script>
                </div>
                <!-- 右表 -->
                <div class="layui-col-sm12 layui-col-md8 layui-col-lg9" style="width: 55%">
                    <@security.authorize  access="hasAnyAuthority('2060003')">
                        <legend>期初库存---搜索信息</legend>
                        <div style="margin: 10px 10px 10px 10px">
                            <form class="layui-form layui-form-pane" action="">
                                <div class="layui-form-item">
                                    <div class="layui-inline">
                                        <label class="layui-form-label">商品名称</label>
                                        <div class="layui-input-inline">
                                            <input type="text" name="goodsName2" autocomplete="off"
                                                   class="layui-input">
                                        </div>
                                    </div>
                                    <a class="layui-btn search_btn2" data-type="reload"><i
                                                class="layui-icon">&#xe615;</i> 搜索</a>
                                </div>
                            </form>
                        </div>
                    </@security.authorize>
                    <table id="goodsList02" class="layui-table" lay-filter="goods02"></table>

                    <#--操作-->
                    <script id="goodsListBar02" type="text/html">
                        <@security.authorize  access="hasAnyAuthority('2060004')">
                            <a class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" id="edit"
                               lay-event="edit">修改库存或成本</a>
                        </@security.authorize>
                        <@security.authorize  access="hasAnyAuthority('2060005')">
                            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete"
                               lay-event="del">删除</a>
                        </@security.authorize>
                    </script>
                    </form>
                </div>
            </div>
            <script type="text/javascript" src="${ctx.contextPath}/js/stock/stock.js"></script>
        </div>
    </body>
</html>