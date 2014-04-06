<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Let'sPlay | Home</title>
<link href="<c:url value ="resourse/css/bootstrap.css"/>"
	rel="stylesheet">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">
					<fmt:message key="SORRY" bundle="${lang}" />
				</h1>
				<ol class="breadcrumb">
					<li><a href="<c:url value= "/home" />"><i
							class="fa fa-dashboard"></i> <fmt:message key="USER_HOME"
								bundle="${lang}" /></a></li>

				</ol>
			</div>
		</div>
	</div>

</body>
</html>