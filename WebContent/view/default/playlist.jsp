<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="lp"%>

<!DOCTYPE html>
<html lang="en">

<body>
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">
					<c:out value="${playlist.namePlayList }"></c:out>
				</h1>
				<ol class="breadcrumb">
					<li><a href="<c:url value= "/home" />"><i
							class="fa fa-dashboard"></i> <fmt:message key="USER_HOME"
								bundle="${lang}" /></a></li>
					<li><a href="<c:url value= "/profile" />"><i
							class="fa fa-dashboard"></i> <fmt:message key="USER_PROFILE"
								bundle="${lang}" /></a></li>
					<li class="active"><i class="fa fa-edit"></i> <fmt:message
							key="PLAYLIST" bundle="${lang}" /></li>
				</ol>
			</div>
		</div>

		<c:if test="${songs != null}">
			<div class="container" align="center">
				<div class="row">
					<c:choose>
						<c:when test="${not empty songs}">
							<audio id="audio" preload="auto" tabindex="0" controls="">
								<source type="audio/mp3"
									src="<lp:fileTag fileName="${songs[0].nameFile }"/>">
							</audio>

							<ul id="playlist">
								<c:forEach var="song" items="${songs}">
									<li id="item"><a class="song"
										href='<lp:fileTag fileName="${song.nameFile }"/>'>
											${song.nameArtists} - ${song.nameSong}</a> <a id="delete"
										href='<c:url value = "/music?action=delete&songId=${song.idSong}&playlistId=${playlist.idUserPlayList}"/>'><i
											class="glyphicon glyphicon-remove"></i></a></li>
								</c:forEach>
							</ul>
						</c:when>
						<c:otherwise>
							<fmt:message key="EMPTY_PLAYLIST" bundle="${lang}" />
							<fmt:message key="CALL_ADD_SONGS" bundle="${lang}" />
							<a href="<c:url value= "/home" />"> <fmt:message key="HERE"
									bundle="${lang}" /></a>

						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</c:if>
	</div>

	<script src="resourse/js/playlist.js"></script>

</body>
</html>