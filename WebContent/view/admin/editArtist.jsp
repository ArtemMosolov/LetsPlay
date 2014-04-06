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
					<fmt:message key="ARTIST_FORM" bundle="${lang}"></fmt:message>
				</h1>

				<ol class="breadcrumb">
					<li><a href="<c:url value= "/admin/home" />"><i
							class="fa fa-dashboard"></i> <fmt:message key="ADMIN_HOME"
								bundle="${lang}"></fmt:message></a></li>
					<li class="active"><i class="fa fa-edit"></i> <fmt:message
							key="Artist" bundle="${lang}"></fmt:message></li>
				</ol>
			</div>
		</div>

		<form enctype="multipart/form-data" class="form-horizontal"
			role="form" action='<c:url value = "/admin/addArtist"/>'
			method="post" id="artistForm">

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
						<label for="ArtistName" class="col-sm-2 control-label"> <fmt:message
								key="ARTIST_NAME" bundle="${lang}" />
						</label>
						<div class="col-sm-5">
							<input name="artistName" type="text" class="form-control"
								id="ArtistName"
								placeholder="<fmt:message key="ARTIST_NAME" bundle="${lang}" />"
								value="${artist.nameArtist}">
						</div>
						<c:if test="${not empty artist.idArtist}">
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
												<li><a
													href="<c:url value= "/admin/addAlbum?artistid=${artist.idArtist}" />"><fmt:message
															key="ADD_ALBUM" bundle="${lang}" /></a></li>
											</ul>
										</div>
									</div>
								</div>
							</div>
						</c:if>
					</div>
					<div class="form-group">
						<label for="Title" class="col-sm-2 control-label"> <fmt:message
								key="ENTER_TITLE" bundle="${lang}" />
						</label>
						<div class="col-sm-5">
							<textarea id="Title"
								placeholder="<fmt:message key="TITLE" bundle="${lang}" />"
								name="title" class="form-control" rows="3"><c:out
									value="${artist.artistDescription.title}" /></textarea>
							<c:if test="${not empty artist.idArtist}">
								<input type="hidden" name="artistId" value="${artist.idArtist}">
							</c:if>
						</div>
					</div>
					<div class="form-group">
						<label for="Description" class="col-sm-2 control-label"> <fmt:message
								key="ENTER_DESCRIPTION" bundle="${lang}" />
						</label>
						<div class="col-sm-5">
							<textarea id="Description"
								placeholder="<fmt:message key="DESCRIPTION" bundle="${lang}" />"
								name="about" class="form-control" rows="3"><c:out
									value="${artist.artistDescription.artistDescription}" /></textarea>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-6 ">
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
							<c:if test="${not empty artist.artistDescription.image.fileName}">
								<img
									src='<lp:fileTag fileName="${artist.artistDescription.image.fileName}"/>' />
								<input type="hidden" name="idImage"
									value="${artist.artistDescription.idImage}">
							</c:if>
						</div>
						<div class="fileinput-preview fileinput-exists thumbnail"
							style="max-width: 200px; max-height: 300px;"></div>
						<div>
							<span class="btn btn-default btn-file"><span
								class="fileinput-new"><fmt:message key="SELECT_IMAGE" bundle="${lang}" /></span><span
								class="fileinput-exists"><fmt:message key="CHANGE" bundle="${lang}" /></span><input type="file"
								name="avatar"></span> <a href="#"
								class="btn btn-default fileinput-exists"
								data-dismiss="fileinput"><fmt:message key="REMOVE" bundle="${lang}" /></a>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		$('.fileinput').fileinput();
	</script>
</body>
<script type="text/javascript">
	$('.fileinput').fileinput();
</script>


</html>