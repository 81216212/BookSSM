<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>ordersucc.jsp</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='/views/css/common/lib.css'/>">
		<link rel="stylesheet" type="text/css" href="<c:url value='/views/css/common/navigation-menu.css'/>">
		<link rel="stylesheet" type="text/css" href="<c:url value='/views/css/common/shortcode.css'/>">
		<link rel="stylesheet" type="text/css" href="<c:url value='/views/css/common/style.css'/>">
		<link rel="stylesheet" type="text/css" href="<c:url value='/views/css/common/font-family.css'/>">
  </head>
<style>
	body{
	font-size: 18px;
	}
</style>  
		<main><!-- Page Banner -->
				<div class="page-banner container-fluid no-padding"><!-- Container -->
					<div class="container">
						<div class="banner-content">
							<h3>订单列表</h3>
							<p>查看您的订单记录！</p>
						</div>
						<ol class="breadcrumb">
							<li>
								<a title="主页" href="<c:url value='/index.jsp'/>" target="_parent">主页</a>
							</li>
							<li class="active">购物车</li>
						</ol>
					</div><!-- Container /- -->
				</div><!-- Page Banner /- -->
				
				<div class="woocommerce-cart container-fluid no-left-padding no-right-padding">
					<!-- Container -->
					<div class="container">
						<!-- Cart Table -->
						<div class="col-md-12 col-sm-12 col-xs-12 cart-table">

							<table class="table table-bordered table-responsive">
								<thead>
								<tr>
									<th colspan="3"><span>订单已生成</span></th>
								</tr>
									<tr>
										<th>订单编号</th>
										<th>合计金额</th>
										<th>收货地址</th>
									</tr>
								</thead>
	<tr align="center">
		<th>${order.oid }</th>
		<th><span class="price_t">&yen;${order.total }</span></th>
		<th>${order.address }</th>
	</tr>
	<tr>
		<td colspan="5" class="action" align="right">
			<a style="background: #e4ddc6;" href="<c:url value='/order/payMoney?oid=${order.oid }&merchantOrder=${order.address }&total=${order.total }'/>" id="linkPay">支付</a>
		</td>
	</tr>
	
  </body>
</html>
