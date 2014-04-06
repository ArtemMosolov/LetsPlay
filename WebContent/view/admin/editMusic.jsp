<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="lp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>

	<div class="container">

		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">
					<fmt:message key="VALIDATE_SONG_NAME" bundle="${lang}"></fmt:message>
				</h1>
				<ol class="breadcrumb">
					<li><a href="<c:url value= "/admin/home" />"><i
							class="fa fa-dashboard"></i> <fmt:message key="ADMIN_HOME"
								bundle="${lang}"> </fmt:message></a></li>
					<li class="active"><i class="fa fa-edit"></i> <fmt:message key="Music"
								bundle="${lang}"> </fmt:message></li>
				</ol>
			</div>
		</div>


		<input type="hidden" name="artistId" value="${artist.idArtist}">



		<form class="form-horizontal" role="form"
			action='<c:url value = "/admin/addMusic"/>' method="post"
			id="MusicEdit">

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






				<c:forEach items='${songs}' var='song'>

					<div class="col-xs-12 col-sm-6 col-md-8">
						<div class="form-group">
							<label for="albumName" class="col-sm-3 control-label"> <fmt:message
									key="ENTER_SONG_NAME" bundle="${lang}" />
							</label>
							<div class="col-sm-5">
								<input name="${song.nameFile}" type="text" class="form-control"
									id="albumName"
									placeholder="<fmt:message key="Name_Song" bundle="${lang}" />"
									value="${song.nameSong}">
							</div>
						</div>

					</div>
					<div class="col-xs-6 col-md-4">
						<label for="select" class="col-lg-2 control-label">Selects</label>
						<div class="col-lg-10">
							<select class="form-control" id="select" name="${song.nameFile}">
								<c:forEach items="${genres}" var="genre">
									<option>${genre.key}</option>
								</c:forEach>

							</select> 
						</div>
					</div>
				</c:forEach>


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