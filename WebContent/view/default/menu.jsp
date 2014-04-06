<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.sessionLocale}" scope="session" />
<c:if test="${sessionScope.sessionLocale==null}">
	<fmt:setLocale value="${pageContext.request.locale}" scope="session" />
</c:if>
<fmt:setBundle basename="locale.messages" var="lang" scope="session" />

<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-ex1-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="<c:url value="home"/>">Let's Play</a>
		</div>
		<div class="collapse navbar-collapse navbar-ex1-collapse">
			<ul class="nav navbar-nav navbar-left">
				<li><a href="<c:url value="about"/>"><fmt:message
							key="ABOUT" bundle="${lang}" /></a></li>
			</ul>
			<ul class="nav navbar-nav navbar-left">
				<li>
					<div class="navbar-form navbar-left">
						<div class="dropdown">
							<input type="text" class="form-control" id="searchinput" data-toggle="dropdown"
								url="/LetsPlay/search" placeholder="<fmt:message
										key="SEARCH" bundle="${lang}" />" isadmin="true"/> 
								<i class="glyphicon glyphicon-search" style="color: white"></i>
						</div>
						<ul id="browser"  class="dropdown-menu" style="display: none;">
							<jsp:include page="result.jsp" flush="true" />
						</ul>
					</div>
				</li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<c:choose>
					<c:when test="${userId != null}">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown"><i class="fa fa-user"></i> <c:out
									value="${userLogin}"></c:out> <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="userhome"><i class="fa fa-home"></i> <fmt:message
											key="USER_HOME" bundle="${lang}" /></a></li>

								<li><a href="profile"><i class="fa fa-user"></i> <fmt:message
											key="USER_PROFILE" bundle="${lang}" /></a></li>

								<li><a href="settings"><i class="fa fa-gear"></i> <fmt:message
											key="USER_SETTINGS" bundle="${lang}" /></a></li>
								<li class="divider"></li>
								<li><a href="<c:url value="singout"/>"><i
										class="fa fa-power-off"></i> <fmt:message key="SIGN_OUT"
											bundle="${lang}" /></a></li>
							</ul>
					</c:when>
					<c:when test="${userId == null}">
						<li><a href="#loginModal" data-toggle="modal"
							data-target="#loginModal"><fmt:message key="LOG_IN"
									bundle="${lang}" /></a></li>
						<li><a href="<c:url value="registration"/>"><fmt:message
									key="REGISTRATION" bundle="${lang}" /></a></li>
					</c:when>
				</c:choose>

				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"><img
						src='<c:url value ="${currLanguage.path}"/>'
						data-toggle="dropdown"> <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<c:forEach items="${languages}" var="language">
							<li><a href="locale?language=${language.locale}"><img
									src='<c:url value ="${language.path}"/>'> <fmt:message
										key="${language.name}" bundle="${lang}" /></a></li>
						</c:forEach>

					</ul></li>
			</ul>
		</div>
	</div>

</nav>
