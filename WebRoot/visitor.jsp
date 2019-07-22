<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>游客界面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		a{
    		text-decoration:none;
    		color:black;
    	}
    	tr:nth-child(n+2):nth-child(odd):nth-child(-n+9){
			background-color: #EEF7FF;
        }
        tr:first-child{
			background-color: #B3C7EF;
		}
		#nav {
        	 text-align: center;
        }
        #page {
            list-style-type: none;
            font-size: 15px;
            display: inline-block;
            padding: 0;
        }
        #page>li {
            float: left;
            padding: 1px;
        }
        #page a {
            text-decoration: none;
            border: 1px solid #cccccc;
            display: block;
            width: 35px;
            padding: 6px;
        }
        #page a:hover {
        	background-color: #eeeeee;
        }
    </style>
  </head>
  
  <body>
  		<c:set var="data" value="${requestScope.page.data}"/>
    	<c:set var="pageNo" value="${requestScope.page.pageNo}"/>
    	<c:set var="totalPage" value="${requestScope.page.totalPage}"/>
    <table width="100%" bgcolor="#cccccc" cellpadding="1px" border="0px">
		<tr bgcolor="#ffffff">
			<td width="20%">ID</td>
			<td width="20%">书名</td>
			<td width="20%">作者</td>
			<td width="20%">出版社</td>
			<td width="20%">状态</td>
		</tr>
		<c:forEach var="data" items="${data}" begin="0" step="1">
			<tr bgcolor="#ffffff">
				<td>${data[0]}</td>
				<td><a href="abc.detail?bno=${data[0]}" id="bno">${data[1]}</a></td>
				<td>${data[2]}</td>
				<td>${data[3]}</td>
				<td>${data[4]}</td>
			</tr>
		</c:forEach>
		
	</table>
	<div id="nav">
	   	<ul id="page">
	   		<li>
	   			<a href="<c:url value="abc.page?pageNo=1"/>">首页</a>
	   		</li>
	   		<li>
	   			<a href="abc.page?pageNo=${pageNo-1>1?pageNo-1:1}">&laquo;</a>
	       	</li>
			
	       	<c:forEach begin="1" end="${totalPage}" varStatus="loop">
	           	<li>
	               	<a href="abc.page?pageNo=${loop.index}">${loop.index}</a>
	           	</li>
	       	</c:forEach>
	       	
	       	<li>
	           	<a href="abc.page?pageNo=${pageNo+1<totalPage?pageNo+1:totalPage}">&raquo;</a>
	       	</li>
	       	<li>
	           	<a href="abc.page?pageNo=${totalPage}">尾页</a>
	       	</li>
	   	</ul>
	</div>
  </body>
</html>
