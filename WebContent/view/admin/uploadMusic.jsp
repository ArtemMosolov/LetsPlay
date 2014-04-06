<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="lp"%>
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
					<fmt:message key="ADD_MUSIC" bundle="${lang}"></fmt:message>
				</h1>
				<ol class="breadcrumb">
					<li><a href="<c:url value= "/admin/home" />"><i
							class="fa fa-dashboard"></i> <fmt:message key="HOME" bundle="${lang}" /></a></li>
					<li class="active"><i class="fa fa-edit"></i> <fmt:message key="Album" bundle="${lang}" /></li>
				</ol>
			</div>
		</div>

		<input type="hidden" name="artistId" value="${artist.idArtist}">

		<form enctype="multipart/form-data" class="form-horizontal"
			role="form" action='<c:url value = "/admin/upload"/>' method="post"
			id="addAlbumForm">

			<div class="row">
				<div class="col-sm-5">
					<div class="form-group">
						<c:if test="${not empty Error }">
							<c:out value="${Error}"></c:out>
							<p>
								<c:forEach items="${invalidFields}" var="field">
									<c:out value="${field}"></c:out>
								</c:forEach>
							</p>
						</c:if>
					</div>
				</div>

				<div class="col-xs-12 col-sm-6 col-md-8">
					<div class="form-group">
						<label for="albumName" class="col-sm-3 control-label"> <fmt:message
								key="ALBUM_TITLE_ENTER" bundle="${lang}" />
						</label>
						<div class="col-sm-5">
							<input name="albumName" type="text" class="form-control"
								id="albumName"
								placeholder="<fmt:message key="ALBUM_TITLE" bundle="${lang}" />"
								value="${album.nameAlbum}">
						</div>
					</div>
				</div>

				<c:if test="${not empty items}">
					<c:forEach var="i" begin="1" end="${items}">
						<div class="col-xs-12 col-sm-6 col-md-8">
							<div class="fileinput fileinput-new" data-provides="fileinput">
								<div class="fileinput-new thumbnail"
									style="width: 100px; height: 100px;"></div>

								<div class="fileinput-preview fileinput-exists thumbnail"
									style="max-width: 100px; max-height: 100px;"></div>
								<div>

									<span class="btn btn-default btn-file"><span
										class="fileinput-new"><fmt:message key="SELECT_SONG" bundle="${lang}" /></span><span
										class="fileinput-exists"><fmt:message key="CHANGE" bundle="${lang}" /></span><input type="file"
										name="avatar"></span> <a href="#"
										class="btn btn-default fileinput-exists"
										data-dismiss="fileinput"><fmt:message key="REMOVE" bundle="${lang}" /></a>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:if>

				<div class="form-group">
					<div class="col-sm-offset-5 col-sm-10">
						<button type="submit" class="btn btn-primary">
							<fmt:message key="ACCEPT" bundle="${lang}" />
						</button>
					</div>
				</div>
			</div>
		</form>
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