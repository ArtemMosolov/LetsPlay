<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<div class="container">
		<div class="col-lg-12">
			<h1 class="page-header">
				<fmt:message key="JOIN" bundle="${lang}" />
				Let's Play!
			</h1>
			<ol class="breadcrumb">
				<li><a href="<c:url value= "/home" />"><i
						class="fa fa-dashboard"></i> <fmt:message key="HOME"
							bundle="${lang}" /></a></li>
				<li class="active"><i class="fa fa-edit"></i> <fmt:message
						key="REGISTRATION" bundle="${lang}" /></li>
			</ol>
		</div>
		<form class="form-horizontal" role="form" action="registration"
			method="post" id="regform">
			<div class="form-group">
				<label for="UserName" class="col-sm-2 control-label"> <fmt:message
						key="USER_NAME" bundle="${lang}" />
				</label>
				<div class="col-sm-5">
					<input name="login" type="text" class="form-control" id="UserName"
						placeholder="<fmt:message key="USER_NAME" bundle="${lang}" />"
						value="${login}">
					<c:if test="${not empty errors}">
						<p class="text-danger">
							<c:if test="${errors.containsKey('name')}">
								<fmt:message key="${errors['name']}" bundle="${lang}" />
							</c:if>

							<c:if test="${errors.containsKey('loginWrong')}">
								<fmt:message key="${errors['loginWrong']}" bundle="${lang}" />
							</c:if>
						</p>

					</c:if>
				</div>
			</div>
			<div class="form-group">
				<label for="Email" class="col-sm-2 control-label">E-mail</label>
				<div class="col-sm-5">
					<input name="email" type="email" class="form-control" id="Email"
						placeholder="E-mail" value="${email}" />
					<c:if test="${not empty errors}">
						<p class="text-danger">
							<c:if test="${errors.containsKey('email')}">
								<fmt:message key="${errors['email']}" bundle="${lang}" />
							</c:if>

							<c:if test="${errors.containsKey('emailWrong')}">
								<fmt:message key="${errors['emailWrong']}" bundle="${lang}" />
							</c:if>
						</p>
					</c:if>
				</div>
			</div>
			<div class="form-group">
				<label for="Password" class="col-sm-2 control-label"> <fmt:message
						key="PASSWORD" bundle="${lang}" />
				</label>
				<div class="col-sm-5">
					<input name="password" type="password" class="form-control"
						id="Password"
						placeholder="<fmt:message key="PASSWORD" bundle="${lang}"/>">
					<c:if test="${not empty errors}">
						<p class="text-danger">
							<c:if test="${errors.containsKey('password')}">
								<fmt:message key="${errors['password']}" bundle="${lang}" />
							</c:if>

							<c:if test="${errors.containsKey('passwordWrong')}">
								<fmt:message key="${errors['passwordWrong']}" bundle="${lang}" />
							</c:if>
						</p>
					</c:if>

				</div>
			</div>
			<div class="form-group">
				<label for="RepeatPassword" class="col-sm-2 control-label">
					<fmt:message key="REPEAT_PASSWORD" bundle="${lang}" />
				</label>
				<div class="col-sm-5">
					<input name="repeatPassword" type="password" class="form-control"
						id="RepeatPassword"
						placeholder="<fmt:message key="REPEAT_PASSWORD" bundle="${lang}"/>">
					<c:if test="${not empty errors}">
						<p class="text-danger">
							<c:if test="${errors.containsKey('repeatPassword')}">
								<fmt:message key="${errors['repeatPassword']}" bundle="${lang}" />
							</c:if>

							<c:if test="${errors.containsKey('passwordEqual')}">
								<fmt:message key="${errors['passwordEqual']}" bundle="${lang}" />
							</c:if>
							<c:if test="${errors.containsKey('exists')}">
								<fmt:message key="${errors['exists']}" bundle="${lang}" />
							</c:if>
						</p>
					</c:if>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-5 col-sm-10">
					<button type="submit" class="btn btn-primary">
						<fmt:message key="REGISTER" bundle="${lang}" />
					</button>
				</div>
			</div>
		</form>
	</div>


</body>
</html>