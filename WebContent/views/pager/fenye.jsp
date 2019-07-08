<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
<title>分页</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/views/css/common/lib.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/views/css/common/navigation-menu.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/views/css/common/shortcode.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/views/css/common/style.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/views/css/common/font-family.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/views/css/book/list2.css'/>">
<script type="text/javascript"
	src="<c:url value='/views/js/jquery.min.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/views/js/book/lib.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/views/js/book/functions.js'/>"></script>
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
	<nav class="ow-pagination" style="margin-top: 20px;">
	<ul class="pager">

		<li><a style="float: left; border: 0px;" href="${url }&page=1"><i
				style="font-style: normal;">首页</i></a></li>

		<li><a style="border: 0px;"><i
				tyle="font-style: normal;">${pageInfo.pageNum}</i></a></li>

		<c:if test="${pageInfo.hasPreviousPage }">
			<li><a style="float: left; border: 0px;"
				href="${url }&page=${pageInfo.pageNum-1}"><i><</i></a></li>
		</c:if>
		<li><a style="float: right; border: 0px;"
			href="javascript:_go();" class="aSubmit">确定</a></li>
		<li><i class="zi"
			style="display: inline-block; margin-top: -3px; float: right;">到
				<input style="width: 25px;" type="text" class="inputPageCode"
				id="pageCode" value="${pageInfo.pageNum}" />页
		</i></a></li>
		<li><i class="zi" style="float: right">共${pageInfo.pages }页</i></li>


		<li><a style="float: right; border: 0px;"
			href="${url }&page=${pageInfo.pages}"><i
				style="font-style: normal;">尾页</i></a></li>
		<c:if test="${pageInfo.hasNextPage }">
			<li><a style="float: right; border: 0px;"
				href="${url }&page=${pageInfo.pageNum+1}"><i>></i></a></li>
		</c:if>
		
	</ul>
	</nav>

</body>

</html>