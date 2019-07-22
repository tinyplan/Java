<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户登录</title>
  </head>
  <link rel="stylesheet" href="./jquery/jquery-ui.css">
    <script type="text/javascript" src="./jquery/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="./jquery/jquery-ui.js"></script>
  <script type="text/javascript">
  	$(function(){
  		$("#login").button();
  		$("#checkImg").click(function(){
  			var timestamp = new Date().getTime();
			$(this).attr('src',$(this).attr('src') + '?' +timestamp );
  		});
  	});
  		
  	function check(){
  		var account = $("#account").val();
  		var pwd = $("#pwd").val();
			if(account==''){
	 			alert("用户名不能为空");
	 			$("#account").focus();
	 			return false;
		 	}else if(pwd==''){
		 		alert("密码不能为空");
		 		$("#pwd").focus();
		 		return false;
		 	}else{
				$.ajax({
					url:'abc.login',
					type:'post',
					data: {account:$("#account").val(), pwd:$("#pwd").val(),identity:$("#identity")}
				});
		 	}
  		return false;
  	}
  </script>
  <style type="text/css">
  	#content{
		/* border:1px solid black; */
		height:560px;
  		padding-top:200px;
  		padding-left:500px;
  		background-image: url("./image/1.jpg");
  		background-size: 100% 100%;
  	}
  	#title{
  		padding-left:120px;
  	}
  	body{
  		margin:0 auto;
  	}
  	a{
  		text-decoration:none;
  	}
  	table{
  		line-height:40px;
  	}
  	#login{
  		width:100px;
  		height:40px;
  	}
  	#account,#pwd,#checkCode{
  		height:30px;
  	}
  	#checkImg{
  		width:60px;
  		height:30px;
  		vertical-align:middle;
  	}
  </style>
  <body>
  	<div id="content">
	  	<div id="title">
	  		<h2>我的图书管理系统</h2>
	  	</div>
		<form action="user.login" method="post" onSubmit="return check()">
			<table width=500px height=200px>
				<tr>
					<td align="right" width=27%><b>用户名:</b></td>
					<td><input type="text" name="account" id="account" size=30></td>
				</tr>
				<tr>
					<td align="right" width=20%><b>密码:</b></td>
					<td><input type="password" name="pwd" id="pwd" size=30></td>
				</tr>
				<tr>
					<td align="right" width=20%><b>用户身份:</b></td>
					<td><input type="radio" name="identity" value="1" checked="checked" id="identity">学生
						<input type="radio" name="identity" value="2" id="identity">管理员</td>
				</tr>
				<tr>
					<td align="right" width=20%><b>验证码:</b></td>
					<td><input type="text" name="checkCode" size=30 id="checkCode"/>
						<img src="./servlet/checkServlet" id="checkImg" title="看不清，请点击图片刷新"></td>
				</tr>
				<tr>
					<td colspan=2 align="center"><input type="submit" value="登录" id="login"></td>
				</tr>
				<tr>
					<td colspan=2 align="center"><a href="abc.visit">游客登录</a></td>
				</tr>
			</table>
		</form>
	</div>
  </body>
</html>
