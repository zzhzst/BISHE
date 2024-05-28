<!DOCTYPE html>
<html>
    <head>
        <#include "../common.ftl">
    </head>

    <body class="childrenBody">
        <form class="layui-form layuimini-form" style="width:80%;">
            <input name="id" type="hidden" value="${(goodsUnit.id)!}"/>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label required">单位名称</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input name"
                           lay-verify="required" name="name" id="name" value="${(goodsUnit.name)!}" placeholder="单位名称">
                </div>
            </div>
            <br/>
            <div class="layui-form-item layui-row layui-col-xs12">
                <div class="layui-input-block">
                    <button class="layui-btn layui-btn-lg" lay-submit=""
                            lay-filter="addOrUpdateGoodsUnit">确认
                    </button>
                    <a class="layui-btn layui-btn-lg layui-btn-normal" id="closeDlg" href="javascript:void(0)">取消</a>
                </div>
            </div>
        </form>
        <script type="text/javascript" src="${ctx.contextPath}/js/goodsUnit/add.update.js"></script>
    </body>
</html>