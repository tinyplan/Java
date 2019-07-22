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
    
    <title>图书详情</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="./jquery/jquery-ui.css">
    <script type="text/javascript" src="./jquery/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="./jquery/jquery-ui.js"></script>
	<style type="text/css">
		#content{
			width:1300px;
			height:500px;
			border:5px dashed #5D13E7;
			margin-left:100px;
			margin-top:50px;
		}
		#image{
			float:left;
			border:3px dashed #9ea9f0;
			width:500px;
			height:400px;
			margin-top:50px;
			margin-left:100px;
		}
		#info{
			float:right;
			width:500px;
			height:400px;
			margin-top:50px;
			margin-right:100px;
		}
		table{
            width: 500px;
        }
        tr{
            height: 50px;
        }
        td{            
            border-bottom:3px solid #6688ff;
        }
        td:first-child{
        	width: 150px;
        }
        b{
        	font-size:20px;
        }
	</style>
	<script type="text/javascript">
		$(function(){
			$("a").button();
		});
	</script>
  </head>
  
  <body>
    <b>图书详情</b>
    <hr>
    <div id="content">
    	<div id="image">
    		<img src="${data[5]}" />
    	</div>
    	<div id="info">
    		<table>
    			<tr>
    				<td>书本ID:</td>
    				<td>${data[0]}</td>
    			</tr>
    			<tr>
    				<td>书本名称:</td>
    				<td>${data[1]}</td>
    			</tr>
    			<tr>
    				<td>出版社:</td>
    				<td>${data[2]}</td>
    			</tr>
    			<tr>
    				<td>出版社:</td>
    				<td>${data[3]}</td>
    			</tr>
    			<tr>
    				<td>书本状态:</td>
    				<td>
    					<c:choose>
							<c:when test="${data[4]==1}">在架上</c:when>
							<c:otherwise>借出</c:otherwise>
						</c:choose>
					</td>
    			</tr>
    			<tr align="center">
    				<c:choose>
    					<c:when test="${user == null}">
    						<td colspan="2"><a>添加至借书列表</a></td>
    					</c:when>
    					<c:otherwise>
    						<c:choose>
    							<c:when test="${user.id eq '1'}">
    								<td colspan="2"><a href="addtolist.student?id=${data[0]}&account=${user.account}">添加至借书列表</a></td>
    							</c:when>
    							<c:otherwise>
    								<td colspan="2"><a>添加至借书列表</a></td>
    							</c:otherwise>
    						</c:choose>
    					</c:otherwise>
    				</c:choose>
    			</tr>
    		</table>
    	</div>
    </div>
  </body>
</html>
