<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>boo_gj.jsp</title>
<link rel="stylesheet" href="<c:url value='/views/css/admin/amazeui.min.css'/>" />
<link rel="stylesheet" href="<c:url value='/views/css/admin/admin.css'/>" />
<link rel="stylesheet" href="<c:url value='/views/css/admin/common.css'/>" />
<style type="text/css">
	table {
		color: #404040;
		font-size: 10pt;
	}
</style>
  </head>
  

  <body>
    <div class="am-cf admin-main">
			<div class=" admin-content">
				<div class="daohang">
					<ul>
						<li>
							<button type="button" class="am-btn am-btn-default am-radius am-btn-xs">首页</li>
        <li>
        <button type="button" class="am-btn am-btn-default am-radius am-btn-xs">
        搜索管理<a class="am-close" title="关闭" href="<c:url value='/adminjsps/admin/body.jsp'/>">×</a>
        </button>
					</ul>
				</div>


				<div class="admin-biaogelist">
					<div class="listbiaoti am-cf">
						<ul class="am-icon-flag on">
							搜索管理
						</ul>
						<dl class="am-icon-home" style="float: right;">
							当前位置： 首页 >
							<a href="#">搜索列表</a>
						</dl>
						
					</div>
  <form action="<c:url value='/admin/adminTopGj'/>" method="get">
<table align="center">
	<tr>
		<td>书名：</td>
		<td><input type="text" name="bname"/></td>
	</tr>
	<tr>
		<td>作者：</td>
		<td><input type="text" name="author"/></td>
	</tr>
	<tr>
		<td>出版社：</td>
		<td><input type="text" name="press"/></td>
	</tr>
	<tr>
		<td><input type="hidden" name="page" value="1"/></td></td>
		<td>
			<input type="submit" value="搜　　索"/>
			<input type="reset" value="清　　空"/>
		</td>
	</tr>
</table>
	</form>
  </body>
</html>
