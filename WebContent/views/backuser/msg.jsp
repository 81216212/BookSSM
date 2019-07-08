<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <link rel="stylesheet" href="<c:url value='/views/css/admin/amazeui.min.css'/>" />
		<link rel="stylesheet" href="<c:url value='/views/css/admin/admin.css'/>" />
		<link rel="stylesheet" href="<c:url value='/views/css/admin/common.css'/>" />
  </head>
  
  <body>
 <div class="am-cf admin-main">
			<div class=" admin-content">
				<div class="daohang">
					<ul>
						<li>
							<button type="button" class="am-btn am-btn-default am-radius am-btn-xs">首页</li> <li>
        <button type="button" class="am-btn am-btn-default am-radius am-btn-xs">
        用户管理<a class="am-close" href="<c:url value='/adminjsps/admin/body.jsp'/>">×</a>
        </button></ul></div>
				<div class="admin-biaogelist">
      <div class="listbiaoti am-cf">
        <ul class="am-icon-flag on">用户管理</ul>
        <dl class="am-icon-home" style="float: right;"> 当前位置： 首页 > <a href="#">用户列表</a></dl></div>
					<span style="float: left;margin-left: 20px;">${success }</span>
<a class="button11" href="<c:url value='/admin/finduser?rows=10&page=1'/>" target="body">返回用户管理</a>
  </body>
</html>
