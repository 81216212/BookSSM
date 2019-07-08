<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

	<head>
		<title>header</title>
		<link rel="stylesheet" type="text/css" href="<c:url value='/views/css/common/lib.css'/>">
		<link rel="stylesheet" type="text/css" href="<c:url value='/views/css/common/navigation-menu.css'/>">
		<link rel="stylesheet" type="text/css" href="<c:url value='/views/css/common/shortcode.css'/>">
		<link rel="stylesheet" type="text/css" href="<c:url value='/views/css/common/style.css'/>">
		<link rel="stylesheet" type="text/css" href="<c:url value='/views/css/common/font-family.css'/>">
		<link rel="stylesheet" type="text/css" href="<c:url value='/views/css/common/top.css'/>">
		<script type="text/javascript" src="<c:url value='/views/js/jquery.min.js'/>"></script>
	</head>
	<script type="text/javascript">
		$(document).ready(function() {
			$(".user").hover(function() {
				$(".zhongxin").slideToggle(100);
			});
		});
	</script>

	<body data-offset="200" data-spy="scroll" data-target=".ow-navigation">
		<div class="navbar-collapse collapse navbar-right" style="height: 35px;line-height: 39px;width:100%;background:#e8ebee;  padding-right: 0px;padding-left: 0px;" id="navbar">
			<ul class="nav navbar-nav" style="float: right;">
				<c:choose>
					<c:when test="${empty sessionScope.sessionUser }">
						<li class="zititle">
							<span>欢迎图书商城，请<a style="color:red" href="<c:url value='/system/jumpLogin'/>" target="_parent">登录</a>成为会员</span>
						</li>
						<li class="zititle">
							<a href="<c:url value='/system/jumpRegist'/>" target="_parent">注册</a>
						</li>
						<li class="zititle">
							<a href="<c:url value='/system/jumpLogin'/>" target="_parent">我的订单</a>
						</li>
					</c:when>
					<c:otherwise>
						<li class="user">
							<a class="zititle" href="#">用户：${sessionScope.sessionUser.loginname }</a>
							<ul class="zhongxin" style="line-height: 35px;width:78px;border-top: 2px solid #0152d9;top:100%;position: absolute;display: none;">

								<li style="font-size:12px;">
									<a href="<c:url value='/order/myOrders'/>" target="body">我的订单</a>
								</li>
								<li style="font-size:12px;">
									<a href="<c:url value='/views/system/pwd.jsp'/>" target="body">个人中心</a>
								</li>
								<li style="font-size:12px;">
									<a href="<c:url value='/system/quit'/>" target="_top">退出</a>
								</li>
							</ul>
						</li>
						<li class="zititle">
							<a href="<c:url value='/index.jsp'/>" target="_parent">主页</a>
						</li>
						<li class="zititle">
							<i class="icon_card"></i><a href="<c:url value='/cart/myCart'/>" target="body">购物车</a>
						</li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
		<!-- Container -->
		<div class="container">
			<!-- nav -->
			<div>
				<a href="<c:url value='/index.jsp'/>" target="_parent">
				<img style="float: left;width:110px; margin-top: 15px;" src="<c:url value='/images/login.png'/>"></a>
				<div class="navbar-header" style="margin-top: 35px;">
					<a style="color:#44a5ff" href="<c:url value='/index.jsp'/>" target="_parent" class="navbar-brand">图书<span>商城</span></a>
				</div>
			</div>
			<!-- nav /- -->
			<div style="margin-left: 100px; margin-top: 45px;float: left;">
				<form action="<c:url value='/book/FindBook?'/>" method="get" target="body" id="form1">
					<input class="souinp" type="text" autocomplete="off" name="bname" />
					<input type="hidden" name="author" />
					<input type="hidden" name="press" />
					<input type="hidden" name="page" value="1"/>
					<div class="fr">
						<button class="searchBtn" type="submit">搜索</button>
					</div>
				</form>
				<a target="body" href="<c:url value='/views/book/gj.jsp'/>" style="margin-left: 15px;position: absolute;margin-top: 9px;">多级搜索</a>
			</div>
		</div>
		<!-- Container /- -->
	</body>

</html>