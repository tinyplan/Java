<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>更新图书信息</title>
    <script type="text/javascript" src="./jquery/jquery-3.3.1.js"></script>
  	<style type="text/css">
  		#title{
  			font-size:20px;
  		}
  	</style>
  	<script type="text/javascript">
  		function check(){
  			var bno = $("#bno").val();
  			var bname = $("#bname").val();
  			var bauthor = $("#bauthor").val();
  			var bpub = $("#bpub").val();
  			if(bno == ''){
  				alert("书号不能为空");
  				$("#bno").focus();
  			}else if(bname == ''){
  				alert("书名不能为空");
  				$("#bname").focus();
  			}else if(bauthor == ''){
  				alert("作者不能为空");
  				$("#bauthor").focus();
  			}else if(bpub == ''){
  				alert("出版社不能为空");
  				$("#bpub").focus();
  			}else{
  				$.ajax({
  					url:'update.do',
  					type:'post',
  					data: {
  							bno:$("#bno").val(),
							bname:$("#bname").val(),
							bauthor:$("#bauthor").val(),
  							bpub:$("#bpub").val()
						},
					success:function(){
						alert("更新成功\n书号:"+$("#bno").val()+
								"\n书名:"+$("#bname").val()+
								"\n作者:"+$("#bpub").val());
						$("#bno").val('');
						$("#bname").val('');
						$("#bauthor").val('');
						$("#bpub").val('');
						if(confirm("是否返回首页")){
							
						}
					}
  				});
  			}
  			return false;
  		}
  	</script>
  </head>
  
  <body>
  	<b id="title">修改图书信息</b>
  	<hr>
	<form onSubmit="return check()">
		<table width="100%" bgcolor="#cccccc" cellpadding="1px" border="0px">
			<tr bgcolor="#ffffff">
				<td>请输入修改的图书ID</td>
				<td>书名</td>
				<td>作者</td>
				<td>出版社</td>
				<td>操作</td>
			</tr>
			<tr bgcolor="#ffffff">
				<td><input type="text" name="bno" value="${data[0]}" id="bno"/></td>
				<td><input type="text" name="bname" value="${data[1]}" id="bname"/></td>
				<td><input type="text" name="bauthor" value="${data[2]}" id="bauthor"/></td>
				<td><input type="text" name="bpub" value="${data[3]}" id="bpub"/></td>
				<td><input type="submit" value="确认"></td>
			</tr>
		</table>
	</form>
  </body>
</html>
