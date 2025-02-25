<!DOCTYPE html>
<html>
    <head>
        <title>商品报溢</title>
        <#include "../common.ftl">
    </head>
    <body class="layuimini-container">

        <form class="layui-form layuimini-form">
            <input type="hidden" name="overflowNumber" value="${overflowNumber!}">
            <input type="hidden" name="goodsJson">
            <#--            <div class="layui-card">
                            <div class="layui-card-body">
                                <div class="layuimini-main">-->
            <br/>
            <fieldset class="layui-elem-field site-demo-button">
                <br/>
                <legend>单号:${overflowNumber!}</legend>
                <div class="layui-row">
                    <div class="layui-col-xs4">
                        <label class="layui-form-label" style="color: red">开单日期*</label>
                        <div class="layui-input-block">
                            <input type="text" name="overflowDate" id="overflowDate"
                                   lay-verify="overflowDate"
                                   placeholder="yyyy-MM-dd" autocomplete="off" readonly="readonly"
                                   class="layui-input">
                        </div>
                    </div>
                    <div class="layui-col-xs4">
                        <label class="layui-form-label">备注</label>
                        <div class="layui-input-block">
                            <input type="text" class="layui-input remarks"
                                   name="remarks" id="remarks">
                        </div>
                    </div>
                    <div class="layui-col-xs4">
                        <label class="layui-form-label"></label>
                        <button class="layui-btn layui-btn-lg" lay-submit=""
                                lay-filter="addOverflowList">保存
                        </button>
                    </div>

                </div>
            </fieldset>
            <table id="overflowList" class="layui-table" lay-filter="overflows"></table>

            <#--操作-->
            <script id="goodsListBar" type="text/html">
                <a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
                <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
            </script>


            <script type="text/html" id="toolbarDemo">
                <div class="layui-btn-container">
                    <a class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add">
                        <i class="layui-icon">&#xe608;</i>
                        添加
                    </a>
                </div>
            </script>
            <#--                    </div>
                            </div>
                        </div>-->
            <script type="text/javascript" src="${ctx.contextPath}/js/overflow/overflow.js"></script>
        </form>
    </body>
</html>