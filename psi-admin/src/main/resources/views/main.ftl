<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>进销存管理系统</title>
        <#include "common.ftl">
        <#--配色方案改变此样式-->
        <style id="layuimini-bg-color"></style>
    </head>
    <body class="layui-layout-body layuimini-all">
        <div class="layui-layout layui-layout-admin">
            <#--顶部配置-->
            <div class="layui-header header">
                <div class="layui-logo" align="left">
                    <a href="">
                        <img src="images/logo.png" alt="logo">
                        <h1 style="margin: 0 0 0 3px">进销存管理系统</h1>
                    </a>
                </div>
                <#--左侧功能-->
                <a>
                    <div class="layuimini-tool"><i title="展开" class="fa fa-outdent" data-side-fold="1"></i></div>
                </a>
                <#--右侧功能-->
                <ul class="layui-nav layui-layout-right">
                    <li class="layui-nav-item" lay-unselect>
                        <div id="time"></div>
                    </li>
                    <script>
                        function showTime() {
                            var date = new Date();

                            // 年月日
                            var year = date.getFullYear();
                            var month = date.getMonth() + 1;
                            var day = date.getDate();

                            // 时分秒
                            var hour = date.getHours();
                            var minute = date.getMinutes();
                            var second = date.getSeconds();

                            // 实时显示
                            var element = document.getElementById('time');
                            element.innerHTML = '<h1>当前时间：' + year + '-' + month + '-' + day + ' ' + hour + ':' + minute + ':' + second;
                        }

                        setInterval('showTime()', 1000);
                    </script>
                    <li class="layui-nav-item" lay-unselect>
                        <a href="javascript:;" data-refresh="刷新"><i class="fa fa-refresh"></i></a>
                    </li>
                    <li class="layui-nav-item" lay-unselect>
                        <a href="javascript:;" data-clear="清理" class="layuimini-clear"><i class="fa fa-trash-o"></i></a>
                    </li>
                    <li class="layui-nav-item mobile layui-hide-xs" lay-unselect>
                        <a href="javascript:;" data-check-screen="full"><i class="fa fa-arrows-alt"></i></a>
                    </li>
                    <li class="layui-nav-item layuimini-setting">
                        <a href="javascript:;"><img
                                    src="images/${(Session.SPRING_SECURITY_CONTEXT.authentication.principal.tx)!}"
                                    class="layui-nav-img">
                            ${(Session.SPRING_SECURITY_CONTEXT.authentication.principal.username)!'未登录'}
                        </a>
                        <dl class="layui-nav-child">
                            <dd>
                                <a href="javascript:;" data-iframe-tab="${ctx.contextPath}/user/setting"
                                   data-title="基本资料"
                                   data-icon="fa fa-gears">基本资料</a>
                            </dd>
                            <dd>
                                <a href="javascript:;" data-iframe-tab="${ctx.contextPath}/user/password"
                                   data-title="修改密码"
                                   data-icon="fa fa-gears">修改密码</a>
                            </dd>
                            <dd>
                                <hr>
                            </dd>
                            <dd>
                                <a href="${ctx.contextPath}/signout" class="login-out">退出登录</a>
                            </dd>
                        </dl>
                    </li>
                    <li class="layui-nav-item layuimini-select-bgcolor mobile layui-hide-xs" lay-unselect>
                        <a href="javascript:;" data-bgcolor="配色方案"><i class="fa fa-ellipsis-v"></i>></a>
                    </li>
                </ul>
            </div>
            <#--菜单配置-->
            <div class="layui-side layui-bg-black">
                <div class="layui-side-scroll layui-left-menu">
                    <ul class="layui-nav layui-nav-tree layui-left-nav-tree layui-this" id="currency">
                        <@security.authorize access="hasAnyAuthority('30')">
                            <li class="layui-nav-item">
                                <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-truck"></i><span
                                            class="layui-left-nav"> 进货管理</span> <span class="layui-nav-more"></span></a>
                                <dl class="layui-nav-child">
                                    <@security.authorize access="hasAnyAuthority('3010')">
                                        <dd>
                                            <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd"
                                               data-tab-mpi="m-p-i-11"
                                               data-tab="purchase/index" target="_self"><i class="fa fa-truck"></i><span
                                                        class="layui-left-nav"> 进货入库</span></a>
                                        </dd>
                                    </@security.authorize>
                                    <@security.authorize access="hasAnyAuthority('3020')">
                                        <dd class="">
                                            <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd"
                                               data-tab-mpi="m-p-i-12"
                                               data-tab="return/index" target="_self"><i
                                                        class="fa fa-upload"></i><span
                                                        class="layui-left-nav"> 退货出库</span></a>
                                        </dd>
                                    </@security.authorize>
                                    <@security.authorize access="hasAnyAuthority('3030')">
                                        <dd class="">
                                            <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd"
                                               data-tab-mpi="m-p-i-13"
                                               data-tab="purchase/searchPage" target="_self"><i
                                                        class="fa fa-search-plus"></i><span
                                                        class="layui-left-nav"> 进货单据查询</span></a>
                                        </dd>
                                    </@security.authorize>
                                    <@security.authorize access="hasAnyAuthority('3040')">
                                        <dd class="">
                                            <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd"
                                               data-tab-mpi="m-p-i-14"
                                               data-tab="return/searchPage" target="_self"><i
                                                        class="fa fa-search-minus"></i><span
                                                        class="layui-left-nav"> 退货单据查询</span></a>
                                        </dd>
                                    </@security.authorize>
                                </dl>
                            </li>
                        </@security.authorize>
                        <@security.authorize access="hasAnyAuthority('40')">
                            <li class="layui-nav-item">
                                <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-server"></i><span
                                            class="layui-left-nav"> 销售管理</span> <span class="layui-nav-more"></span></a>
                                <dl class="layui-nav-child">
                                    <@security.authorize access="hasAnyAuthority('4010')">
                                        <dd>
                                            <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd"
                                               data-tab-mpi="m-p-i-11"
                                               data-tab="sale/index" target="_self"><i class="fa fa-paw"></i><span
                                                        class="layui-left-nav"> 销售出库</span></a>
                                        </dd>
                                    </@security.authorize>
                                    <@security.authorize access="hasAnyAuthority('4020')">
                                        <dd class="">
                                            <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd"
                                               data-tab-mpi="m-p-i-12"
                                               data-tab="customerReturn/index" target="_self"><i
                                                        class="fa fa-pencil"></i><span
                                                        class="layui-left-nav"> 客户退货</span></a>
                                        </dd>
                                    </@security.authorize>
                                    <@security.authorize access="hasAnyAuthority('4030')">
                                        <dd class="">
                                            <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd"
                                               data-tab-mpi="m-p-i-13"
                                               data-tab="sale/searchPage" target="_self"><i
                                                        class="fa fa-pencil-square"></i><span
                                                        class="layui-left-nav"> 销售单据查询</span></a>
                                        </dd>
                                    </@security.authorize>
                                    <@security.authorize access="hasAnyAuthority('4040')">
                                        <dd class="">
                                            <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd"
                                               data-tab-mpi="m-p-i-14"
                                               data-tab="customerReturn/searchPage" target="_self"><i
                                                        class="fa fa-reply"></i><span
                                                        class="layui-left-nav"> 客户退货查询</span></a>
                                        </dd>
                                    </@security.authorize>
                                </dl>
                            </li>
                        </@security.authorize>
                        <@security.authorize access="hasAnyAuthority('50')">
                            <li class="layui-nav-item">
                                <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-binoculars"></i><span
                                            class="layui-left-nav"> 库存管理</span> <span class="layui-nav-more"></span></a>
                                <dl class="layui-nav-child">
                                    <@security.authorize access="hasAnyAuthority('5010')">
                                        <dd>
                                            <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd"
                                               data-tab-mpi="m-p-i-11"
                                               data-tab="damage/index" target="_self"><i class="fa fa-adjust"></i><span
                                                        class="layui-left-nav"> 商品报损</span></a>
                                        </dd>
                                    </@security.authorize>
                                    <@security.authorize access="hasAnyAuthority('5020')">
                                        <dd class="">
                                            <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd"
                                               data-tab-mpi="m-p-i-12"
                                               data-tab="overflow/index" target="_self"><i
                                                        class="fa fa-anchor"></i><span
                                                        class="layui-left-nav"> 商品报溢</span></a>
                                        </dd>
                                    </@security.authorize>
                                    <@security.authorize access="hasAnyAuthority('5030')">
                                        <dd class="">
                                            <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd"
                                               data-tab-mpi="m-p-i-13"
                                               data-tab="common/alarmPage" target="_self"><i
                                                        class="fa fa-archive"></i><span
                                                        class="layui-left-nav"> 库存报警</span></a>
                                        </dd>
                                    </@security.authorize>
                                    <@security.authorize access="hasAnyAuthority('5040')">
                                        <dd class="">
                                            <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd"
                                               data-tab-mpi="m-p-i-14"
                                               data-tab="common/toDamageOverflowSearchPage" target="_self"><i
                                                        class="fa fa-arrows"></i><span
                                                        class="layui-left-nav"> 报损报溢查询</span></a>
                                        </dd>
                                    </@security.authorize>
                                    <@security.authorize access="hasAnyAuthority('5050')">
                                        <dd class="">
                                            <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd"
                                               data-tab-mpi="m-p-i-15"
                                               data-tab="common/toGoodsStockPage" target="_self"><i
                                                        class="fa fa-briefcase"></i><span
                                                        class="layui-left-nav"> 当前库存查询</span></a>
                                        </dd>
                                    </@security.authorize>
                                </dl>
                            </li>
                        </@security.authorize>
                        <@security.authorize access="hasAnyAuthority('60')">
                            <li class="layui-nav-item">
                                <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-pie-chart"></i><span
                                            class="layui-left-nav"> 统计报表</span> <span class="layui-nav-more"></span></a>
                                <dl class="layui-nav-child">
                                    <@security.authorize access="hasAnyAuthority('6010')">
                                        <dd>
                                            <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd"
                                               data-tab-mpi="m-p-i-11"
                                               data-tab="report/countSupplier" target="_self"><i
                                                        class="fa fa-user"></i><span
                                                        class="layui-left-nav"> 供应商统计</span></a>
                                        </dd>
                                    </@security.authorize>
                                    <@security.authorize access="hasAnyAuthority('6020')">
                                        <dd class="">
                                            <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd"
                                               data-tab-mpi="m-p-i-12"
                                               data-tab="report/countCustomer" target="_self"><i
                                                        class="fa fa-ship"></i><span
                                                        class="layui-left-nav"> 客户统计</span></a>
                                        </dd>
                                    </@security.authorize>
                                    <@security.authorize access="hasAnyAuthority('6030')">
                                        <dd class="">
                                            <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd"
                                               data-tab-mpi="m-p-i-13"
                                               data-tab="report/countPurchase" target="_self"><i
                                                        class="fa fa-shopping-cart"></i><span
                                                        class="layui-left-nav"> 商品采购统计</span></a>
                                        </dd>
                                    </@security.authorize>
                                    <@security.authorize access="hasAnyAuthority('6040')">
                                        <dd class="">
                                            <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd"
                                               data-tab-mpi="m-p-i-14"
                                               data-tab="report/countSale" target="_self"><i
                                                        class="fa fa-trophy"></i><span
                                                        class="layui-left-nav"> 商品销售统计</span></a>
                                        </dd>
                                    </@security.authorize>
                                    <@security.authorize access="hasAnyAuthority('6050')">
                                        <dd class="">
                                            <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd"
                                               data-tab-mpi="m-p-i-15"
                                               data-tab="report/countDaySale" target="_self"><i
                                                        class="fa fa-tint"></i><span
                                                        class="layui-left-nav"> 按日统计</span></a>

                                        </dd>
                                    </@security.authorize>
                                    <@security.authorize access="hasAnyAuthority('6060')">
                                        <dd class="">
                                            <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd"
                                               data-tab-mpi="m-p-i-16"
                                               data-tab="report/countMonthSale" target="_self"><i
                                                        class="fa fa-toggle-down"></i><span
                                                        class="layui-left-nav"> 按月统计</span></a>
                                        </dd>
                                    </@security.authorize>
                                </dl>
                            </li>
                        </@security.authorize>
                        <@security.authorize access="hasAnyAuthority('20')">
                            <li class="layui-nav-item">
                                <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-bars"></i><span
                                            class="layui-left-nav"> 基础资料</span> <span class="layui-nav-more"></span></a>
                                <dl class="layui-nav-child">
                                    <@security.authorize access="hasAnyAuthority('2010')">
                                        <dd>
                                            <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd"
                                               data-tab-mpi="m-p-i-11"
                                               data-tab="supplier/index" target="_self"><i class="fa fa-user"></i><span
                                                        class="layui-left-nav"> 供应商管理</span></a>
                                        </dd>
                                    </@security.authorize>
                                    <@security.authorize access="hasAnyAuthority('2020')">
                                        <dd class="">
                                            <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd"
                                               data-tab-mpi="m-p-i-12"
                                               data-tab="customer/index" target="_self"><i
                                                        class="fa fa-users"></i><span
                                                        class="layui-left-nav"> 客户管理</span></a>
                                        </dd>
                                    </@security.authorize>
                                    <@security.authorize access="hasAnyAuthority('2030')">
                                        <dd class="">
                                            <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd"
                                               data-tab-mpi="m-p-i-13"
                                               data-tab="goods/index" target="_self"><i
                                                        class="fa fa-diamond"></i><span
                                                        class="layui-left-nav"> 商品管理</span></a>
                                        </dd>
                                    </@security.authorize>
                                    <@security.authorize access="hasAnyAuthority('2040')">
                                        <dd class="">
                                            <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd"
                                               data-tab-mpi="m-p-i-14"
                                               data-tab="goodsType/index" target="_self"><i
                                                        class="fa fa-paper-plane"></i><span
                                                        class="layui-left-nav"> 分类管理</span></a>
                                        </dd>
                                    </@security.authorize>
                                    <@security.authorize access="hasAnyAuthority('2050')">
                                        <dd class="">
                                            <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd"
                                               data-tab-mpi="m-p-i-15"
                                               data-tab="goodsUnit/index" target="_self"><i
                                                        class="fa fa-key"></i><span
                                                        class="layui-left-nav"> 单位管理</span></a>
                                        </dd>
                                    </@security.authorize>
                                    <@security.authorize access="hasAnyAuthority('2060')">
                                        <dd class="">
                                            <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd"
                                               data-tab-mpi="m-p-i-16"
                                               data-tab="stock/index" target="_self"><i
                                                        class="fa fa-home"></i><span
                                                        class="layui-left-nav"> 期初库存</span></a>
                                        </dd>
                                    </@security.authorize>
                                </dl>
                            </li>
                        </@security.authorize>
                        <@security.authorize access="hasAnyAuthority('10')">
                            <li class="layui-nav-item">
                                <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-gears"></i><span
                                            class="layui-left-nav"> 系统设置</span> <span class="layui-nav-more"></span></a>
                                <dl class="layui-nav-child">
                                    <@security.authorize access="hasAnyAuthority('1010')">
                                        <dd>
                                            <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd"
                                               data-tab-mpi="m-p-i-11" data-tab="user/index" target="_self"><i
                                                        class="fa fa-user"></i><span class="layui-left-nav"> 用户管理</span></a>
                                        </dd>
                                    </@security.authorize>

                                    <@security.authorize access="hasAnyAuthority('1020')">
                                        <dd class="">
                                            <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd"
                                               data-tab-mpi="m-p-i-12" data-tab="role/index" target="_self"><i
                                                        class="fa fa-users"></i><span
                                                        class="layui-left-nav"> 角色管理</span></a>
                                        </dd>
                                    </@security.authorize>
                                    <@security.authorize access="hasAnyAuthority('1030')">
                                        <dd class="">
                                            <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd"
                                               data-tab-mpi="m-p-i-13" data-tab="menu/index" target="_self"><i
                                                        class="fa fa-window-maximize"></i><span
                                                        class="layui-left-nav"> 菜单管理</span></a>
                                        </dd>
                                    </@security.authorize>
                                </dl>
                            </li>
                        </@security.authorize>
                        <span class="layui-nav-bar" style="top: 201px; height: 0px; opacity: 0;"></span>
                    </ul>
                </div>
            </div>
            <#--主题配置-->
            <div class="layui-body">
                <div class="layui-tab" lay-filter="layuiminiTab" id="top_tabs_box">
                    <ul class="layui-tab-title" id="top_tabs">
                        <li class="layui-this" id="layuiminiHomeTabId" lay-id="welcome"><i class="fa fa-home"></i>
                            <span>首页</span></li>
                    </ul>
                    <ul class="layui-nav closeBox">
                        <li class="layui-nav-item">
                            <a href="javascript:;"> <i class="fa fa-dot-circle-o"></i> 页面操作</a>
                            <dl class="layui-nav-child">
                                <dd><a href="javascript:;" data-page-close="current"><i class="fa fa-close"></i>
                                        关闭当前</a>
                                </dd>
                                <dd><a href="javascript:;" data-page-close="other"><i class="fa fa-window-close"></i>
                                        关闭其他</a>
                                </dd>
                                <dd><a href="javascript:;" data-page-close="all"><i class="fa fa-window-close-o"></i>
                                        关闭全部</a>
                                </dd>
                            </dl>
                        </li>
                    </ul>
                    <div class="layui-tab-content clildFrame">
                        <div id="layuiminiHomeTabIframe" class="layui-tab-item layui-show">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript" src="${ctx.contextPath}/js/main.js"></script>
    </body>
</html>
