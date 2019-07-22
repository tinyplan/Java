<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page contentType="text/html; charset=UTF-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>查询</title>
	<style type="text/css">
    	a{
    		text-decoration:none;
    		color:black;
    	}
    </style>
  </head>
  
  <body>
  <button><a href="add.do">添加图书</a></button> | <button><a href="login.jsp">退出登录</a></button>
  	<form action="abc.msearch" method="post">
  		<input type="text" name="bname"/>
  		<input type="submit" value="精确查找">
  	</form>
 	<table width="100%" bgcolor="#cccccc" cellpadding="1px" border="0px">
		<tr bgcolor="#ffffff">
			<td width="10%">ID</td>
			<td width="20%">书名</td>
			<td width="20%">作者</td>
			<td width="20%">出版社</td>
			<td width="20%">状态</td>
			<td width="10%">操作</td>
		</tr>
		<c:forEach var="data" items="${data}" begin="0" step="1">
			<tr bgcolor="#ffffff">
				<td>${data[0]}</td>
				<td><a href="abc.detail?bno=${data[0]}" id="bno">${data[1]}</a></td>
				<td>${data[2]}</td>
				<td>${data[3]}</td>
				<td>${data[4]}</td>
				<td><button><a href="update.do?id=${data[0]}">修改</a></button> | 
							<button><a href="delete.do?id=${data[0]}">删除</a></button></td>
			</tr>
		</c:forEach>
	</table>
	
  </body>
</html>
