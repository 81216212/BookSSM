<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>body</title>
    <script type="text/javascript" src="<c:url value='/views/js/jquery-1.5.1.js'/>"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value='/views/css/common/libiao.css'/>">
  </head>
  <style>

  </style>
  <body>
	<section class="main_box">
		<div class="box">
			<ul>
				<li style="margin-left: 200px">
					<a>
						<h3>图书</h3>
						
						<img src="<c:url value='/images/1.jpg'/>" alt="img">
					</a>
				</li>
				<li style="margin-left: 10px">
					<a>
						<h3>图书</h3>
						
						<img src="<c:url value='/images/2.jpg'/>" alt="img">
					</a>
				</li>
				<li style="margin-left: 10px">
					<a>
						<h3>图书</h3>
						
						<img src="<c:url value='/images/3.jpg'/>" alt="img">
					</a>
				</li>
				<li style="margin-left: 10px">
					<a>
						<h3>图书</h3>
						
						<img src="<c:url value='/images/4.jpg'/>" alt="img">
					</a>
				</li>
				<li style="margin-left: 10px">
					<a>
						<h3>图书</h3>
						
						<img src="<c:url value='/images/5.jpg'/>" alt="img">
					</a>
				</li>
				<li style="margin-left: 10px" class="on">
					<a>
						<h3>图书</h3>
						
						<img src="<c:url value='/images/6.jpg'/>" alt="img">
					</a>
				</li>
			</ul>
		</div>
	</section>
	<script>
		$(".box ul li").hover(function(){
			$(this).addClass('on').siblings().removeClass('on');
		});
	</script>

		
  </body>
</html>
