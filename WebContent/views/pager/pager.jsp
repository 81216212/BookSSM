<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
<title>分页</title>

<script type="text/javascript" src="<c:url value='/views/js/jquery.min.js'/>"></script>
<link rel="stylesheet" href="<c:url value='/views/pager/pager.css'/>" />

</head>
<style>
</style>
<script type="text/javascript">
	function _go() {
		var page = $("#pageCode").val();//获取文本框中的当前页码
		var number=/^[1-9]\d*$|^0$/;
		if(number.test(page)==false) {//对当前页码进行整数校验
			alert('请输入正确的页码！');
			$("#pageCode").val(${pageInfo.pageNum});
			return;
		}
		if(eval(page) > eval(${pageInfo.pages})) {//判断当前页码是否大于最大页
			alert('没有该页数！');
			$("#pageCode").val(${pageInfo.pageNum});
			return;
		}
		location = "${url}&page=" + page;
	};
</script>
<body>
<div class="divBody">
	<div class="divContent">
		<a class="aBtn bold" href="${url }&page=1">首页</a>


		<c:if test="${pageInfo.hasPreviousPage }">
			<a class="aBtn bold" href="${url }&page=${pageInfo.pageNum-1}">上一页</a>
		</c:if>
		
		
		
		<c:forEach var="i" begin="1" end="${pageInfo.pages}">
		  <a class="aBtn" href="${url }&page=${i}">${i}</a>
		</c:forEach>
		
		
		<c:if test="${pageInfo.hasNextPage }">
			<a class="aBtn bold" href="${url }&page=${pageInfo.pageNum+1}">下一页</a>
		</c:if>
		
		
		<a class="aBtn bold" href="${url }&page=${pageInfo.pages}">尾页</a>
		
		<span>共${pageInfo.pages }页</span>
		
		<span>到</span>
		<input style="width: 25px;" type="text" class="inputPageCode" id="pageCode" value="${pageInfo.pageNum}" />
		<span>页</span>
		
		<a href="javascript:_go();" class="aSubmit">确定</a>
		
		
</div>
</div>
</body>

</html>