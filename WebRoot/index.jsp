<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="UTF-8">
    <title>首页</title>
    <link rel="stylesheet" href="./jquery/jquery-ui.css">
    <script type="text/javascript" src="./jquery/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="./jquery/jquery-ui.js"></script>
    <script type="text/javascript">
        $(function(){
            $("a").button();
        });
    </script>
    <style type="text/css">
        body{
            margin: 0 auto;
        }
        #banner{
            border:1px solid white;
            text-align: center;
            width:auto;
            height: 100px;
            background-color: rgb(245, 249, 253);
            font-size: 40px;
            font-family:'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
        }
        #center{
            height:690px;
            width:auto;
            text-align: center;
            background-image: url("./image/1.jpg");
            background-size: 100% 100%;
        }
        #footer{
            border: 1px solid #2a2a2a;
            height:65px;
            text-align: center;
            background-color: #2a2a2a;
        }
        #btn1,#btn2{
            width:150px;
            height:50px;
            margin-top: 350px;
            margin-left:50px;
            padding-bottom: 10px;
            text-align: center;
        }
    </style>
</head>
<body>
    <div id="banner">
        <p>图书管理系统</p>
    </div>
    <div id="center">
        <div id="content">
            <a href="login.jsp" id="btn1"><h3>登录</h3></a>
            <a href="register.jsp" id="btn2"><h3>注册</h3></a>
        </div>
    </div>
    <div id="footer">
        <h4>CopyRight @ xzy 计算机九班</h4>
    </div>
</body>
</html>
