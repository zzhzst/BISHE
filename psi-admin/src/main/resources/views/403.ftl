<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title></title>
        <#include "common.ftl">
    </head>

    <style>
        * {
            padding: 0;
            margin: 0;
            font-size: 0.38rem;
        }

        ul {
            list-style: none;
        }

        a {
            text-decoration: none;
            -webkit-tap-highlight-color: transparent
        }

        .clearfix:after {
            content: '';
            width: 0;
            height: 0;
            display: block;
            clear: both;
        }

        html {
            height: 100%;
            width: 100%;
        }

        body {
            font-size: 0.28rem;
            height: 100%;
            width: 100%;
            display: flex;
            flex-direction: column;
            position: relative;
            background-color: white !important;
        }

        .content {
            position: absolute;
            top: 50%;
            transform: translateY(-50%);
            width: 100%;
            text-align: center;
        }

        .content > img {
            height: 300px;
            max-width: 370px;
            margin-right: 180px;
        }

        .content > * {
            display: inline-block;
        }

        .content-r {
            vertical-align: top;
        }

        .content-r > h1 {
            font-size: 72px;
            color: #434e59;
            margin-bottom: 24px;
            font-weight: 600;
        }

        .content-r > p {
            font-size: 20px;
            color: rgba(0, 0, 0, .45);
            margin-bottom: 16px;
        }

        button {
            margin-top: 20px;
        }

    </style>

    <body>
        <div class="content">
            <img src="${ctx.contextPath}/images/403.svg" alt="">
            <div class="content-r">
                <h1>403</h1>
                <p>抱歉，你无权访问该页面</p>
            </div>
        </div>
    </body>
</html>