<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>

<body>
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">
					<fmt:message key="ADMIN_HOME" bundle="${lang}" />
					<small><fmt:message key="SLOGON" bundle="${lang}" /></small>
				</h1>
				<ol class="breadcrumb">
					<li class="active"><fmt:message key="ADMIN_HOME"
							bundle="${lang}" /></li>
				</ol>
			</div>

			<div class="col-md-8">
				<div class="input-group" style="width: 300px;">
					<input type="text" class="form-control" id="searchinput"
						url="/LetsPlay/search"> <span class="input-group-addon">
						<span class="glyphicon glyphicon-search"></span>
					</span>
				</div>
			</div>

			<div class="col-md-4">
				<a href="<c:url value= "/admin/addArtist" />"
					class="btn btn-primary" role="button"><span
					class="glyphicon glyphicon-plus"></span> <fmt:message
						key="ADD_ARTIST" bundle="${lang}" /></a>
			</div>
		</div>

		<c:if test="${message!=null }">
			<div class="alert alert-warning">
				<p>
					<fmt:message key="${message}" bundle="${lang}" />
				</p>
			</div>
		</c:if>
		<div id="browser" style="margin: 50px;">
			<jsp:include page="browser.jsp" flush="true"></jsp:include>
		</div>
	</div>
</body>
</html>