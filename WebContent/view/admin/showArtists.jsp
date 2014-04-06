<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
</head>
<body id="browser">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">
					<fmt:message key="ADMIN_HOME" bundle="${lang}" />
				</h1>
				<ol class="breadcrumb">
					<li><a href="<c:url value= "/admin/home" />"><i
							class="fa fa-dashboard"></i> <fmt:message key="ADMIN_HOME"
								bundle="${lang}"></fmt:message></a></li>
					<li class="active"><i class="fa fa-dashboard"></i> <fmt:message
							key="Artist_List" bundle="${lang}"></fmt:message></li>
				</ol>
			</div>


			<div class="col-md-8">
				<div class="col-lg-6">
					<div class="input-group" style="width: 300px;">
						<input type="text" class="form-control" id="searchinput"
							url="/LetsPlay/admin/showArtist" value="${parametr}"> <span
							class="input-group-addon"> <span
							class="glyphicon glyphicon-search"></span>
						</span>
					</div>
				</div>
			</div>

			<div class="col-md-4">
				<div class="col-lg-4">
					<div class="btn-group">
						<a href="<c:url value= "/admin/addArtist" />"
							class="btn btn-primary" role="button"><span
							class="glyphicon glyphicon-plus"></span> <fmt:message
								key="ADD_ARTIST" bundle="${lang}" /></a>
					</div>
				</div>
			</div>
		</div>

		<c:if test="${message!=null }">
			<div class="alert alert-warning">
				<p>
					<fmt:message key="${message}" bundle="${lang}" />
				</p>
			</div>
		</c:if>
		<br>
		<div class="panel panel-success">
			<div class="panel-heading">
				<h3 align="center">
					<fmt:message key="Artist_List" bundle="${lang}"></fmt:message>
				</h3>
			</div>
			<table class="table">
				<tr align="center">
					<td><fmt:message key="ARTIST_NAME" bundle="${lang}"></fmt:message></td>
					<td width="100"><i class="glyphicon glyphicon-pencil"></i></td>
					<td width="100"><i class="glyphicon glyphicon-remove"></i></td>
				</tr>
				<c:forEach items="${list}" var="item">
					<tr align="center">
						<td><a
							href='<c:url value = "/admin/showAlbum?artistid=${item.idArtist}"/>'>${item.nameArtist}</a></td>
						<td><a
							href='<c:url value= "/admin/addArtist?artistid=${item.idArtist}" />'><i
								class="glyphicon glyphicon-pencil"></i></a></td>
						<td><a
							href='<c:url value = "/admin/delete?artistid=${item.idArtist}"/>'><i
								class="glyphicon glyphicon-remove"></i></a></td>
					</tr>
				</c:forEach>
			</table>
		</div>


	</div>

</body>
</html>