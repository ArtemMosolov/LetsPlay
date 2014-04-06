<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="lp"%>
<!DOCTYPE html>
<html>
<body>

	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">
					<fmt:message key="ALBUM_FORM" bundle="${lang}"></fmt:message>
				</h1>
				<ol class="breadcrumb">
					<li><a href="<c:url value= "/admin/home" />"><i
							class="fa fa-dashboard"></i> <fmt:message key="ADMIN_HOME"
								bundle="${lang}">
							</fmt:message></a></li>
					<li class="active"><i class="fa fa-edit"></i> <fmt:message
							key="Album" bundle="${lang}">
						</fmt:message></li>
				</ol>
			</div>
		</div>


		<input type="hidden" name="artistId" value="${artist.idArtist}">

		<form enctype="multipart/form-data" class="form-horizontal"
			role="form" action='<c:url value = "/admin/addAlbum"/>' method="post"
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


					<c:if test="${empty artistid and empty album.idAlbum}">
						<div class="form-group">
							<label for="ArtistName" class="col-sm-3 control-label"> <fmt:message
									key="ARTIST_NAME" bundle="${lang}" />
							</label>
							<div class="col-sm-5">
								<input name="artistName" type="text" class="form-control"
									id="ArtistName"
									placeholder="<fmt:message key="FIRST_NAME" bundle="${lang}" />"
									value="${artist.nameArtist}">
							</div>
						</div>
					</c:if>
					<div class="form-group">
						<label for="albumName" class="col-sm-3 control-label"> <fmt:message
								key="ALBUM_NAME_ENTER" bundle="${lang}" />
						</label>
						<div class="col-sm-5">
							<input name="albumName" type="text" class="form-control"
								id="albumName"
								placeholder="<fmt:message key="ALBUM_NAME" bundle="${lang}" />"
								value="${album.nameAlbum}">
						</div>


						<c:if test="${not empty album.idAlbum}">
							<div class="col-xs-6 col-md-4">
								<div class="col-md-1">
									<div class="col-lg-1">
										<div class="btn-group">
											<button type="button" class="btn btn-warning dropdown-toggle"
												data-toggle="dropdown">
												<fmt:message key="ADD_ITEM" bundle="${lang}" />
												<span class="caret"></span>
											</button>
											<ul class="dropdown-menu" role="menu">
												<li><a href="#itemsModal" data-toggle="modal"
													data-target="#itemsModal"><fmt:message key="ADD_MUSIC"
															bundle="${lang}" /></a></li>
											</ul>
										</div>
									</div>
								</div>
							</div>

						</c:if>
					</div>
					<div class="form-group">
						<label for="albumTitle" class="col-sm-3 control-label"> <fmt:message
								key="ALBUM_TITLE_ENTER" bundle="${lang}" />
						</label>
						<div class="col-sm-5">
							<input name="albumTitle" type="text" class="form-control"
								id="albumTitle"
								placeholder="<fmt:message key="ALBUM_TITLE" bundle="${lang}" />"
								value="${album.albumDescription.titleAlbum}">
						</div>
					</div>


					<div class="form-group">
						<label for="about" class="col-sm-3 control-label"> <fmt:message
								key="ALBUM_DESCRIPTION_ENTER" bundle="${lang}" />
						</label>
						<div class="col-sm-5">
							<textarea id="about"
								placeholder="<fmt:message key="DESCRIPTION" bundle="${lang}" />"
								name="about" class="form-control" rows="3"><c:out
									value="${album.albumDescription.descriptionAlbum}" /></textarea>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-5 col-sm-10">
							<button type="submit" class="btn btn-primary">
								<fmt:message key="ACCEPT" bundle="${lang}" />
							</button>
						</div>
					</div>
				</div>



				<div class="col-xs-6 col-md-4">

					<div class="fileinput fileinput-new" data-provides="fileinput">
						<div class="fileinput-new thumbnail"
							style="width: 200px; height: 300px;">
							<c:if test="${not empty album.albumDescription.image.fileName }">
								<img
									src='<lp:fileTag fileName="${album.albumDescription.image.fileName}"/>' />
								<input type="hidden" name="idImage"
									value="${album.albumDescription.idImage}">
							</c:if>

						</div>
						<div class="fileinput-preview fileinput-exists thumbnail"
							style="max-width: 200px; max-height: 300px;"></div>
						<div>
							<span class="btn btn-default btn-file"><span
								class="fileinput-new"><fmt:message key="SELECT_IMAGE" bundle="${lang}" /></span><span
								class="fileinput-exists"><fmt:message key="CHANGE" bundle="${lang}" /></span><input type="file"
								name="albumImage"></span> <a href="#"
								class="btn btn-default fileinput-exists"
								data-dismiss="fileinput"><fmt:message key="REMOVE" bundle="${lang}" /></a>
						</div>
					</div>

				</div>
			</div>


		</form>
	</div>
	<div class="modal fade" id="itemsModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">
						<fmt:message key="SELECT_ITEMS_TO_ADD" bundle="${lang}" />
					</h4>
				</div>

				<form method="get" action="<c:url value= "/admin/upload"/>"
					name="create" id="form">
					<div class="col-xs-6 col-md-4">
						<label for="select" class="col-lg-2 control-label"><fmt:message
								key="ITEMS_TO_ADD" bundle="${lang}" /></label>
						<div class="col-lg-10">
							<select class="form-control" id="select" name="items">
								<c:forEach var="i" begin="1" end="25">
									<option>${i}</option>
								</c:forEach>
							</select> <input type="hidden" name="albumid" value="${album.idAlbum}" />
						</div>
					</div>
					<div class="modal-footer">
						<p>
							<button type="submit" class="btn btn-primary">
								<fmt:message key="ACCEPT" bundle="${lang}" />
							</button>
						</p>
					</div>
				</form>
			</div>

		</div>
	</div>

</body>
<script type="text/javascript">
	$(document).ready(function() {
		$('#itemsCreate').modal('show');
	});
</script>
<script>
	$(document).ready(function() {
		$('#myTab a:last').tab('show');
	});
</script>
<script type="text/javascript">
	$('.fileinput').fileinput();
</script>


</html>