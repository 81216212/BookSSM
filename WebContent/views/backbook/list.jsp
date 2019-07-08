<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>图书分类</title>

<script type="text/javascript" src="<c:url value='/views/js/backbook/list.js'/>"></script>
<script type="text/javascript" src="<c:url value='/views/js/jquery-1.5.1.js'/>"></script>
<link rel="stylesheet" type="text/css" href="<c:url value='/views/css/backbook/list.css'/>">
<link rel="stylesheet" href="<c:url value='/views/css/admin/amazeui.min.css'/>" />
<link rel="stylesheet" href="<c:url value='/views/css/admin/admin.css'/>" />
  </head>
  <body>
				<div class="daohang">
					<ul>
						<li style="height: 36px">
							<button type="button" class="am-btn am-btn-default am-radius am-btn-xs" style="margin-top:4px;">首页</li>
        <li style="height: 36px">
        <button type="button" class="am-btn am-btn-default am-radius am-btn-xs" style="margin-left:-204px;margin-top: 4px;">图书管理
<a class="am-close" title="关闭" href="<c:url value='/adminjsps/admin/body.jsp'/>">×</a>
        </button>
					</ul>
				</div>
				<div class="admin-biaogelist">
					<div class="listbiaoti am-cf">
						<ul class="am-icon-flag on">图书管理</ul>
						<dl class="am-icon-home" style="float: right;">当前位置： 首页 ><a href="#">图书列表</a></dl>
					</div>
<div class="divBook">
<ul style="margin-left: 3px;">
<c:if test="${empty allBook }"><h1>没有图书</h1></c:if>
<c:forEach items="${allBook }" var="book">
 <li style="margin-left:60px">
  <div class="inner">
    <a class="pic" href="adminFindBook?press&author&bname=${book.bname }">
    <img src="<c:url value='/${book.imageb }'/>" border="0"/></a>
    <p class="price" >
		<span class="price_n">&yen;${book.currprice }</span>
		<span class="price_r">&yen;${book.price }</span>
		(<span class="price_s">${book.discount }折</span>)
	</p>
	<c:url value="/BookServlet" var="authorUrl">
		<c:param name="method" value="adminfindByAuthor"/>
		<c:param name="author" value="${book.author }"/>
	</c:url>
	<c:url value="/BookServlet" var="pressUrl">
		<c:param name="method" value="adminfindByPress"/>
		<c:param name="press" value="${book.press }"/>
	</c:url>
	<p><a id="bookname" title="${book.bname }" href="adminFindBook?press&author&bname=${book.bname }">${book.bname }</a></p>
	<p><a href="adminFindBook?author=${book.author }&bname&press" name='P_zz' title='${book.author }'>${book.author }</a></p>
	<p class="publishing">
		<span>出版社：</span><a href="adminFindBook?press=${book.press }&author&bname">${book.press }</a>
	</p>
  </div>
 </li>
</c:forEach>
</ul>
</div>
<div style="float:left; width: 100%; text-align: center;">
	<%@include file="/views/pager/pager.jsp" %>
</div>
</div>
</div>
  </body>
</html>