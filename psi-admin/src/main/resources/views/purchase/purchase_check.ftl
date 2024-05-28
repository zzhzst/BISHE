<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>进货单审核</title>
        <#include "../common.ftl">
        <style>
            .layui-form-item .layui-input-company {
                width: auto;
                padding-right: 10px;
                line-height: 38px;
            }
        </style>
    </head>
    <body>
        <div class="layuimini-container">
            <div class="layuimini-main">
                <div class="layui-form layuimini-form">
                    <div class="layui-form-item">
                        <label class="layui-form-label required">进货单号</label>
                        <div class="layui-input-block">
                            <input type="text" name="purchaseNumber"
                                   readonly="readonly" value="${(purchase.purchaseNumber)!}" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">电子签名</label>
                        <div class="layui-upload">
                            <button type="button" class="layui-btn" id="test1">上传电子签名</button>
                            <tip>审核同意需要电子签名，审核不同意不需要电子签名</tip>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">预览图</label>
                        <div class="layui-upload-list">
                            <img class="layui-upload-img" id="demo1" style="width: 92px;height: 92px"
                                 src="../images/${(purchase.check)!}">
                            <p id="demoText"></p>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">备注</label>
                        <div class="layui-input-block">
                            <input type="text" name="remarks"
                                   value="${(purchase.remarks)!}" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <input type="hidden" name="id" value="${(purchase.id)!}">
                            <input type="hidden" name="checkState" id="checkState" value="${(purchase.checkState)!}">
                            <button class="layui-btn layui-btn-normal" lay-submit lay-filter="saveBtn">审核同意</button>
                            <button class="layui-btn layui-btn-normal" lay-submit lay-filter="saveBtn1">审核不同意</button>
                            <a class="layui-btn layui-btn-lg layui-btn-normal" id="closeDlg" href="javascript:void(0)">取消</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript" src="${ctx.contextPath}/js/purchase/purchase_check.js"></script>
    </body>
</html>