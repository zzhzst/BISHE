<!DOCTYPE html>
<html>
    <head>
        <title>商品采购统计</title>
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
                <div class="layui-col-sm12 layui-col-md4 layui-col-lg3" style="width: 12%">

                    <div class="layui-card-body">
                        <fieldset class="layui-elem-field layui-field-title">
                            <legend style="font-size: 15px">商品类别</legend>
                        </fieldset>

                        <div id="goodsTypeTree" class="ztree">
                        </div>
                        <input type="hidden" name="typeId"/>
                    </div>
                </div>
                <!-- 右表 -->
                <div class="layui-col-sm12 layui-col-md8 layui-col-lg9" style="width: 88%">
                    <div class="layui-row">
                        <form class="layui-form">
                            <form class="layui-form">
                                <fieldset class="layui-elem-field site-demo-button">
                                    <legend>采购统计设置</legend>
                                    <div style="margin: 10px 10px 10px 10px">
                                        <form class="layui-form layui-form-pane" action="">
                                            <div class="layui-form-item">
                                                <div class="layui-col-xs3">
                                                    <label class="layui-form-label">开始日期</label>
                                                    <div class="layui-input-block">
                                                        <input type="text" name="startDate" id="startDate"
                                                               lay-verify="startDate"
                                                               placeholder="yyyy-MM-dd" autocomplete="off"
                                                               readonly="readonly"
                                                               class="layui-input">
                                                    </div>
                                                </div>
                                                <div class="layui-col-xs3">
                                                    <label class="layui-form-label">结束日期</label>
                                                    <div class="layui-input-block">
                                                        <input type="text" name="endDate" id="endDate"
                                                               lay-verify="endDate"
                                                               placeholder="yyyy-MM-dd" autocomplete="off"
                                                               readonly="readonly"
                                                               class="layui-input">
                                                    </div>
                                                </div>
                                                <div class="layui-col-xs3">
                                                    <label class="layui-form-label">商品名称</label>
                                                    <div class="layui-input-block">
                                                        <input type="text" name="goodsName" id="goodsName"
                                                               class="layui-input">
                                                    </div>
                                                </div>
                                                <a class="layui-btn search_btn" data-type="reload"><i
                                                            class="layui-icon">&#xe615;</i> 搜索</a>
                                            </div>
                                        </form>
                                    </div>
                                </fieldset>
                            </form>
                            <table id="purchaseList" class="layui-table" lay-filter="purchaseList"></table>
                            <script id="purchaseListBar" type="text/html">
                                <a class="layui-btn layui-btn-xs" id="edit" lay-event="search">货单</a>
                            </script>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript" src="${ctx.contextPath}/js/count/purchase.js"></script>
    </body>
</html>