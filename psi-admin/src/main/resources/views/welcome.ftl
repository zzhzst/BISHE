<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>首页二</title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <#include "./common.ftl">
        <style>
            .layui-card {
                border: 1px solid #f2f2f2;
                border-radius: 5px;
            }

            .icon {
                margin-right: 10px;
                color: #1aa094;
            }

            .icon-cray {
                color: #ffb800 !important;
            }

            .icon-blue {
                color: #1e9fff !important;
            }

            .icon-tip {
                color: #ff5722 !important;
            }

            .layuimini-qiuck-module {
                text-align: center;
                margin-top: 10px
            }

            .layuimini-qiuck-module a i {
                display: inline-block;
                width: 100%;
                height: 60px;
                line-height: 60px;
                text-align: center;
                border-radius: 2px;
                font-size: 30px;
                background-color: #F8F8F8;
                color: #333;
                transition: all .3s;
                -webkit-transition: all .3s;
            }

            .layuimini-qiuck-module a cite {
                position: relative;
                top: 2px;
                display: block;
                color: #666;
                text-overflow: ellipsis;
                overflow: hidden;
                white-space: nowrap;
                font-size: 14px;
            }

            .welcome-module {
                width: 100%;
                height: 210px;
            }

            .panel {
                background-color: #fff;
                border: 1px solid transparent;
                border-radius: 3px;
                -webkit-box-shadow: 0 1px 1px rgba(0, 0, 0, .05);
                box-shadow: 0 1px 1px rgba(0, 0, 0, .05)
            }

            .panel-body {
                padding: 10px
            }

            .panel-title {
                margin-top: 0;
                margin-bottom: 0;
                font-size: 12px;
                color: inherit
            }

            .label {
                display: inline;
                padding: .2em .6em .3em;
                font-size: 75%;
                font-weight: 700;
                line-height: 1;
                color: #fff;
                text-align: center;
                white-space: nowrap;
                vertical-align: baseline;
                border-radius: .25em;
                margin-top: .3em;
            }

            .layui-red {
                color: red
            }

            .main_btn > p {
                height: 40px;
            }

            .layui-bg-number {
                background-color: #F8F8F8;
            }

            .layuimini-notice:hover {
                background: #f6f6f6;
            }

            .layuimini-notice {
                padding: 7px 16px;
                clear: both;
                font-size: 12px !important;
                cursor: pointer;
                position: relative;
                transition: background 0.2s ease-in-out;
            }

            .layuimini-notice-title, .layuimini-notice-label {
                padding-right: 70px !important;
                text-overflow: ellipsis !important;
                overflow: hidden !important;
                white-space: nowrap !important;
            }

            .layuimini-notice-title {
                line-height: 28px;
                font-size: 14px;
            }

            .layuimini-notice-extra {
                position: absolute;
                top: 50%;
                margin-top: -8px;
                right: 16px;
                display: inline-block;
                height: 16px;
                color: #999;
            }
        </style>
    </head>
    <body>
        <div class="layuimini-container">
            <div class="layuimini-main">
                <div class="layui-row layui-col-space15">
                    <div class="layui-col-md8">
                        <div class="layui-row layui-col-space15">
                            <div class="layui-col-md12">
                                <div class="layui-card">
                                    <div class="layui-card-header"><i class="fa fa-warning icon"></i>数据统计</div>
                                    <div class="layui-card-body">
                                        <div class="welcome-module">
                                            <div class="layui-row layui-col-space10">
                                                <div class="layui-col-xs3">
                                                    <div class="panel layui-bg-number">
                                                        <div class="panel-body">
                                                            <div class="panel-title">
                                                                <span class="label pull-right layui-bg-blue">实时</span>
                                                                <h5>用户统计</h5>
                                                            </div>
                                                            <div class="panel-content">
                                                                <h1 class="no-margins">
                                                                    1234
                                                                </h1>
                                                                <small>当前分类总记录数</small>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="layui-col-xs3">
                                                    <div class="panel layui-bg-number">
                                                        <div class="panel-body">
                                                            <div class="panel-title">
                                                                <span class="label pull-right layui-bg-cyan">实时</span>
                                                                <h5>角色统计</h5>
                                                            </div>
                                                            <div class="panel-content">
                                                                <h1 class="no-margins">
                                                                    1234
                                                                </h1>
                                                                <small>当前分类总记录数</small>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="layui-col-xs3">
                                                    <div class="panel layui-bg-number">
                                                        <div class="panel-body">
                                                            <div class="panel-title">
                                                                <span class="label pull-right layui-bg-orange">实时</span>
                                                                <h5>菜单统计</h5>
                                                            </div>
                                                            <div class="panel-content">
                                                                <h1 class="no-margins">
                                                                    1234
                                                                </h1>
                                                                <small>当前分类总记录数</small>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="layui-col-xs3">
                                                    <div class="panel layui-bg-number">
                                                        <div class="panel-body">
                                                            <div class="panel-title">
                                                                <span class="label pull-right layui-bg-green">实时</span>
                                                                <h5>供应商统计</h5>
                                                            </div>
                                                            <div class="panel-content">
                                                                <h1 class="no-margins">
                                                                    1234
                                                                </h1>
                                                                <small>当前分类总记录数</small>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="layui-col-xs3">
                                                    <div class="panel layui-bg-number">
                                                        <div class="panel-body">
                                                            <div class="panel-title">
                                                                <span class="label pull-right layui-bg-blue">实时</span>
                                                                <h5>客户统计</h5>
                                                            </div>
                                                            <div class="panel-content">
                                                                <h1 class="no-margins">
                                                                    1234
                                                                </h1>
                                                                <small>当前分类总记录数</small>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="layui-col-xs3">
                                                    <div class="panel layui-bg-number">
                                                        <div class="panel-body">
                                                            <div class="panel-title">
                                                                <span class="label pull-right layui-bg-cyan">实时</span>
                                                                <h5>商品统计</h5>
                                                            </div>
                                                            <div class="panel-content">
                                                                <h1 class="no-margins">1234</h1>
                                                                <small>当前分类总记录数</small>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="layui-col-xs3">
                                                    <div class="panel layui-bg-number">
                                                        <div class="panel-body">
                                                            <div class="panel-title">
                                                                <span class="label pull-right layui-bg-orange">实时</span>
                                                                <h5>今日进货单据统计</h5>
                                                            </div>
                                                            <div class="panel-content">
                                                                <h1 class="no-margins">1234</h1>
                                                                <small>当前分类总记录数</small>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="layui-col-xs3">
                                                    <div class="panel layui-bg-number">
                                                        <div class="panel-body">
                                                            <div class="panel-title">
                                                                <span class="label pull-right layui-bg-green">实时</span>
                                                                <h5>今日销售单据统计</h5>
                                                            </div>
                                                            <div class="panel-content">
                                                                <h1 class="no-margins">1234</h1>
                                                                <small>当前分类总记录数</small>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="layui-col-md12">
                                <div class="layui-card">
                                    <div class="layui-card-header"><i class="fa fa-line-chart icon"></i>报表统计</div>
                                    <div class="layui-card-body">
                                        <div id="echarts-records" style="width: 100%;min-height:600px"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="layui-col-md4">

                        <div class="layui-card">
                            <div class="layui-card-header"><i class="fa fa-bullhorn icon icon-tip"></i>系统公告</div>
                            <div class="layui-card-body layui-text">
                                <div class="layuimini-notice">
                                    <div class="layuimini-notice-title">增加配色方案</div>
                                    <div class="layuimini-notice-extra">2023-03-11 23:06</div>
                                    <div class="layuimini-notice-content layui-hide">
                                        增加配色方案<br>
                                        不同用户对界面的颜色喜欢不同，增加配色模式使用户的选择增加<br>
                                    </div>
                                </div>
                                <div class="layuimini-notice">
                                    <div class="layuimini-notice-title">新增系统404模板</div>
                                    <div class="layuimini-notice-extra">2023-03-10 12:57</div>
                                    <div class="layuimini-notice-content layui-hide">
                                        访问找不到资源时，应转到404模板<br>
                                    </div>
                                </div>
                                <div class="layuimini-notice">
                                    <div class="layuimini-notice-title">修复页面操作关闭当前页面无效</div>
                                    <div class="layuimini-notice-extra">2023-03-09 14:28</div>
                                    <div class="layuimini-notice-content layui-hide">
                                        layuimini.js中只有对关闭其他和关闭所有进行实现<br>
                                        通过修改layuimini.js，增加关闭当前页面的实现<br>
                                    </div>
                                </div>
                                <div class="layuimini-notice">
                                    <div class="layuimini-notice-title">界面图标美化</div>
                                    <div class="layuimini-notice-extra">2023-03-08 11:02</div>
                                    <div class="layuimini-notice-content layui-hide">
                                        界面菜单中的图表原先存在重复<br>
                                        修改界面图表样式使得界面更加美观<br>
                                    </div>
                                </div>
                                <div class="layuimini-notice">
                                    <div class="layuimini-notice-title">首页界面美化</div>
                                    <div class="layuimini-notice-extra">2023-03-07 11:55</div>
                                    <div class="layuimini-notice-content layui-hide">
                                        原先的首页界面太单调不美观<br>
                                        修改首页界面样式，美观简介<br>
                                        实时体现系统数据<br>
                                    </div>
                                </div>
                                <div class="layuimini-notice">
                                    <div class="layuimini-notice-title">增加刷新，清除缓存，全屏等功能</div>
                                    <div class="layuimini-notice-extra">2023-03-05 14:53</div>
                                    <div class="layuimini-notice-content layui-hide">
                                        界面头部增加刷新，清楚缓存，全屏等功能<br>
                                        全屏操作系统效果更加优秀<br>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="layui-card">
                            <div class="layui-card-header"><i class="fa fa-fire icon"></i>版本信息</div>
                            <div class="layui-card-body layui-text">
                                <table class="layui-table">
                                    <colgroup>
                                        <col width="100">
                                        <col>
                                    </colgroup>
                                    <tbody>
                                        <tr>
                                            <td>系统名称</td>
                                            <td>
                                                进销存管理系统
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>技术选型</td>
                                            <td>
                                                后端技术：Spring,Spring Mvc,Spring Boot,Spring Security,MyBatisPlus<br>
                                                前端技术：Layui,Echars,JavaScript,Freemark
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>当前版本</td>
                                            <td>v1.0.0</td>
                                        </tr>
                                        <tr>
                                            <td>主要特色</td>
                                            <td>零门槛 / 响应式 / 清爽 / 极简</td>
                                        </tr>
                                        <tr>
                                            <td>开发相关</td>
                                            <td>
                                                Layui文档：<a href="https://layui.gitee.io/v2/docs/"
                                                           target="_blank">点击查看</a><br>
                                                Echars文档：<a href="https://echarts.apache.org/handbook/zh/get-started/"
                                                            target="_blank">点击查看</a><br>
                                                MyBtisPlus文档：<a href="https://baomidou.com/pages/d357af/"
                                                                target="_blank">点击查看</a><br>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>

                        <div class="layui-card">
                            <div class="layui-card-header"><i class="fa fa-paper-plane-o icon"></i>作者心语</div>
                            <div class="layui-card-body layui-text layadmin-text">
                                <p>界面基于layui2.5.5以及font-awesome-4.7.0进行实现。layui开发文档地址：<a
                                            class="layui-btn layui-btn-xs layui-btn-danger" target="_blank"
                                            href="https://layui.gitee.io/v2/docs/">layui文档</a></p>
                                <p>系统作者：青海大学2019级学生-zzh</p>
                                <p>欢迎各位用户使用进销存管理系统</p>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>

        <script>
            layui.use(['layer', 'miniTab', 'echarts'], function () {
                var $ = layui.jquery,
                    layer = layui.layer,
                    miniTab = layui.miniTab,
                    echarts = layui.echarts;

                miniTab.listen();

                //定时器更新界面的值
                Count();

                function Count() {
                    $.ajax({
                        url: ctx + '/count/count',
                        type: 'post',
                        dataType: 'json',
                        data: {},
                        success: function (r) {
                            var userCount = r.obj.userCount;
                            var roleCount = r.obj.roleCount;
                            var menuCount = r.obj.menuCount;
                            var supplierCount = r.obj.supplierCount;
                            var customerCount = r.obj.customerCount;
                            var goodsCount = r.obj.goodsCount;
                            var purchaseCount = r.obj.purchaseCount;
                            var saleCount = r.obj.saleCount;
                            var count = document.getElementsByClassName("no-margins");
                            count[0].innerHTML = userCount;
                            count[1].innerHTML = roleCount;
                            count[2].innerHTML = menuCount;
                            count[3].innerHTML = supplierCount;
                            count[4].innerHTML = customerCount;
                            count[5].innerHTML = goodsCount;
                            count[6].innerHTML = purchaseCount;
                            count[7].innerHTML = saleCount;

                        },
                        error: function () {
                        }
                    })
                    setTimeout(function () {
                        Count()
                    }, 20 * 1000);
                }


                /**
                 * 查看公告信息
                 **/
                $('body').on('click', '.layuimini-notice', function () {
                    var title = $(this).children('.layuimini-notice-title').text(),
                        noticeTime = $(this).children('.layuimini-notice-extra').text(),
                        content = $(this).children('.layuimini-notice-content').html();
                    var html = '<div style="padding:15px 20px; text-align:justify; line-height: 22px;border-bottom:1px solid #e2e2e2;background-color: #2f4056;color: #ffffff">\n' +
                        '<div style="text-align: center;margin-bottom: 20px;font-weight: bold;border-bottom:1px solid #718fb5;padding-bottom: 5px"><h4 class="text-danger">' + title + '</h4></div>\n' +
                        '<div style="font-size: 12px">' + content + '</div>\n' +
                        '</div>\n';
                    parent.layer.open({
                        type: 1,
                        title: '系统公告' + '<span style="float: right;right: 1px;font-size: 12px;color: #b1b3b9;margin-top: 1px">' + noticeTime + '</span>',
                        area: '300px;',
                        shade: 0.8,
                        id: 'layuimini-notice',
                        btn: ['确定'],
                        btnAlign: 'c',
                        moveType: 1,
                        content: html,
                        success: function (layero) {
                            var btn = layero.find('.layui-layer-btn');
                            btn = btn.find('.layui-layer-btn0');
                            btn.on("click", function () {
                                layer.msg("感谢查看", {icon: 6});
                            });
                        }
                    });
                });

                /**
                 * 报表功能
                 */
                var countSaleByDayList;
                countSaleByDay();

                function countSaleByDay() {
                    $.ajax({
                        url: ctx + '/count/countSaleByDay',
                        type: 'post',
                        dataType: 'json',
                        async: false,
                        data: {},
                        success: function (r) {
                            countSaleByDayList = r;
                        }
                    })
                }

                var countPachaseList;
                countPachase();

                function countPachase() {
                    $.ajax({
                        url: ctx + '/count/countPachase',
                        type: 'post',
                        dataType: 'json',
                        async: false,
                        data: {},
                        success: function (r) {
                            countPachaseList = r;
                        }
                    })
                }

                var countSaleList;
                countSale();

                function countSale() {
                    $.ajax({
                        url: ctx + '/count/countPachase',
                        type: 'post',
                        dataType: 'json',
                        async: false,
                        data: {},
                        success: function (r) {
                            countSaleList = r;
                        }
                    })
                }

                var countReturnList;
                countReturn();

                function countReturn() {
                    $.ajax({
                        url: ctx + '/count/countReturn',
                        type: 'post',
                        dataType: 'json',
                        async: false,
                        data: {},
                        success: function (r) {
                            countReturnList = r;
                        }
                    })
                }

                var countCustomerReturnlist;
                countCustomerReturn();

                function countCustomerReturn() {
                    $.ajax({
                        url: ctx + '/count/countCustomerReturn',
                        type: 'post',
                        dataType: 'json',
                        async: false,
                        data: {},
                        success: function (r) {
                            countCustomerReturnlist = r;
                        }
                    })
                }

                var echartsRecords = echarts.init(document.getElementById('echarts-records'), 'walden');
                var optionRecords = {
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        data: ['进货单数', '销售单数', '退货单数', '客户退货单数', '日盈利金额']
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    toolbox: {
                        feature: {
                            saveAsImage: {}
                        }
                    },
                    xAxis: {
                        type: 'category',
                        boundaryGap: false,
                        data: ['前7天', '前6天', '前5天', '前4天', '前3天', '前2天', '前1天']
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [
                        {
                            name: '进货单数',
                            type: 'line',
                            data: countPachaseList
                        },
                        {
                            name: '销售单数',
                            type: 'line',
                            data: countSaleList
                        },
                        {
                            name: '退货单数',
                            type: 'line',
                            data: countReturnList
                        },
                        {
                            name: '客户退货单数',
                            type: 'line',
                            data: countCustomerReturnlist
                        },
                        {
                            name: '日盈利金额',
                            type: 'line',
                            data: countSaleByDayList
                        }
                    ]
                };
                echartsRecords.setOption(optionRecords);
                echartsRecords.set

                // echarts 窗口缩放自适应
                window.onresize = function () {
                    echartsRecords.resize();
                }

            });
        </script>
    </body>
</html>
