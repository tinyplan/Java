<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>删除图书</title>
    <style type="text/css">
  		#title{
  			font-size:20px;
  		}
  	</style>
  </head>
  
  <body>
  	<b id="title">添加图书</b>
  	<hr>
    <form action="delete.do" method="post">
    	请输入删除的图书名字：<input type="text" name="bname"/>
    	<input type="submit" value="删除">
    </form>
  </body>
</html>
