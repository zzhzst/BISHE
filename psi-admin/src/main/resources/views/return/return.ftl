<!DOCTYPE html>
<html>
    <head>
        <title>退货出库</title>
        <#include "../common.ftl">
    </head>
    <body class="layuimini-container">

        <form class="layui-form">
            <input type="hidden" name="returnNumber" value="${returnNumber!}">
            <input type="hidden" name="goodsJson">
            <br/>
            <fieldset class="layui-elem-field site-demo-button">
                <br/>
                <legend>单号:${returnNumber!}</legend>
                <div class="layui-row">
                    <div class="layui-col-xs4">
                        <label class="layui-form-label" style="color: red">供应商*</label>
                        <div class="layui-input-block">
                            <select id="supplierId" name="supplierId">
                                <option value="0">请选择</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-col-xs4">
                        <label class="layui-form-label">应付金额</label>
                        <div class="layui-input-block">
                            <input type="text" class="layui-input amountPayable"
                                   name="amountPayable" id="amountPayable">
                        </div>
                    </div>
                    <div class="layui-col-xs4">
                        <label class="layui-form-label">实付金额</label>
                        <div class="layui-input-block">
                            <input type="text" class="layui-input amountPaid"
                                   name="amountPaid" id="amountPaid">
                        </div>
                    </div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs4">
                        <label class="layui-form-label" style="color: red">收货日期*</label>
                        <div class="layui-input-block">
                            <input type="text" name="returnDate" id="returnDate" lay-verify="returnDate"
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
                    <div class="layui-col-xs3">
                        <label class="layui-form-label">是否付款</label>
                        <div class="layui-input-block">
                            <select id="state" name="state" class="select">
                                <option value="1">已付</option>
                                <option value="0">未付</option>
                            </select>
                        </div>
                    </div>
                    <button class="layui-btn layui-btn" lay-submit=""
                            lay-filter="addReturnList">保存
                    </button>
                </div>
            </fieldset>
            <table id="returnList" class="layui-table" lay-filter="returns"></table>

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
            <script type="text/javascript" src="${ctx.contextPath}/js/return/return.js"></script>
        </form>
    </body>
</html>