<!DOCTYPE html>
<html>
    <head>
        <title>退货单据查询</title>
        <#include "../common.ftl">
    </head>
    <body class="layuimini-container">

        <div class="layui-fluid">
            <div class="layui-row layui-col-space15">
                <!-- 左表 -->
                <div class="layui-col-sm12 layui-col-md4 layui-col-lg3" style="width: 55%">
                    <form class="layui-form">
                        <legend>退货单据查询</legend>
                        <br/>
                        <div class="layui-row">
                            <div class="layui-col-xs4">
                                <label class="layui-form-label">单据号</label>
                                <div class="layui-input-block">
                                    <input type="text" class="layui-input returnNumber"
                                           name="returnNumber" id="returnNumber">
                                </div>
                            </div>
                            <div class="layui-col-xs4">
                                <label class="layui-form-label">供应商</label>
                                <div class="layui-input-block">
                                    <select id="supplierId" name="supplierId">
                                        <option value="">请选择</option>
                                    </select>
                                </div>
                            </div>
                            <div class="layui-col-xs4">
                                <label class="layui-form-label">是否付款</label>
                                <div class="layui-input-block">
                                    <select id="state" name="state" class="select">
                                        <option value="">全部</option>
                                        <option value="1">已付</option>
                                        <option value="0">未付</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <br/>
                        <div class="layui-row">
                            <div class="layui-col-xs4">
                                <label class="layui-form-label">开始日期</label>
                                <div class="layui-input-block">
                                    <input type="text" name="startDate" id="startDate"
                                           lay-verify="startDate"
                                           placeholder="yyyy-MM-dd" autocomplete="off" readonly="readonly"
                                           class="layui-input">
                                </div>
                            </div>

                            <div class="layui-col-xs4">
                                <label class="layui-form-label">结束日期</label>
                                <div class="layui-input-block">
                                    <input type="text" name="endDate" id="endDate" lay-verify="endDate"
                                           placeholder="yyyy-MM-dd" autocomplete="off" readonly="readonly"
                                           class="layui-input">
                                </div>
                            </div>
                            <div class="layui-col-xs4">
                                <label class="layui-form-label"></label>
                                <a class="layui-btn search_btn" data-type="reload"><i
                                            class="layui-icon">&#xe615;</i> 搜索</a>
                            </div>
                        </div>
                    </form>

                    <table id="returnList" class="layui-table" lay-filter="returnList"></table>

                    <script id="returnListBar" type="text/html">
                        <a class="layui-btn layui-btn-xs" id="edit" lay-event="search">货单</a>
                        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
                    </script>
                    </form>
                </div>
                <!-- 右表 -->
                <div class="layui-col-sm12 layui-col-md8 layui-col-lg9" style="width: 45%">

                    <form class="layui-form">
                        <legend>退货单商品信息</legend>
                        <br/>
                        <div class="layui-row">
                            <div class="layui-col-xs4">
                                <label class="layui-form-label">退货单号</label>
                                <div class="layui-input-block">
                                    <input type="text" class="layui-input returnNumber"
                                           name="returnNumber_" id="returnNumber_" readonly="readonly">
                                </div>
                            </div>
                            <div class="layui-col-xs4">
                                <label class="layui-form-label">供应商</label>
                                <div class="layui-input-block">
                                    <input type="text" class="layui-input purchaseNumber"
                                           name="supplierName_" id="supplierName_" readonly="readonly">
                                </div>
                            </div>
                            <div class="layui-col-xs4">
                                <label class="layui-form-label">退货金额</label>
                                <div class="layui-input-block">
                                    <input type="text" class="layui-input purchaseNumber"
                                           name="amountPayable_" id="amountPayable_" readonly="readonly">
                                </div>
                            </div>
                        </div>
                        <br/>
                        <div class="layui-row">
                            <div class="layui-col-xs4">
                                <label class="layui-form-label">支付状态</label>
                                <div class="layui-input-block">
                                    <input type="state_" id="state_" class="layui-input"
                                           readonly="readonly">
                                </div>
                            </div>

                            <div class="layui-col-xs4">
                                <label class="layui-form-label">操作员</label>
                                <div class="layui-input-block">
                                    <input type="text" name="userName_" id="userName_" class="layui-input"
                                           readonly="readonly">
                                </div>
                            </div>
                            <div class="layui-col-xs4">
                                <label class="layui-form-label"></label>
                                <a class="layui-btn search_btn02" data-type="reload"><i
                                            class="layui-icon">&#xe615;</i> 重置</a>
                            </div>
                        </div>
                        </fieldset>
                    </form>
                    <table id="returnListGoods" class="layui-table" lay-filter="returnListGoods"></table>
                </div>
            </div>
        </div>

        <script type="text/javascript" src="${ctx.contextPath}/js/return/return.search.js"></script>
    </body>
</html>