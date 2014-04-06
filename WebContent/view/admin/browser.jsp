<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="lp"%>

<!DOCTYPE html>
<html>
<body>

	<div id="search-result" align="center">
		<div class="row text-center">
			<c:forEach items="${searchList}" var="entry">
				<c:choose>
					<c:when
						test="${(entry.key eq 'Artist') and (not empty entry.value)}">
						<div class="row">
							<div class="col-lg-12">
								<h3><fmt:message key="Artist"
													bundle="${lang}" /></h3>
							</div>
						</div>
						<c:forEach items="${entry.value}" var="result">
							<div class="col-lg-3 col-md-6 hero-feature">
								<div class="thumbnail">
									<c:if
										test="${result.artistDescription.image.fileName ne null }">
										<img width="350" height="250"
											src='<lp:fileTag fileName="${result.artistDescription.image.fileName }"/>'>
									</c:if>
									<c:if
										test="${result.artistDescription.image.fileName eq null }">
										<img src="<c:url value= "/resourse/image/800x500.gif" />"
											width="350" height="250">
									</c:if>
									<div class="caption">
										<h3>${result.nameArtist}</h3>
										<p>${result.artistDescription.artistDescription}</p>
										<p>
											<a
												href="<c:url value= "/admin/addArtist?artistid=${result.idArtist}" />"
												class="btn btn-primary"><fmt:message key="UPDATE"
													bundle="${lang}" /></a> <a
												href="<c:url value= "/admin/delete?artistid=${result.idArtist}" />"
												class="btn btn-default"><fmt:message key="DELETE"
													bundle="${lang}" /></a>
										</p>
									</div>
								</div>
							</div>

						</c:forEach>
					</c:when>

					<c:when test="${(entry.key eq 'Song') and (not empty entry.value)}">
						<div class="row">
							<div class="col-lg-12">
								<h3><fmt:message key="Song"
													bundle="${lang}" /></h3>
							</div>
						</div>
						<c:forEach items="${entry.value}" var="result">
							<div class="col-lg-3 col-md-6 hero-feature">
								<div class="thumbnail">
									<c:if test="${result.fileName ne null }">
										<img src='<lp:fileTag fileName="${result.fileName}"/>'
											width="800px" height="500">
									</c:if>
									<c:if test="${result.fileName eq null }">
										<img src='<c:url value= "/resourse/image/800x500.gif" />'
											width="800px" height="500">
									</c:if>

									<div class="caption">
										<h3>${result.nameSong}</h3>
										<p>

											<a
												href="<c:url value= "/admin/delete?songid=${result.idSong}" /> "
												class="btn btn-default"><fmt:message key="DELETE"
													bundle="${lang}" /></a>
										</p>
									</div>
								</div>
							</div>

						</c:forEach>
					</c:when>

					<c:when
						test="${(entry.key eq 'Album') and (not empty entry.value)}">
						<div class="row">
							<div class="col-lg-12">
								<h3><fmt:message key="Album"
													bundle="${lang}" /></h3>
							</div>
						</div>
						<c:forEach items="${entry.value}" var="result">
							<div class="col-lg-3 col-md-6 hero-feature">
								<div class="thumbnail">
									<c:if test="${result.albumDescription.image.fileName ne null }">
										<img
											src='<lp:fileTag fileName="${result.albumDescription.image.fileName}"/>'
											width="800px" height="500">
									</c:if>
									<c:if test="${result.albumDescription.image.fileName eq null }">
										<img src="<c:url value= "/resourse/image/800x500.gif" />"
											width="800px" height="500">
									</c:if>
									<div class="caption">
										<h3>${result.nameAlbum}</h3>
										<p>${result.albumDescription.descriptionAlbum}</p>
										<p>
											<a
												href="<c:url value= "/admin/addAlbum?albumid=${result.idAlbum}" />"
												class="btn btn-primary"><fmt:message key="UPDATE"
													bundle="${lang}" /></a> <a
												href="<c:url value= "/admin/delete?albumid=${result.idAlbum}" /> "
												class="btn btn-default"><fmt:message key="DELETE"
													bundle="${lang}" /></a>
										</p>
									</div>
								</div>
							</div>

						</c:forEach>
					</c:when>
				</c:choose>
			</c:forEach>
		</div>
	</div>
</body>
</html>