<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>cartlist.jsp</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/views/css/common/lib.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/views/css/common/navigation-menu.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/views/css/common/shortcode.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/views/css/common/style.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/views/css/common/font-family.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/views/css/cart/newlist.css'/>">
<script src="<c:url value='/views/js/common/functions.js'/>"></script>
<script src="<c:url value='/views/js/jquery-1.5.1.js'/>"></script>
<script src="<c:url value='/views/js/common/round.js'/>"></script>
<script type="text/javascript">
	$(function() {
		showTotal(); //计算总计

		
		$("#selectAll").click(function() {
			
			var bool = $("#selectAll").attr("checked");
			
			setItemCheckBox(bool);
			
			setJieSuan(bool);
			
			showTotal();
		});

		
		$(":checkbox[name=checkboxBtn]").click(function() {
			var all = $(":checkbox[name=checkboxBtn]").length; //所有商品的个数
			var select = $(":checkbox[name=checkboxBtn][checked=true]").length; //获取所有被选择商品的个数

			if (all == select) { //全都选中了
				$("#selectAll").attr("checked", true); //勾选全选复选框
				setJieSuan(true); //让结算按钮有效
			} else if (select == 0) { //谁都没有选中
				$("#selectAll").attr("checked", false); //取消全选
				setJieSuan(false); //让结算失效
			} else {
				$("#selectAll").attr("checked", false); //取消全选
				setJieSuan(true); //让结算有效				
			}
			showTotal(); //重新计算总计
		});

		
		$(".jian")
				.click(
						function() {
							// 获取cartItemId
							var id = $(this).attr("id").substring(0, 20);
							// 获取输入框中的数量
							var quantity = $("#" + id + "Quantity").val();
							// 判断当前数量是否为1，如果为1,那就不是修改数量了，而是要删除了。
							if (quantity == 1) {
								if (confirm("您是否真要删除该商品？")) {
									location = "/cmj/cart/batchDelete?cartitemid="+ id;
								}
							} else {
								sendUpdateQuantity(id, quantity - 1);
							}
						});

		// 给加号添加click事件
		$(".jia").click(function() {
			// 获取cartItemId
			var id = $(this).attr("id").substring(0, 20);
			// 获取输入框中的数量
			var quantity = $("#" + id + "Quantity").val();
			sendUpdateQuantity(id, Number(quantity) + 1);
		});
	});

	// 请求服务器，修改数量。
	function sendUpdateQuantity(id, quantity) {
		$.ajax({
			async : false,
			cache : false,
			url : "/cmj/cart/updateQuantity",
			data : {
				cartitemid : id,
				quantity : quantity
			},
			type : "POST",
			dataType : "json",
			success : function(result) {
				//1. 修改数量
				$("#" + id + "Quantity").val(result.quantity);
				//2. 修改小计
				$("#" + id + "Subtotal").text(result.subtotal);
				//3. 重新计算总计
				showTotal();
			}
		});
	}

	
	function showTotal() {
		var total = 0;
		
		$(":checkbox[name=checkboxBtn][checked=true]").each(function() {
			//2. 获取复选框的值，即其他元素的前缀
			var id = $(this).val();
			//3. 再通过前缀找到小计元素，获取其文本
			var text = $("#" + id + "Subtotal").text();
			//4. 累加计算
			total += Number(text);
		});
		// 5. 把总计显示在总计元素上
		$("#total").text(round(total, 2)); //round()函数的作用是把total保留2位
	}

	
	function setItemCheckBox(bool) {
		$(":checkbox[name=checkboxBtn]").attr("checked", bool);
	}

	
	function setJieSuan(bool) {
		if (bool) {
			$("#jiesuan").removeClass("kill").addClass("jiesuan");
			$("#jiesuan").unbind("click"); //撤消当前元素止所有click事件
			$("#jiesuan").attr("title", "商品结算");
		} else {
			$("#jiesuan").removeClass("jiesuan").addClass("kill");
			$("#jiesuan").attr("title", "没有商品无法结算");
			$("#jiesuan").click(function() {
				return false;
			});
		}

	}

	
	function batchDelete() {
		// 1. 获取所有被选中商品的复选框
		// 2. 创建一数组，把所有被选中的复选框的值添加到数组中
		// 3. 指定location为CartItemServlet，参数method=batchDelete，参数cartItemIds=数组的toString()
		var cartItemIdArray = new Array();
		$(":checkbox[name=checkboxBtn][checked=true]").each(function() {
			cartItemIdArray.push($(this).val()); //把复选框的值添加到数组中
		});
		location = "/cmj/cart/batchDelete?cartitemid="+ cartItemIdArray;
	}

	
	function jiesuan() {
		// 1. 获取所有被选择的商品的id，放到数组中
		var cartItemIdArray = new Array();
		$(":checkbox[name=checkboxBtn][checked=true]").each(function() {
			cartItemIdArray.push($(this).val()); //把复选框的值添加到数组中
		});
		// 2. 把数组的值toString()，然后赋给表单的cartItemIds这个hidden
		$("#cartItemIds").val(cartItemIdArray.toString());
		// 把总计的值，也保存到表单中
		$("#hiddenTotal").val($("#total").text());
		// 3. 提交这个表单
		$("#jieSuanForm").submit();
	}

	$(document).ready(function() {
		$(".geren").mousedown(function() {
			$(".zhongxin").slideToggle(1);
		});
	});
	
	$(function(){
		if($(".cartmsg").text().length>0){
			alert($(".cartmsg").text()+"-图书库存不足");
		}
	});
