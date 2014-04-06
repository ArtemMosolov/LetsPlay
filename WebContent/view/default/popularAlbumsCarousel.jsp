<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="lp"%>

<!DOCTYPE html>

<div class='row'>
	<div class='col-md-12'>
		<div class="carousel slide media-carousel" id="media">
			<div class="carousel-inner">

				<div class="item active">
					<div class="row">
						<c:forEach items="${popularAlbumsList}" var="album" begin="0"
							end="2" varStatus="loop">
							<div class="col-md-4">
								<a class="thumbnail"
									href='<c:url value = "/showAlbum?idAlbum=${album.idAlbum}"/>'>
									<c:if test="${album.albumDescription.image.fileName  ne null }">
										<img id="picture"
											src='<lp:fileTag fileName="${album.albumDescription.image.fileName}"/>'
											alt="Empty picture!">
									</c:if> <c:if test="${album.albumDescription.image.fileName eq null }">
										<img id="picture"
											src="<c:url value= "/resourse/image/800x500.gif" />">
									</c:if>
								</a>
								<div class="caption">
									<a
										href='<c:url value = "/showAlbum?idAlbum=${album.idAlbum}"/>'>
										<h3 align="center">${album.nameAlbum}</h3>
									</a>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>


				<div class="item">
					<div class="row">
						<c:forEach items="${popularAlbumsList}" var="album" begin="3"
							end="5">
							<div class="col-md-4">
								<a class="thumbnail"
									href='<c:url value = "/showAlbum?idAlbum=${album.idAlbum}"/>'>
									<c:if test="${album.albumDescription.image.fileName  ne null }">
										<img width="350" height="250"
											src='<lp:fileTag fileName="${album.albumDescription.image.fileName}"/>'
											alt="Empty picture!">
									</c:if> <c:if test="${album.albumDescription.image.fileName eq null }">
										<img id="picture"
											src="<c:url value= "/resourse/image/800x500.gif" />">
									</c:if>
								</a>
								<div class="caption">
									<a
										href='<c:url value = "/showAlbum?idAlbum=${album.idAlbum}"/>'>
										<h3 align="center">${album.nameAlbum}</h3>
									</a>
								</div>

							</div>
						</c:forEach>
					</div>
				</div>

				<div class="item">
					<div class="row">
						<c:forEach items="${popularAlbumsList}" var="album" begin="6"
							end="9">
							<div class="col-md-4">
								<a class="thumbnail"
									href='<c:url value = "/showAlbum?idAlbum=${album.idAlbum}"/>'>
									<c:if test="${album.albumDescription.image.fileName  ne null }">
										<img id="picture"
											src='<lp:fileTag fileName="${album.albumDescription.image.fileName}"/>'
											alt="Empty picture!">
									</c:if> <c:if test="${album.albumDescription.image.fileName eq null }">
										<img id="picture"
											src="<c:url value= "/resourse/image/800x500.gif" />">
									</c:if>
								</a>
								<div class="caption">
									<a
										href='<c:url value = "/showAlbum?idAlbum=${album.idAlbum}"/>'>
										<h3 align="center">${album.nameAlbum}</h3>
									</a>
								</div>

							</div>
						</c:forEach>
					</div>
				</div>
				<a data-slide="prev" href="#media" class="left carousel-control"></a>
				<a data-slide="next" href="#media" class="right carousel-control"></a>
			</div>
		</div>
	</div>
</div>
<br>