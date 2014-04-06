<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="<c:url value = "/resourse/css/fileuploader.css"/>"
	rel="stylesheet">
<link href="<c:url value = "/resourse/css/jquery.ui.css"/>"
	rel="stylesheet">
<script
	src='http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js'></script>
<script src='<c:url value="/resourse/js/fileuploader.js"/>'></script>
<script src='<c:url value="/resourse/js/script.js"/>'></script>
</head>
<body>
	<div class="container">
		<div id="fileuploader_many" urlsite='<c:url value = "/admin/upload"/>'></div>
	</div>
</body>
</html>