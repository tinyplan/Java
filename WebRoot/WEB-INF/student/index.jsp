<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>学生首页</title>
    <link rel="stylesheet" href="./jquery/jquery-ui.css">
    <script type="text/javascript" src="./jquery/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="./jquery/jquery-ui.js"></script>
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
    	#bname{
    		color:#145a9e;
    	}
    	#welcome{
    		float:right;
    		font-size:20px;
    	}
    	#list{
    		font-size:20px;
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
    <script type="text/javascript">
    	$(function(){
    		$("a,input[type=submit],button").button();
    	});
    </script>
  </head>
  
  <body>
  	<c:set var="data" value="${requestScope.page.data}"/>
    <c:set var="pageNo" value="${requestScope.page.pageNo}"/>
    <c:set var="totalPage" value="${requestScope.page.totalPage}"/>
  <b id="list">图书列表</b>
  <b id="welcome">欢迎你,<c:out value="${user.account}"></c:out></b>
  <hr>
  	<a href="searchlist.student">查看借书列表</a>
  	<a href="searchborrow.student?account=${user.account}">查看已借书列表</a>
  	<a href="searchreturn.student?account=${user.account}">查看还书列表</a>
    <form action="search.student" method="post">
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
				<td>
					<a href="abc.detail?bno=${data[0]}" id="bno">${data[1]}</a>
				</td>
				<td>${data[2]}</td>
				<td>${data[3]}</td>
				<td>
					<c:choose>
						<c:when test="${data[4]==1}">在架上</c:when>
						<c:otherwise>借出</c:otherwise>
					</c:choose>
				</td>
				<td>
					<c:choose>
						<c:when test="${data[4]==1}">
							<a href="addtolist.student?id=${data[0]}&account=${user.account}">添加至借书列表</a>
						</c:when>
						<c:otherwise>
							<button Enabled="false" style="opacity:0.2">添加至借书列表</button>
						</c:otherwise>
					</c:choose>
				</td>
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
