<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>登录失败</title>
	<meta http-equiv="Refresh" content="5;url=./login.jsp ">
	
  </head>
  
  <body>
  	<c:choose>
  		<c:when test="${status=='1'}">
  			用户名或密码错误，请重试<br/>
  		</c:when>
  		<c:otherwise>
  			验证码错误<br/>
  		</c:otherwise>
  	</c:choose>
  	页面将在5秒后返回...<br/>
	<a href="./login.jsp">五秒后若未响应，请点击</a>
	
  </body>
</html>
