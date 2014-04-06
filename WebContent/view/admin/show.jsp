<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="lp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="resourse/css/jasny-bootstrap.css" rel="stylesheet">
<script src="resourse/js/jasny-bootstrap.js"></script>
<script src="resourse/js/validation.js"></script>
</head>
<body>

	<div id="container">

		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">
					<fmt:message key="ADMIN_HOME" bundle="${lang}" />
				</h1>
				<ol class="breadcrumb">
					<li><a href="<c:url value= "/admin/home" />"><i
							class="fa fa-dashboard"></i> Home</a></li>
				</ol>
			</div>


			<div class="col-md-8">
				<div class="col-lg-6">
					<div class="input-group" style="width: 300px;">
						<input type="text" class="form-control" id="searchinput"
							onkeyup="searchFiles()"
							onKeyPress="return disableEnterKey(event)"> <span
							class="input-group-addon"> <span
							class="glyphicon glyphicon-search"></span>
						</span>
					</div>
				</div>
				<div class="col-lg-6">
					<div class="btn-group">
						<button type="button" class="btn btn-default dropdown-toggle"
							data-toggle="dropdown">
							Дропалка <span class="caret"></span>
						</button>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#">Robert 1</a></li>
							<li><a href="#">Robert 2</a></li>
							<li><a href="#">Robert 3</a></li>
						</ul>
					</div>
				</div>
			</div>

			<div class="col-md-4">
				<div class="col-lg-4">
					<div class="btn-group">
						<button type="button" class="btn btn-warning dropdown-toggle"
							data-toggle="dropdown">
							<fmt:message key="ADD_ITEM" bundle="${lang}" />
							<span class="caret"></span>
						</button>
						<ul class="dropdown-menu" role="menu">
							<li><a href="<c:url value= "/admin/addArtist" />"> <fmt:message
										key="ADD_ARTIST" bundle="${lang}" /></a></li>
							<li><a href="<c:url value= "/admin/addAlbum" />"><fmt:message
										key="ADD_ALBUM" bundle="${lang}" /></a></li>
							<li><a href="#"><fmt:message key="ADD_MUSIC"
										bundle="${lang}" /></a></li>
						</ul>
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
		<div class="panel panel-default">
			<div class="panel-heading"><h3 align="center">Панель для Роберта</h3></div>
			<table class="table">
				<tr align="center">
					<td>Напевно артисти</td>
					<td>Напевно альбоми</td>
					<td>Напевно пісні</td>
					<td width="100"><i class="glyphicon glyphicon-pencil"></i></td>
					<td width="100"><i class="glyphicon glyphicon-remove"></i></td>
				</tr>
				
				<tr align="center">
					<td>Тест 1</td>
					<td>Тест 2</td>
					<td>Тест 3</td>
					<td>Редагни мене</td>
					<td>Делітни мене</td>
				</tr>
				
				<tr align="center">
					<td>Тест 1</td>
					<td>Тест 2</td>
					<td>Тест 3</td>
					<td>Редагни мене</td>
					<td>Делітни мене</td>
				</tr>
				
			</table>
		</div>


	</div>

</body>
<script>
	$(document).ready(function() {
		$('#myTab a:last').tab('show');
	});
</script>
<script type="text/javascript">
	$('.fileinput').fileinput();
</script>

</html>