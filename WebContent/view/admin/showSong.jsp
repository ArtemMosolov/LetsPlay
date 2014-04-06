<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="lp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
								bundle="${lang}" /></a></li>
					<li class="active"><i class="fa fa-dashboard"></i> <fmt:message
							key="Song_List" bundle="${lang}"></fmt:message></li>
				</ol>
			</div>


			<div class="col-md-8">
				<div class="col-lg-6">
					<div class="input-group" style="width: 300px;">
						<input type="text" class="form-control" id="searchinput"
							url="/LetsPlay/admin/showSong" value="${parametr}"> <span
							class="input-group-addon"> <span
							class="glyphicon glyphicon-search"></span>
						</span>
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
					<fmt:message key="Song_List" bundle="${lang}"></fmt:message>
				</h3>
			</div>
			<table class="table">
				<tr align="center">
					<td><fmt:message key="Name_Song" bundle="${lang}"></fmt:message></td>
					<td><fmt:message key="ARTIST_NAME" bundle="${lang}"></fmt:message></td>
					<td><fmt:message key="Name_Album" bundle="${lang}"></fmt:message></td>
					<td width="180" align="left"><i
						class="glyphicon glyphicon-play"></i></td>
					<td width="100"><i class="glyphicon glyphicon-remove"></i></td>
				</tr>

				<c:forEach items="${list}" var="song">

					<tr align="center">
						<td>${song.nameSong}</td>
						<td><a
							href='<c:url value = "/admin/showSong?nameArtist=${song.nameArtists}"/>'>${song.nameArtists}</a></td>
						<td><a
							href='<c:url value = "/admin/showSong?albumid=${song.idAlbum}"/>'>${song.nameAlbum}</a></td>
						<td align="left">
							<div class="btn-group">
								<button type="button" class="btn btn-default dropdown-toggle"
									data-toggle="dropdown">
									<i class="glyphicon glyphicon-play"></i> <span class="caret"></span>
								</button>
								<ul class="dropdown-menu">
									<li><audio controls> <source
											src='<lp:fileTag fileName ="${song.nameFile}" />'
											type="audio/mpeg"></source></audio></li>
								</ul>
							</div>
						</td>
						<td><a
							href='<c:url value = "/admin/delete?songid=${song.idSong}"/>'><i
								class="glyphicon glyphicon-remove"></i></a></td>
					</tr>

				</c:forEach>

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