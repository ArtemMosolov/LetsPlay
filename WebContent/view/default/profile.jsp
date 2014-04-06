<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<body>
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">
					<fmt:message key="USER_HOME" bundle="${lang}" />
				</h1>
				<ol class="breadcrumb">
					<li><a href="<c:url value= "/home" />"><i
							class="fa fa-dashboard"></i> <fmt:message key="USER_HOME"
								bundle="${lang}" /></a></li>
					<li class="active"><i class="fa fa-edit"></i> <fmt:message
							key="USER_PROFILE" bundle="${lang}" /></li>
				</ol>
			</div>
		</div>

		<div class="row">
			<div class="col-lg-12" align="right">
				<a href="#createPlaylist" data-toggle="modal"
					data-target="#createPlaylist" class="btn btn-primary"
					data-role="button"><fmt:message key="ADD_PLAYLIST"
						bundle="${lang}" /></a>
			</div>
		</div>

		<div class="row" align="center">
			<div class="playlist" style="width: 700px;">
				<div class="col-lg-12">
					<c:choose>
						<c:when test="${not empty playlists}">
							<div class="panel panel-success">
								<div class="panel-heading" align="center">
									<fmt:message key="USERS_PLAYLISTS" bundle="${lang}" />
								</div>
								<div class="panel-body">
									<c:if test="${playlists != null}">
										<table class="table table-striped">
											<thead>
												<tr align="center">
													<td align="center" width="300"><fmt:message
															key="NAME_PLAYLIST" bundle="${lang}" /></td>
													<td align="center"><fmt:message key="EDIT_PLAYLIST"
															bundle="${lang}" /></td>
													<td align="center"><fmt:message key="DELETE_PLAYLIST"
															bundle="${lang}" /></td>
												</tr>
											</thead>
											<c:forEach var="playlist" items="${playlists}">
												<tr align="center">
													<td width="300px"><a
														href='<c:url value = "/playlist?playlistId=${playlist.idUserPlayList}"/>'>
															${playlist.namePlayList}</a></td>
													<td><a
														href='<c:url value = "/createplaylist?playlistId=${playlist.idUserPlayList}"/>'><i
															class="glyphicon glyphicon-pencil"></i></a></td>
													<td><a
														href='<c:url value = "/deleteplaylist?playlistId=${playlist.idUserPlayList}"/>'><i
															class="glyphicon glyphicon-remove"></i></a></td>
												</tr>
											</c:forEach>
										</table>
									</c:if>
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<fmt:message key="USER_LIST_EMPTY" bundle="${lang}" />
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>


		<jsp:include page="modalAddPlaylist.jsp"></jsp:include>
	</div>
</body>
</html>