<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
    <title>body</title>
		<link rel="stylesheet" type="text/css" href="<c:url value='/views/css/common/lib.css'/>">
		<link rel="stylesheet" type="text/css" href="<c:url value='/views/css/common/navigation-menu.css'/>">
		<link rel="stylesheet" type="text/css" href="<c:url value='/views/css/common/shortcode.css'/>">
		<link rel="stylesheet" type="text/css" href="<c:url value='/views/css/common/style.css'/>">
		<link rel="stylesheet" type="text/css" href="<c:url value='/views/css/common/font-family.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/views/css/book/list2.css'/>">
    <script type="text/javascript" src="<c:url value='/views/js/jquery.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/views/js/book/lib.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/views/js/book/functions.js'/>"></script>
  </head>
  <style>
	</style>
  <body>
		<main>
			<!-- Product Section -->
			<div id="product-section" class="product-section container-fluid no-padding">
				<!-- Container -->
				<div class="container">
					<div class="row">
						<!-- Products -->
						<ul class="products">
							<!-- Product -->
							<c:if test="${empty allBook }"><span class="wu" style="margin-top: -5px">没有图书</span></c:if>
							<c:forEach items="${allBook }" var="book">
							<li class="product video" style="margin-bottom: 30px;margin-top:10px;">
								<a href="<c:url value='selectByBid?bid=${book.bid }'/>">
									<img src="<c:url value='/${book.imageb }'/>" border="0"/></a>
									<div style="width:280px;overflow:hidden;white-space:nowrap;text-overflow:ellipsis;">
									<a id="bookname" title="${book.bname }" href="<c:url value='selectByBid?bid=${book.bid }'/>">${book.bname }</a></div>
									<span class="price"><del>&yen;${book.price }</del>&yen;${book.currprice }</span>
									
									<div><a href="FindBook?author=${book.author }&bname&press" title='${book.author }'>${book.author }</a></div>
									<div><a href="FindBook?press=${book.press }&author&bname">${book.press }</a></div>
								
							</li><!-- Product /- -->
							</c:forEach>
							</ul>
					</div><!-- Row /- -->
					<%@include file="../pager/fenye.jsp" %>
					
				</div><!-- Container /- -->
			</div><!-- Product Section /- -->
			</main>
  </body>
<script type="text/javascript">
	function _go() {
		var pc = $("#pageCode").val();//获取文本框中的当前页码
		if(!/^[1-9]\d*$/.test(pc)) {//对当前页码进行整数校验
			alert("请输入正确的页码!");
			return;
		}
		if(pc > ${pb.tp}) {//判断当前页码是否大于最大页
			alert("超出页数!");
			return;
		}
		location = "${pb.url}&pc=" + pc;
	}
</script>
</html>
