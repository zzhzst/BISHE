<!DOCTYPE html>
<html>
    <head>
        <title>库存报警</title>
        <#include "../common.ftl">
        <link rel="stylesheet" href="${ctx.contextPath}/js/zTree_v3-3.5.32/css/zTreeStyle/zTreeStyle.css"
              type="text/css">
        <script type="text/javascript" src="${ctx.contextPath}/lib/jquery-3.4.1/jquery-3.4.1.min.js"></script>
        <script type="text/javascript" src="${ctx.contextPath}/js/zTree_v3-3.5.32/js/jquery.ztree.core.js"></script>
    </head>

    <body class="layuimini-container">
        <div class="layuimini-main">
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
            <table id="goodsList" class="layui-table" lay-filter="goods"></table>
        </div>
        <script type="text/javascript" src="${ctx.contextPath}/js/common/alarm.js"></script>
    </body>
</html>