</script>
</head>
<style>
</style>
<body data-offset="200" data-spy="scroll" data-target=".ow-navigation">
	<main><!-- Page Banner -->

	<div class="page-banner container-fluid no-padding">
		<!-- Container -->
		<div class="container">
			<div class="banner-content">
				<h3>购物车</h3>
				<p>尽情挑选您喜欢的商品吧！</p>
			</div>
			<ol class="breadcrumb">
				<li><a title="主页" href="<c:url value='/index.jsp'/>"
					target="_parent">主页</a></li>
				<li class="active">购物车</li>
			</ol>
		</div>
		<!-- Container /- -->
	</div>
	<!-- Page Banner /- --> <!-- Cart -->
	<div
class="woocommerce-cart container-fluid no-left-padding no-right-padding">
<!-- Container -->
<div class="container">
	<!-- Cart Table -->
<div class="col-md-12 col-sm-12 col-xs-12 cart-table">
	
	<table class="table table-bordered table-responsive">
		<thead>
			<tr>
				<td align="left" style="font-size: 14px;border-bottom: 0;">
					<input type="checkbox" id="selectAll" checked="checked" /><label
					for="selectAll">全选</label></td>
				<th>商品</th>
				<th>商品名称</th>
				<th>数量</th>
				<th>单价</th>
				<th>总价</th>
				<th>删除</th>
			</tr>
		</thead>
		<c:choose>
			<c:when test="${empty cartItemList }">
				<table class="table table-bordered table-responsive">
					<tr>
						<td align="right"><img align="top"
							src="<c:url value='/images/icon_empty.png'/>" /></td>
						<td><span class="spanEmpty">您的购物车中暂时没有商品
								&nbsp;快去购物吧!</span></td>
					</tr>
				</table>
			</c:when>
			<c:otherwise>

		<tbody>
	<c:forEach items="${cartItemList }" var="cartItem">
		<tr class="cart_item">
			<td align="left"><input value="${cartItem.cartitemid }"
				type="checkbox" name="checkboxBtn" checked="checked" /></td>
			<td style="padding:10px" class="product-thumbnail">
			<a class="linkImage" href="<c:url value='/book/selectByBid?bid=${cartItem.book.bid }'/>">
					<img border="0" style="width:110px;" align="top"
					src="<c:url value='/${cartItem.book.imageb }'/>" />
			</a></td>
			<td class="product-name"><a style="font-size:20px;"
				href="<c:url value='/book/selectByBid?bid=${cartItem.book.bid }'/>">
				<span>${cartItem.book.bname}</span>
			</a></td>
			<td class="product-quantity">
				<div class="prd-quantity">
					<input
						style="font-size:35px;padding: 12px 0px;line-height: 2px;"
						value="-" class="jian" id="${cartItem.cartitemid }jian"
						type="button"> <input class="quantity"
						name="quantity1" readonly="readonly"
						id="${cartItem.cartitemid }Quantity" type="text"
						value="${cartItem.quantity }" /> <input
						style="font-size:35px;padding: 12px 0px;line-height: 2px;"
						value="+" class="jia" id="${cartItem.cartitemid }jia"
						type="button">
				</div></td>
			<td><span style="font-size:20px;">&yen;<span
					class="currPrice">${cartItem.book.currprice }</span>
			</span></td>
			<td width="100px"><span style="font-size:20px;"
				class="price_n">&yen;<span class="subTotal"
					id="${cartItem.cartitemid }Subtotal">${cartItem.subtotal}</span>
			</span></td>
			<td><a title="删除商品"
				onclick="return confirm('您是否要删除该商品?')"
				href="<c:url value='/cart/batchDelete?cartitemid=${cartItem.cartitemid }'/>"><i
					class="icon icon-Delete"></i>
			</a></td>
		</tr>
	</c:forEach>

	<tr>
		<td colspan="2" class="tdBatchDelete"><a
			style="text-decoration: none;" title="删除已选商品"
			onclick="return confirm('您是否要删除已选择的商品?')"
			href="javascript:batchDelete();">批量删除</a></td>
		<td style="font-size:20px;" colspan="5" align="right"
			class="tdTotal"><span>总计：</span><span class="price_t">&yen;<span
				id="total"></span>
		</span></td>
	</tr>
	<tr>
		<td colspan="7" class="action"><a
			href="<c:url value='/views/system/main.jsp'/>" target="_parent"
			title="继续购物">继续购物</a> 
			<a href="javascript:jiesuan();"
			title="结算购物车" id="jiesuan" class="jiesuan">结算购物车</a></td>
			<span class="cartmsg" style="display: none;">${cartmsg }</span>
	</tr>
</tbody>
</table>
<form id="jieSuanForm" action="<c:url value='/cart/settle'/>" method="post">
	<input type="hidden" name="cartitemid" id="cartItemIds" /> 
	<input type="hidden" name="total" id="hiddenTotal" /> 
</form>

</c:otherwise>
</c:choose>
	</div>
	<!-- Cart Table /- -->
</div>
<!-- Container /- -->
</div>
	<!-- Cart /- --> </main>
</body>

</html>