<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Let'sPlay | ${title}</title>

<link href='<c:url value= "/resourse/css/bootstrap.css"/>' rel="stylesheet">
<link href='<c:url value= "/resourse/css/datepicker.css"/>' rel="stylesheet">
<link href='<c:url value= "/resourse/css/jasny-bootstrap.css"/>' rel="stylesheet">
<link href='<c:url value= "/resourse/css/playlist.css"/>' rel="stylesheet">
<link href='<c:url value= "/resourse/css/yamm.css"/>' rel="stylesheet">

<script src='<c:url value= "/resourse/js/jquery-1.10.2.js"/>'></script>
<script src='<c:url value= "/resourse/js/bootstrap.js"/>'></script>
<script src='<c:url value= "/resourse/js/validation.js"/>'></script>
<script src='<c:url value= "/resourse/js/bootstrap-datepicker.js"/>'></script>
<script src='<c:url value= "/resourse/js/jasny-bootstrap.js"/>'></script>
<script src='<c:url value= "/resourse/js/datepicker.js"/>'></script>
<script src='<c:url value= "/resourse/js/jquery.validate.js"/>'></script>
<script src='<c:url value= "/resourse/js/search.js"/>'></script>

<c:choose>
	<c:when
		test="${(sessionScope.sessionLocale=='uk')||(sessionScope.sessionLocale=='uk_UA')}">
		<script src="resourse/js/messages_uk.js"></script>
	</c:when>
	<c:when
		test="${(sessionScope.sessionLocale=='ru')||(sessionScope.sessionLocale=='ru_RU')}">
		<script src="resourse/js/messages_ru.js"></script>
	</c:when>
</c:choose>

</head>
<body>
	<jsp:include page="menu.jsp" />
	<c:if test="${url != null}">
		<jsp:include page="${url}" flush="true" />
	</c:if>
	<jsp:include page="login.jsp" />
	<jsp:include page="modalAddPlaylist.jsp"></jsp:include>
</body>
</html>