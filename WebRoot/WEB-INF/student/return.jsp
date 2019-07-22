<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<link rel="stylesheet" href="./jquery/jquery-ui.css">
    <script type="text/javascript" src="./jquery/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="./jquery/jquery-ui.js"></script>
    <title>归还图书</title>
    <script type="text/javascript">
    	$(function(){
    		$("a,input[type=submit],button").button();
    	});
    </script>
  </head>
  
  <body>
    <table width="100%" bgcolor="#cccccc" cellpadding="1px" border="0px">
		<tr bgcolor="#ffffff">
			<td>ID</td>
			<td>书名</td>
			<td>作者</td>
			<td>出版社</td>
			<td>借书时间</td>
			<td>操作</td>
		</tr>
		<c:forEach var="data" items="${data}" begin="0" step="1">
			<tr bgcolor="#ffffff">
				<td>${data[0]}</td>
				<td>${data[1]}</td>
				<td>${data[2]}</td>
				<td>${data[3]}</td>
				<td>${data[4]}</td>
				<td>
					<a href="borrow.student?list=${data[0]}&account=${account}&type=part">重新借阅</a>
				</td>
			</tr>
		</c:forEach>
	</table>
  </body>
</html>
