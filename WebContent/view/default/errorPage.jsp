<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ERROR_PAGE</title>
<link href="<c:url value = "/resourse/js/bootstrap.js"/>"
	rel="stylesheet">
<script src="<c:url value= "/resourse/js/jquery-1.10.2.js" />"></script>
</head>
<body>
	<div class="container" align="center">
		<div class="col-lg-12">
			<h1 class="page-header">Sorry! We couldn't find your page. But
				we're working on it. Honest.</h1>
			<h2>
				<a href='<c:url value = "/home"/>'> Go to the front page </a>
			</h2>
		</div>
		<img src="<c:url value= "/resourse/image/polar-bear.jpg" />">
	</div>


</body>
</html>