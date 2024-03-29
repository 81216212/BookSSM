<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

	<head>
		<title>My JSP 'bookdesc.jsp' starting page</title>
		<link rel="stylesheet" type="text/css" href="<c:url value='/views/css/backbook/add.css'/>">
		<link rel="stylesheet" href="<c:url value='/views/css/admin/common.css'/>" />
		<link rel="stylesheet" href="<c:url value='/views/css/admin/amazeui.min.css'/>" />
		<link rel="stylesheet" href="<c:url value='/views/css/admin/admin.css'/>" />
	    <script type="text/javascript" src="<c:url value='/views/js/jquery-1.5.1.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/views/js/jquery.datepick.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/views/js/jquery.datepick-zh-CN.js'/>"></script>
		<script type="text/javascript">
			$(function() {
				

				$("#btn").addClass("btn1");
				$("#btn").hover(
					function() {
						$("#btn").removeClass("btn1");
						$("#btn").addClass("btn2");
					},
					function() {
						$("#btn").removeClass("btn2");
						$("#btn").addClass("btn1");
					}
				);

				$("#btn").click(function() {
					var bname = $("#bname").val();
					var currPrice = $("#currPrice").val();
					var price = $("#price").val();
					var discount = $("#discount").val();
					var author = $("#author").val();
					var press = $("#press").val();
					var pid = $("#pid").val();
					var cid = $("#cid").val();
					var image_w = $("#image_w").val();
					var image_b = $("#image_b").val();

					if(!bname || !currPrice || !price || !discount || !author || !press || !pid || !cid || !image_w || !image_b) {
						alert("图名、当前价、定价、折扣、作者、出版社、1级分类、2级分类、大图、小图都不能为空！");
						return false;
					}

					if(isNaN(currPrice) || isNaN(price) || isNaN(discount)) {
						alert("当前价、定价、折扣必须是合法小数！");
						return false;
					}
					$("#form").submit();
				});
			});

			
			}
		</script>
	</head>
	<body>
		<div>
<div class="am-cf admin-main">
	<div class=" admin-content">
		<div class="daohang">
			<ul>
				<li style="margin:0px;margin-right:5px">
					<button type="button" class="am-btn am-btn-default am-radius am-btn-xs">首页</li>
        <li style="margin:0px;">
        <button type="button" class="am-btn am-btn-default am-radius am-btn-xs">
        商品图书管理<a class="am-close" title="关闭" href="<c:url value='/adminjsps/admin/body.jsp'/>">×</a>
        </button>
						</ul>
					</div>
				</div>
			</div>

			<div class="admin-biaogelist" style="background-color: #fff;">
				<div class="listbiaoti am-cf">
					<ul class="am-icon-flag on">
						商品图书管理
					</ul>
					<dl class="am-icon-home" style="float: right;">
						当前位置： 首页 >
						<a href="#">图书列表</a>
					</dl>
					<dl>
          <a title="添加图书" class="button" href="<c:url value='/admin/addBook'/>" style="width: 70px;">添加图书</a>
        </dl>
				</div>

				<form action="<c:url value='/admin/addBookMsg'/>" enctype="multipart/form-data" id="form">
					<p style="font-weight: 900; color: red;">${bookmsg }</p>
					<div>
						<ul>
							<li>书名：　<input id="bname" type="text" name="bname" value="" style="width:500px;" /></li>
							<li>产品图：<input id="image_w" type="file" name="imagew" /></li>
							<li>缩略图：<input id="image_b" type="file" name="imageb" /></li>
							<li>原价：<input id="currPrice" type="text" name="currprice" value="100" style="width:50px;" /></li>
							<li>折扣价：<input id="price" type="text" name="price" value="88" style="width:50px;" /> 
							 折扣：<input id="discount" type="text" name="discount" value="8.8" style="width:30px;" />折</li>
						</ul>
						<hr style="margin-left: 50px; height: 1px; color: #dcdcdc" />
						<table>
							<tr>
								<td colspan="3">
									作者：　　<input type="text" id="author" name="author" value="CMJ" style="width:150px;" />
								</td>
							</tr>
							<tr>
								<td colspan="3">
									出版社：　<input type="text" name="press" id="press" value="清华出版社" style="width:200px;" />
								</td>
							</tr>
							<tr>
								<td colspan="3">出版时间：<input type="date" id="publishtime" name="publishtime" value="2019-1-1" style="width:140px;" /></td>
							</tr>
							<tr>
								<td>字数：　　<input type="text" name="wordnum" id="wordNum" value="66666" style="width:80px;" /></td>
							</tr>
							<tr>
								<td>库存：　　<input type="text" name="total" id="total" value="100" style="width:80px;" /></td>
							</tr>
							<tr>
								<td>
									一级分类：
									<select name="pid" id="pid" onchange="loadChildren()">
										<option value="">====请选择1级分类====</option>
										<c:forEach items="${parents }" var="parent">
											<option value="${parent.cid }">${parent.cname }</option>
										</c:forEach>

									</select>
								</td>
								<td>
									二级分类：
									<select name="cid" id="cid">
										<option value="">====请选择2级分类====</option>
										<c:forEach items="${parents }" var="parent">
										<c:forEach items="${parent.children}" var="child">
											<option value="${child.cid }">${child.cname}</option>
										</c:forEach>
										</c:forEach>
									</select>
								</td>
								<td></td>
							</tr>
							<tr>
								<td>
									<input type="button" id="btn" class="btn" value="添加新书">
									<a href="<c:url value='/admin/adminfindallbook?rows=10&page=1'/>" target="body"><input type="button" class="btn" value="返回图书"></a>
								</td>
							</tr>
						</table>
					</div>
				</form>
			</div>
	</body>

</html>