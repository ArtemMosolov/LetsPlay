<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="resourse/css/bootstrap.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="menu.jsp"></jsp:include>

	<c:if test="${message != null}">
		<fmt:message key="${message}" bundle="${lang}" />
	</c:if>
</body>
</html>