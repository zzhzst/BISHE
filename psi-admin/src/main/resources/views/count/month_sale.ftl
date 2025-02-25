<!DOCTYPE html>
<html>
    <head>
        <title>日销售统计</title>
        <#include "../common.ftl">
    </head>

    <body class="layuimini-container">
        <div class="layui-fluid">
            <form class="layui-form">
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <label class="layui-form-label">开始日期</label>
                        <div class="layui-input-block">
                            <input type="text" name="startDate" id="startDate" lay-verify="startDate"
                                   placeholder="yyyy-MM-dd" autocomplete="off" readonly="readonly" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-input-inline">
                        <label class="layui-form-label">结束日期</label>
                        <div class="layui-input-block">
                            <input type="text" name="endDate" id="endDate" lay-verify="endDate"
                                   placeholder="yyyy-MM-dd" autocomplete="off" readonly="readonly" class="layui-input">
                        </div>
                    </div>
                    <a class="layui-btn search_btn" data-type="reload"><i
                                class="layui-icon">&#xe615;</i> 搜索</a>
                </div>
            </form>
            <table id="monthSale" class="layui-table" lay-filter="monthSale"></table>
            <script type="text/html" id="toolbarDemo"></script>
        </div>
        <script type="text/javascript" src="${ctx.contextPath}/js/count/month.sale.js"></script>
    </body>
</html>