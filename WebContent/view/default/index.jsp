<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="lp"%>

<!DOCTYPE html>
<div id="myCarousel" class="carousel slide">
	<ol class="carousel-indicators">
		<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
		<li data-target="#myCarousel" data-slide-to="1"></li>
	</ol>
	<div class="carousel-inner">
		<div class="item active">
			<div class="fill">
				<img src="resourse/image/slide1.png">
			</div>
		</div>
		<div class="item">
			<div class="fill">
				<img src="resourse/image/slide2.png">
			</div>
		</div>
	</div>
	<a class="left carousel-control" href="#myCarousel" data-slide="prev">
		<span class="icon-prev"></span>
	</a> <a class="right carousel-control" href="#myCarousel" data-slide="next">
		<span class="icon-next"></span>
	</a>
</div>


<div class="container">
	<div class="row">
		<c:choose>
			<c:when test="${not empty ArtistsError}">
				<h1 align="center">
					<fmt:message key="NO_ARTISTS" bundle="${lang}" />
				</h1>
			</c:when>
			<c:otherwise>
				<h1 align="center">
					<fmt:message key="MOST_POPULAR_ARTISTS" bundle="${lang}" />
				</h1>
				<br>
				<c:forEach items="${popularArtistsList}" var="popularArtist">
					<div class="col-sm-6 col-md-4">
						<div class="thumbnail">
							<a
								href='<c:url value = "/showArtist?idArtist=${popularArtist.idArtist}"/>'>

								<c:if
									test="${popularArtist.artistDescription.image.fileName ne null }">
									<img id="picture"
										src='<lp:fileTag fileName="${popularArtist.artistDescription.image.fileName}"/>'
										alt="Empty picture!">
								</c:if> <c:if
									test="${popularArtist.artistDescription.image.fileName eq null }">
									<img id="picture"
										src="<c:url value= "/resourse/image/800x500.gif" />">
								</c:if>
							</a>
							<div class="caption">
								<a
									href='<c:url value = "/showArtist?idArtist=${popularArtist.idArtist}"/>'>
									<h3 align="center">${popularArtist.nameArtist}</h3>
								</a>
							</div>
						</div>
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</div>

	<div class="col-xs-12">
		<c:choose>
			<c:when test="${not empty SongsError}">
				<h1 align="center">
					<fmt:message key="NO_SONGS" bundle="${lang}" />
				</h1>
			</c:when>
			<c:otherwise>
				<h1 align="center">
					<fmt:message key="MOST_POPULAR_SONGS" bundle="${lang}" />
				</h1>
				<br>

				<table class="table table-striped">
					<thead>
						<tr align="center">
							<td align="left"><fmt:message key="TOP" bundle="${lang}" /></td>
							<td align="left"></td>
							<td align="center"><fmt:message key="SONG_NAME"
									bundle="${lang}" /></td>
							<td align="center"><fmt:message key="VOTES" bundle="${lang}" /></td>
							<c:if test="${not empty sessionScope.userId}">
								<td align="center"><fmt:message key="ADD" bundle="${lang}" /></td>
							</c:if>
						</tr>
					</thead>
					<c:forEach items="${popularSongsList}" var="popularSong"
						varStatus="status">
						<tr align="center">
							<td align="left">${status.index+1}</td>

							<td align="left" width="65"><audio controls="">
									<source src="<lp:fileTag fileName="${popularSong.nameFile }"/>"
										type="audio/mpeg" />
								</audio></td>
							<td align="center"><a
								href='<c:url value = "/showAlbum?idAlbum=${popularSong.idAlbum}"/>'>
									${popularSong.nameSong} </a></td>

							<td align="center">${popularSong.countBigInteger}</td>
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
					<fmt:message key="MOST_POPULAR_ALBUMS" bundle="${lang}" />
				</h1>
				<br>
				<jsp:include page="popularAlbumsCarousel.jsp"></jsp:include>
			</c:otherwise>
		</c:choose>
	</div>

	<div class="modal fade" id="congratulationModal">
		<div class="modal-dialog">
			<div class="modal-content"></div>
		</div>
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">
						<fmt:message key="CONGRATULATION" bundle="${lang}" />
					</h4>
				</div>

				<div class="modal-body">
					<fmt:message key="CONGRATULATION_MESSAGE" bundle="${lang}" />
					<div class="modal-footer">
						<p>
							<button type="button" class="btn btn-primary"
								data-dismiss="modal">
								<fmt:message key="ОК" bundle="${lang}" />
							</button>
						</p>
					</div>
				</div>
			</div>

		</div>
	</div>

	<div class="modal fade" id="sendPasswordModal">
		<div class="modal-dialog">
			<div class="modal-content"></div>
		</div>
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">
						<fmt:message key="MESSAGE" bundle="${lang}" />
					</h4>
				</div>

				<div class="modal-body">
					<fmt:message key="YOUR_PASWORD_WAS_SEND_TO_EMAIL" bundle="${lang}" />
					<div class="modal-footer">
						<p>
							<button type="button" class="btn btn-primary"
								data-dismiss="modal">
								<fmt:message key="ОК" bundle="${lang}" />
							</button>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="changePasswordModal">
		<div class="modal-dialog">
			<div class="modal-content"></div>
		</div>
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">
						<fmt:message key="MESSAGE" bundle="${lang}" />
					</h4>
				</div>

				<div class="modal-body">
					<fmt:message key="YOUR_PASWORD_WAS_CHANGED" bundle="${lang}" />
					<div class="modal-footer">
						<p>
							<button type="button" class="btn btn-primary"
								data-dismiss="modal">
								<fmt:message key="ОК" bundle="${lang}" />
							</button>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>



	<c:if test="${message != null || show != null}">
		<script type="text/javascript">
			$(document).ready(function() {
				$('#loginModal').modal('show');
			});
		</script>
	</c:if>


	<c:if test="${not empty sendPassword}">
		<script type="text/javascript">
			$(document).ready(function() {
				$('#sendPasswordModal').modal('show');
			});
		</script>
	</c:if>

	<c:if test="${not empty changePassword}">
		<script type="text/javascript">
			$(document).ready(function() {
				$('#changePasswordModal').modal('show');
			});
		</script>
	</c:if>

	<c:if test="${not empty congratulation}">
		<script type="text/javascript">
			$(document).ready(function() {
				$('#congratulationModal').modal('show');
			});
		</script>
	</c:if>
</div>

<script type="text/javascript">
	$('.carousel').carousel({
		interval : 5000
	});
</script>