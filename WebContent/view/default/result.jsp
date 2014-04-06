<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<c:if test="${searchResult != null && searchResult}">
	<div class="alert alert-info">
		<h5>
			<fmt:message key="NO_SEARCH_RESULT" bundle="${lang}" />
		</h5>
	</div>
</c:if>
<li><span>Artists</span> <c:forEach items="${searchList['Artist']}"
		var="item">
		<a href='<c:url value = "/showArtist?idArtist=${item.idArtist}"/>'>${item.nameArtist}</a>
	</c:forEach></li>
<li><span>Album</span> <c:forEach items="${searchList['Album']}"
		var="item">
		<a href='<c:url value = "/showAlbum?idAlbum=${item.idAlbum}"/>'>${item.nameAlbum}</a>
		<%-- 	<span>${item.nameArtists}</span> --%>
	</c:forEach></li>
<li><span>Song</span> <c:forEach items="${searchList['Song']}"
		var="item">
		<a href='<c:url value = "/showAlbum?idAlbum=${item.idAlbum}"/>'>${item.nameSong}</a>
		<%-- 	<span>${item.nameArtists}</span> --%>
	</c:forEach></li>
</html>