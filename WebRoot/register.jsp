<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<c:choose>
  		<c:when test="${stu==null}">
			<title>注册界面</title>
			<style type="text/css">
				*{
					margin:0 auto;
				}
				b{
					font-size:20px;
				}
				#signin{
					padding-top:250px;
					text-align:center;
				}
				#content{
					height:750px;
					background-image: url("./image/1.jpg");
					background-size:cover;
					text-align:center;
				}
				table{
					height:200px;
					width:500px;
				}
				input{
					height:30px;
				}
				#button{
					text-align:center;
				}
			</style>
		</c:when>
		<c:otherwise>
			<c:choose>
				<c:when test="${status=='1'}">
					<title>注册成功</title>
				</c:when>
				<c:otherwise>
					<meta http-equiv="Refresh" content="5;url=./register.jsp ">
					<title>注册失败</title>
				</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose> 
  </head>
  
  <body>
  	<c:choose>
  		<c:when test="${stu==null}">
  		<div id="content">
  		
			<form action="abc.sign" method="post" id="signin">
				<b>注册账户</b>
				<table>
				<tr>
					<td align="right" width=30%>学号：</td>
					<td>
						<input type="text" name="sno" value="请输入学号" 
						onfocus='if(this.value=="请输入学号"){this.value="";};'
						onblur='if(this.value==""){this.value="请输入学号";};' 
						size=30>
					</td>
				</tr>
				<tr>
					<td align="right">姓名：</td>
					<td>
						<input type="text" name="sname" value="请输入姓名"
						onfocus='if(this.value=="请输入姓名"){this.value="";};'
						size=30>
					</td>
				</tr>
				<tr>
					<td align="right">性别：</td>
					<td>
						<input type="radio" name="gender" value="男" checked="checked">男
						<input type="radio" name="gender" value="女">女
					</td>
				</tr>
				<tr>
					<td align="right">年龄：</td>
					<td><input type="text" name="sage" value="请输入年龄"
						onfocus='if(this.value=="请输入年龄"){this.value="";};'
						size=30>
					</td>
				</tr>
				<tr>
					<td align="right">密码：</td>
					<td>
						<input type="text" name="spwd" value="请输入密码" 
						onfocus='if(this.value=="请输入密码"){this.value="";};'
						size=30/>
					</td>
				</tr>
				<tr>
					<td colspan=2 id="button"><input type="submit" value="提交"/>
									<input type="reset" value="重置" /></td>
				</tr>
				</table>
			</form>
		</div>
		</c:when>
		<c:otherwise>
			<c:choose>
				<c:when test="${status=='1'}">
					用户名：<c:out value="${stu.sno}"></c:out><br/>
				  	用户姓名：<c:out value="${stu.sname}"></c:out><br/>
				  	登录密码：<c:out value="${stu.spwd}"></c:out><br/>
				    	注册成功<br/>
				    <a href="./index.jsp">返回主界面</a>
				</c:when>
				<c:otherwise>
					输入的内容不合法<br/>
					注册失败<br/>
					即将返回注册界面<br/>
	    			<a href="./register.jsp">若5秒后未返回,请点击返回注册界面</a>
				</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose> 
  </body>
</html>
