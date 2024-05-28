<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>进销存管理系统</title>
        <#--通过在meta中设置X-UA-Compatible的值，可以指定网页的兼容性模式设置。-->
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <#--如果请求的url是静态的html页面，则需要在页面中添加meta标签代码：-->
        <meta http-equiv="Access-Control-Allow-Origin" content="*">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="format-detection" content="telephone=no">
        <#include "common.ftl">
        <style>
            .login-main .login-bottom .center .item input {
                display: inline-block;
                width: 227px;
                height: 22px;
                padding: 0;
                position: absolute;
                border: 0;
                outline: 0;
                font-size: 14px;
                letter-spacing: 0;
            }

            body {
                background: url(${ctx.contextPath}/images/background.svg) 0% 0% / cover no-repeat;
                position: static;
                font-size: 12px;
            }

            input::-webkit-input-placeholder {
                color: #a6aebf;
            }

            input::-moz-placeholder { /* Mozilla Firefox 19+ */
                color: #a6aebf;
            }

            input:-moz-placeholder { /* Mozilla Firefox 4 to 18 */
                color: #a6aebf;
            }

            input:-ms-input-placeholder { /* Internet Explorer 10-11 */
                color: #a6aebf;
            }

            input:-webkit-autofill { /* 取消Chrome记住密码的背景颜色 */
                -webkit-box-shadow: 0 0 0 1000px white inset !important;
            }

            html {
                height: 80%;
            }

            .login-main .login-bottom .center .item .validateImg {
                position: absolute;
                right: 1px;
                cursor: pointer;
                height: 47px;
                border: 1px solid #e6e6e6;
            }

            .footer a, .footer span {
                color: #fff;
            }

            @media screen and (max-width: 428px) {

            }

            .layui-form {
                width: 320px !important;
                margin: auto !important;
                margin-top: 160px !important;
            }

            .layui-form button {
                width: 100% !important;
                height: 60px !important;
                line-height: 60px !important;
                font-size: 16px !important;
                background-color: #5FB878 !important;
                font-weight: 550 !important;
            }

            .layui-form-checked[lay-skin=primary] i {
                border-color: #5FB878 !important;
                background-color: #5FB878 !important;
                color: #fff !important;
            }

            .layui-form-item {
                margin-top: 20px !important;
            }

            .layui-input {
                height: 60px !important;
                line-height: 60px !important;
                padding-left: 15px !important;
                border-radius: 3px !important;
                font-size: 20px !important;
            }

            .layui-input:focus {
                box-shadow: 0px 0px 2px 1px #5FB878 !important;
            }

            .logo {
                width: 80px !important;
                margin-top: 10px !important;
                margin-bottom: 10px !important;
                margin-left: 20px !important;
            }

            .title {
                font-size: 40px !important;
                font-weight: 550 !important;
                margin-left: 20px !important;
                color: #5FB878 !important;
                display: inline-block !important;
                height: 60px !important;
                line-height: 80px !important;
                margin-top: 10px !important;
                position: absolute !important;
            }

            .desc {
                width: 100% !important;
                text-align: center !important;
                color: gray !important;
                height: 60px !important;
                line-height: 60px !important;
                font-size: 20px !important;
            }

            body {
                background-repeat: no-repeat;
                background-color: whitesmoke;
                background-size: 100%;
                height: 100%;
            }

            .code {
                float: left;
                margin-right: 13px;
                margin: 0px !important;
                border: #e6e6e6 1px solid;
                display: inline-block !important;
            }

            .codeImage {
                float: right;
                height: 50px;
                border: #e6e6e6 1px solid;
                cursor: pointer;
            }

            @media (max-width: 768px) {
                body {
                    background-position: center;
                }
            }


        </style>
    </head>

    <body>
        <form class="layui-form" action="javascript:void(0);">
            <div class="layui-form-item">
                <img class="logo" src="${ctx.contextPath}/images/logo.png"/>
                <div class="title">PSI Admin</div>
                <div class="desc">
                    基 于 Web 的 进 销 存 管 理 系 统
                </div>
            </div>
            <div class="layui-form-item">
                <input placeholder="账 户 : admin " lay-verify="required" name="username" maxlength="24" hover
                       class="layui-input"/>
            </div>
            <div class="layui-form-item">
                <input placeholder="密 码 : admin " lay-verify="required" name="password" maxlength="20" hover
                       class="layui-input" type="password"/>
            </div>
            <div class="layui-form-item">
                <input placeholder="验证码 : " hover lay-verify="required" name="captcha" maxlength="4"
                       class="code layui-input layui-input-inline"/>
                <img src="${ctx.contextPath}/image" class="codeImage" id="refreshCaptcha"/>
            </div>
            <div class="layui-form-item">
                <input type="checkbox" name="" title="七天免登录" name="rememberMe" id="rememberMe" value="true"
                       lay-skin="primary" checked>
            </div>
            <div class="layui-form-item">
                <button type="button" class="pear-btn pear-btn-success login" lay-submit lay-filter="login">
                    立 即 登 入
                </button>
            </div>
        </form>
        <script src="${ctx.contextPath}/js/index.js" charset="utf-8"></script>
    </body>
</html>