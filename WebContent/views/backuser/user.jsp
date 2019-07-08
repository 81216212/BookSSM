<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>用户列表</title>
		<link rel="icon" type="image/png" href="<c:url value='/images/ico.png'/>">
		<link rel="stylesheet" href="<c:url value='/views/css/admin/amazeui.min.css'/>" />
		<link rel="stylesheet" href="<c:url value='/views/css/admin/admin.css'/>" />
		<link rel="stylesheet" href="<c:url value='/views/css/admin/common.css'/>" />
	    <script type="text/javascript" src="<c:url value='/views/js/jquery-1.5.1.js'/>"></script>
	</head>
  <body>
  <p style="display:none" id="ordermsg">${success }</p>
    <div class="am-cf admin-main">
			<div class=" admin-content">
				<div class="daohang">
					<ul>
						<li>
							<button type="button" class="am-btn am-btn-default am-radius am-btn-xs">首页</li> <li>
        <button type="button" class="am-btn am-btn-default am-radius am-btn-xs">
        用户管理<a class="am-close" title="关闭" href="<c:url value='/view/admin/body.jsp'/>">×</a>
        </button></ul></div>
				<div class="admin-biaogelist">
      <div class="listbiaoti am-cf">
        <ul class="am-icon-flag on">用户管理</ul>
        <dl class="am-icon-home" style="float: right;"> 当前位置： 首页 > <a href="#">用户列表</a></dl></div>
					
		<form class="am-form am-g" style="margin: -23px auto 15px auto;">
						<table width="100%" class="am-table am-table-bordered am-table-radius am-table-striped am-table-hover">
							<thead>
								<tr class="am-success">
									<th class="table-title">用户名称</th>
									<th class="table-title">用户密码</th>
									<th class="table-title">用户邮箱</th>
									<th class="table-title">用户状态</th>
									<th width="163px" class="table-set">操作</th>
								</tr>
							</thead>

`							<c:if test="${empty allUser }">没有用户</c:if>
				<c:forEach items="${allUser }" var="user" varStatus="num">
					<tbody>
						<tr>
							<td>${user.loginname }</td>
							<td>${user.loginpass }</td>
							<td>${user.email }</td>
							<td class="fu"><span class="state${num.index}">${user.status }</span></td>
							<td>
						<div class="am-btn-toolbar">
							<div class="am-btn-group am-btn-group-xs">
							<a title="激活用户" style="display:none;" class="button11" id="button${num.index}" onclick="return confirm('您是否要激活该用户?')" 
href="<c:url value='/admin/userActivation?uid=${user.uid }'/>">激活用户</a>
					<a title="删除用户" class="button" onclick="return confirm('您是否要删除该用户?')" 
href="<c:url value='/admin/userDelete?uid=${user.uid }'/>">删除</a>
							</div>
						</div>
							</td>
						</tr>
					</tbody>

				</c:forEach>

			</table>
			</form>
			<%@include file="/views/pager/pager.jsp" %>
	</body>
<script>
$(document).ready(function(){
	var ordermsg = $("#ordermsg").text();
	if(ordermsg.length!=0){
		alert($("#ordermsg").text());
	}
	});
		
$(function(){
	var b =$("span").size();
	for(var i = 0; i<=b;i++){
		var a =$(".state"+i).text();
		if(a=='false'){
			$(".state"+i).text("用户未激活");
			$(".state"+i).css("color","red");
			$("#button"+i).css("display","inline-block");
		}
		if(a=='true'){
			$(".state"+i).text("用户已激活");
			$(".state"+i).css("color","blue");
		}
	}
});
</script>
</html>