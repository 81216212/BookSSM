<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>系统信息</title>
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
<style type="text/css">
body {
	font-family: 'Arizonia', cursive;
}
</style>
<script type="text/javascript">
	window.onload = function() {
		var myspan = document.getElementById("myspan");
		var timer = 5;
		var flag;
		function daoji() {
			timer = timer - 1;
			myspan.innerHTML = timer;
			if (timer == 0) {
				location.href = "jumpLogin";
				clearInterval(flag);
			}
		}
		flag = setInterval(daoji, 1000);
	}
</script>
</head>
<main><!-- Page Banner -->
<div class="page-banner container-fluid no-padding">
	<!-- Container -->
	<div class="container">
		<div class="banner-content">
			<h3>修改密码</h3>
			<p>更改您的密码！</p>
		</div>
		<ol class="breadcrumb">
			<li><a title="主页" href="<c:url value='/index.jsp'/>"
				target="_parent">主页</a></li>
			<li class="active">修改密码</li>
		</ol>
	</div>
	<!-- Container /- -->
</div>
<!-- Page Banner /- -->

<div
	class="woocommerce-cart container-fluid no-left-padding no-right-padding">
	<!-- Container -->
	<div class="container">
		<!-- Cart Table -->
		<div class="col-md-12 col-sm-12 col-xs-12 cart-table">

			<table class="table table-bordered table-responsive">
				<thead>

					<c:choose>
						<c:when test="${code eq 'success' }">
							<%--如果code是功能，它显示对号图片 --%>
							<c:set var="img" value="/images/dui.jpg" />
							<c:set var="title" value="修改密码成功" />
						</c:when>
						<c:when test="${code eq 'error' }">
							<%--如果code是功能，它显示错号图片 --%>
							<c:set var="img" value="/images/cuo.png" />
							<c:set var="title" value="修改密码失败" />
						</c:when>
					</c:choose>

					<tr>
						<th colspan="3">${title}</th>
					</tr>
					<tr align="center">
						<th align="center"><img style="margin: 0 auto;"
							src="<c:url value='${img }'/>" width="150" /></th>
						<th><span
							style="font-size: 30px; font-family: 'Arizonia', cursive; color: #c30;">${msg }</span>
						</th>
						<th align="center">页面登陆跳转倒计时:<a href="" id="myspan" target="_top"></a>
						</th>
					</tr>
				</thead>
			</table>
		</div>
		<!-- Login /- -->
	</div>
	<!-- Container /- -->
</div>
</main>
</body>
</html>
