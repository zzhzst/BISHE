<!DOCTYPE html>
<html>
    <head>
        <title>进货单据查询</title>
        <#include "../common.ftl">
        <style type="text/css">
            .layui-table-cell {
                text-align: center;

                height: auto;

                white-space: normal;

            }

            .layui-table img {
                max-width: 300px
            }
        </style>

    </head>

    <body class="layuimini-container">
        <div class="layui-fluid">
            <div class="layui-row layui-col-space15">
                <!-- 左表 -->
                <div class="layui-col-sm12 layui-col-md4 layui-col-lg3" style="width: 55%">
                    <form class="layui-form">
                        <legend>进货单据查询</legend>
                        <br/>
                        <div class="layui-row">
                            <div class="layui-col-xs4">
                                <label class="layui-form-label">单据号</label>
                                <div class="layui-input-block">
                                    <input type="text" class="layui-input purchaseNumber"
                                           name="purchaseNumber" id="purchaseNumber">
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
                    <table id="purchaseList" class="layui-table" lay-filter="purchaseList"></table>


                    <script id="purchaseListBar" type="text/html">
                        <@security.authorize  access="hasAnyAuthority('3030002')">
                            {{#  if(d.checkState =="未审核"){ }}
                            <a class="layui-btn layui-btn-warm layui-btn-xs data-count-edit" id="check"
                               lay-event="check">审核</a>
                            {{#  } else { }}

                            {{#  } }}
                        </@security.authorize>
                        <a class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" id="edit" lay-event="search">货单</a>
                        <@security.authorize  access="hasAnyAuthority('3030001')">
                            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="del">删除</a>
                        </@security.authorize>
                    </script>
                </div>
                <!-- 右表 -->
                <div class="layui-col-sm12 layui-col-md8 layui-col-lg9" style="width:45%">

                    <form class="layui-form">
                        <legend>进货单商品信息</legend>
                        <br/>
                        <div class="layui-row">
                            <div class="layui-col-xs4">
                                <label class="layui-form-label">进货单号</label>
                                <div class="layui-input-block">
                                    <input type="text" class="layui-input purchaseNumber"
                                           name="purchaseNumber_" id="purchaseNumber_" readonly="readonly">
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
                                <label class="layui-form-label">进货金额</label>
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
                                            class="layui-icon">&#xe669;</i> 重置</a>
                            </div>
                        </div>
                    </form>
                    <table id="purchaseListGoods" class="layui-table"
                           lay-filter="purchaseListGoods"></table>
                </div>
            </div>
        </div>
        <script type="text/javascript" src="${ctx.contextPath}/js/purchase/purchase.search.js"></script>
    </body>
</html>