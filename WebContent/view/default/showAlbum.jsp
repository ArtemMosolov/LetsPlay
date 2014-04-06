<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="lp"%>

<!DOCTYPE html>

<div class="container">
	<div class="row">
		<br>
		<c:choose>
			<c:when test="${not empty AlbumError}">
				<h1 align="center">
					<fmt:message key="NO_ALBUMS" bundle="${lang}" />
				</h1>
			</c:when>
			<c:otherwise>
				<div class="jumbotron">
					<div class="container"></div>
					<div class="row">
						<div class="col-md-4">
							<c:if test="${album.albumDescription.image.fileName  ne null }">
								<img id="picture"
									src='<lp:fileTag fileName="${album.albumDescription.image.fileName}"/>'
									alt="Empty picture!">
							</c:if>
							<c:if test="${album.albumDescription.image.fileName eq null }">
								<img id="picture"
									src="<c:url value= "/resourse/image/800x500.gif" />">
							</c:if>
						</div>
						<div class="col-md-8">
							<h2>${album.nameAlbum}</h2>
							<p>${album.albumDescription.descriptionAlbum}</p>
						</div>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
	</div>

	<div class="col-lg-12">
		<c:choose>
			<c:when test="${not empty SongsError}">
				<h1 align="center">
					<fmt:message key="NO_SONGS" bundle="${lang}" />
				</h1>
			</c:when>
			<c:otherwise>
				<h1 align="center">
					<fmt:message key="SONGS_FOR_CURRENT_ALBUM" bundle="${lang}" />
				</h1>
				<br>
				<table class="table table-striped">
					<thead>
						<tr align="center">
							<td align="left"><fmt:message key="TOP" bundle="${lang}" /></td>
							<td align="left"></td>
							<td align="center"><fmt:message key="SONG_NAME"
									bundle="${lang}" /></td>
							<c:if test="${not empty sessionScope.userId}">
								<td align="center"><fmt:message key="ADD" bundle="${lang}" /></td>
							</c:if>
						</tr>
					</thead>
					<c:forEach items="${popularSongsForCurrentAlbum}" var="popularSong"
						varStatus="status">
						<tr align="center">
							<td align="left">${status.index+1}</td>

							<td align="left" width="65"><audio controls="">
									<source src="<lp:fileTag fileName="${popularSong.nameFile }"/>"
										type="audio/mpeg" />
								</audio></td>
							<td align="center">${popularSong.nameSong}</td>
							<td align="center"><c:if
									test="${not empty sessionScope.userId}">
									<div class="btn-group">
										<button type="button" class="btn btn-default dropdown-toggle"
											data-toggle="dropdown">
											<fmt:message key="ADD_TO" bundle="${lang}" />
											<span class="caret"></span>
										</button>
										<ul class="dropdown-menu" role="menu">
											<c:choose>
												<c:when test="${not empty playlists}">
													<c:forEach items="${playlists}" var="playlist">
														<li><a
															href='<c:url value="/music?action=add&songId=${popularSong.idSong}&playlistId=${playlist.key.idUserPlayList}"/>'>${playlist.key.namePlayList}
																<span class="badge"> ${playlist.value}</span>
														</a></li>
													</c:forEach>
												</c:when>
												<c:when test="${empty playlists }">
													<li><a href="#createPlaylist" data-toggle="modal"
														data-target="#createPlaylist"><fmt:message
																key="ADD_NEW_PLAYLIST" bundle="${lang}" /></a></li>
												</c:when>
											</c:choose>
										</ul>
									</div>
								</c:if></td>
						</tr>
					</c:forEach>
				</table>
			</c:otherwise>
		</c:choose>
	</div>


	<div class="row">
		<c:choose>
			<c:when test="${not empty AlbumsError}">
				<h1 align="center">
					<fmt:message key="NO_ALBUMS" bundle="${lang}" />
				</h1>
			</c:when>
			<c:otherwise>
				<h1 align="center">
					<fmt:message key="ALBUMS_FOR_CURRENT_ARTIST" bundle="${lang}" />
				</h1>
				<br>
				<c:forEach items="${albumsForCurrentArtist}" var="album">
					<div class="col-sm-6 col-md-4">
						<div class="thumbnail">
							<a href='<c:url value = "/showAlbum?idAlbum=${album.idAlbum}"/>'>
								<c:if test="${album.albumDescription.image.fileName ne null }">
									<img id="picture"
										src='<lp:fileTag fileName="${album.albumDescription.image.fileName}"/>'
										alt="Empty picture!">
								</c:if> <c:if test="${album.albumDescription.image.fileName eq null }">
									<img id="picture"
										src="<c:url value= "/resourse/image/800x500.gif" />">
								</c:if>
							</a>
							<div class="caption">
								<a href='<c:url value = "/showAlbum?idAlbum=${album.idAlbum}"/>'>
									<h3 align="center">${album.nameAlbum}</h3>
								</a>
							</div>
						</div>
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</div>
</div>

