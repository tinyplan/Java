<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>借书列表</title>
    <link rel="stylesheet" href="./jquery/jquery-ui.css">
    <script type="text/javascript" src="./jquery/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="./jquery/jquery-ui.js"></script>
	<style type="text/css">
    	a{
    		text-decoration:none;
    		color:black;
    	}
    </style>
    <script type="text/javascript">
    	$(function(){
    		$("a,input[type=submit],button").button();
    	});
    </script>
  </head>
  
  <body>
  		<a href="borrow.student?list=all&account=${account}&type=all">借书</a>
    	<table width="100%" bgcolor="#cccccc" cellpadding="1px" border="0px">
		<tr bgcolor="#ffffff">
			<td>ID</td>
			<td>书名</td>
			<td>作者</td>
			<td>出版社</td>
			<td>操作</td>
		</tr>
		<c:forEach var="data" items="${sessionScope.list[account]}" begin="0" step="1">
			<tr bgcolor="#ffffff">
				<td>${data[0]}</td>
				<td>${data[1]}</td>
				<td>${data[2]}</td>
				<td>${data[3]}</td>
				<td><a href="borrow.student?list=${data[0]}&account=${account}&type=part">确认借阅</a>&nbsp;
					<a href="remove.student?list=${data[0]}&account=${account}">移出</a></td>
			</tr>
		</c:forEach>
	</table>
  </body>
</html>
