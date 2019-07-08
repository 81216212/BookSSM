<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<head>
		<title>订单列表</title>
		<link rel="stylesheet" href="<c:url value='/views/css/admin/amazeui.min.css'/>" />
		<link rel="stylesheet" href="<c:url value='/views/css/admin/admin.css'/>" />
		<link rel="stylesheet" href="<c:url value='/views/css/admin/common.css'/>" />
	    <script type="text/javascript" src="<c:url value='/views/js/jquery-1.5.1.js'/>"></script>
	    <script type="text/javascript" src="<c:url value='/views/js/vue.js'/>"></script>
	</head>
	<style>
	a{
		color:#3265d9;
	}
	</style>
<script type="text/javascript">
$(function(){
	if($("#ordermsg").text().length>0){
		alert($("#ordermsg").text());
	}
});

</script>
  <body>
    <div class="am-cf admin-main">
			<div class=" admin-content">
				<div class="daohang">
					<ul>
						<li>
							<button type="button" class="am-btn am-btn-default am-radius am-btn-xs">首页</li> <li>
        <button type="button" class="am-btn am-btn-default am-radius am-btn-xs">
        订单管理<a class="am-close" title="关闭" href="<c:url value='/adminjsps/admin/body.jsp'/>">×</a>
        </button></ul></div>
				<div class="admin-biaogelist">
      <div class="listbiaoti am-cf">
        <ul class="am-icon-flag on">订单管理</ul>
        <dl class="am-icon-home" style="float: right;"> 当前位置： 首页 > <a href="#">订单列表</a></dl></div>	
				<span id="ordermsg">${ordermsg }</span>
					<div class="am-form am-g" style="margin: -23px auto 15px auto;">
					<div>
  <a href="<c:url value='/admin/adminOrderByFind?status=1&ordertime=${ordertime }&oid=${oid }'/>">未付款</a>  | 
  <a href="<c:url value='/admin/adminOrderByFind?status=2&ordertime=${ordertime }&oid=${oid }'/>">已付款</a>  | 
  <a href="<c:url value='/admin/adminOrderByFind?status=3&ordertime=${ordertime }&oid=${oid }'/>">已发货</a>  | 
  <a href="<c:url value='/admin/adminOrderByFind?status=4&ordertime=${ordertime }&oid=${oid }'/>">交易成功</a>  | 
  <a href="<c:url value='/admin/adminOrderByFind?status=5&ordertime=${ordertime }&oid=${oid }'/>">已取消</a>
</div>
	<form style="width:600px;float:right;" action="<c:url value='/admin/adminOrderByFind'/>" method="get" target="body" id="form1">
        <input type="text" name="oid" style="border-radius:4px;box-sizing:border-box;border:1px solid #c8cccf;
        height:28px;width:200px;float: left;" placeholder="搜索订单" autocomplete="off" value="${oid }">
        <input type="date" value="${ordertime }" name="ordertime" style="border-radius:4px;box-sizing:border-box;border:1px solid #c8cccf;
        height:28px;width:200px;float:left;margin-left:30px;">
        <input type="hidden" value="" name="status">
        <button style="height: 28px;border-radius: 5px;margin-left: -5px;" class="am-btn am-btn-xs am-xiao">
		<a style="color:black" href="javascript:document.getElementById('form1').submit();" target="body">搜索</a></button>
	</form>

	<table width="100%" class="am-table am-table-bordered am-table-radius am-table-striped am-table-hover">
		<thead>
			<tr class="am-success">
				<th style="width:30%;line-height:40px;" class="table-title">商品信息
				<span class="status" style="display:none">${status }</span></th>
				<th style="width:20%;line-height: 40px;" class="table-title">下单时间</th>
				<th style="width:10%;line-height: 40px;" class="table-title">用户</th>
				<th style="width:15%;line-height: 40px;" class="table-title">金额</th>
				<th style="width:15%;line-height: 40px;" class="table-title">订单状态</th>
				<th style="width:10%;line-height: 40px;" class="table-set">操作</th>
			</tr>
		</thead>

		<c:forEach items="${allOrder }" var="order">	
		<c:if test="${empty allOrder }">没有订单</c:if>
			<tbody>
				<tr>	
					<td>订单号：<a href="<c:url value='/admin/adminLoadOrder?oid=${order.oid }'/>">${order.oid }</a></td>
					<td>${order.ordertime }</td>
					<td colspan="4">${order.user.loginname }</td>
				</tr>
				<tr>
					<td colspan="3"><c:forEach items="${order.orderItems }" var="orderItem">
			<img border="0" width="70" src="<c:url value='/${orderItem.book.imageb}'/>"/>
							</c:forEach></td>
					<td><span>&yen;${order.total }</span></td>
					<td>
					<c:choose>
				<c:when test="${order.status eq 1 }">(等待付款)</c:when>
			<c:when test="${order.status eq 2 }">(准备发货)</c:when>
					<c:when test="${order.status eq 3 }">(等待确认)</c:when>
				<c:when test="${order.status eq 4 }">(交易成功)</c:when>
		<c:when test="${order.status eq 5 }">(已取消)</c:when>
			</c:choose>	
					</td>
					<td>
						<div class="am-btn-toolbar">
							<div class="am-btn-group am-btn-group-xs">
							<a title="查看订单" class="button11" href="<c:url value='/admin/adminLoadOrder?oid=${order.oid }'/>">查看</a><br/>
							<a title="删除订单" class="button11" onclick="return confirm('您是否真要删除该订单?')" style="color:red"
							href="<c:url value='/admin/adminDeleteOrder?oid=${order.oid }'/>">删除</a><br/>
<c:if test="${order.status eq 1 }">
<a title="取消订单" class="button11" onclick="return confirm('您是否要取消该订单？')" href="<c:url value='/admin/adminCancelOrder?oid=${order.oid }'/>">取消</a><br/>						
</c:if>
<c:if test="${order.status eq 2 }">
<a title="订单发货" class="button11" onclick="return confirm('您是否要进行发货？')" href="<c:url value='/admin/adminDeliverOrder?oid=${order.oid }'/>">发货</a><br/>
</c:if>	
				</div>
			</div>
		</td>
	</tr>
</tbody>
</c:forEach>
	</table>
	</div>
	<%@include file="/views/pager/pager.jsp" %>
	</body>
</html>