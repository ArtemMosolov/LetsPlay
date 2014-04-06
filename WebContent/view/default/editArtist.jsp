<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>

	<div class="container">

		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">
					<fmt:message key="ARTIST_FORM" bundle="${lang}"></fmt:message>
				</h1>
			</div>
		</div>
		<form enctype="multipart/form-data" class="form-horizontal"
			role="form" action="addArtist" method="post" id="artistForm">

			<div class="row">
				<div class="col-xs-12 col-sm-6 col-md-8">
					<div class="form-group">

						<label for="ArtistName" class="col-sm-2 control-label"> <fmt:message
								key="ARTIST_NAME" bundle="${lang}" />
						</label>
						<div class="col-sm-5">
							<input name="artistName" type="text" class="form-control"
								id="ArtistName"
								placeholder="<fmt:message key="FIRST_NAME" bundle="${lang}" />"
								value="${userInfo.firstName}">
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
									value="${userInfo.description}" /></textarea>
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
							style="width: 200px; height: 300px;"></div>
						<div class="fileinput-preview fileinput-exists thumbnail"
							style="max-width: 200px; max-height: 300px;"></div>
						<div>
							<span class="btn btn-default btn-file"><span
								class="fileinput-new">Select image</span><span
								class="fileinput-exists">Change</span><input type="file"
								name="avatar"></span> <a href="#"
								class="btn btn-default fileinput-exists"
								data-dismiss="fileinput">Remove</a>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
