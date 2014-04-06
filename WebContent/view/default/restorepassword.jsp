<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<div class="container">
	<div class="col-lg-12">
		<h1 class="page-header">
			<fmt:message key="RESTORE_PASSWORD" bundle="${lang}" />
		</h1>
	</div>

	<c:choose>
		<c:when test="${error != null}">
			<fmt:message key="${error}" bundle="${lang}" />
		</c:when>
		<c:when test="${error == null }">
			<form class="form-horizontal" role="form" action="restorepassword"
				method="post" id="restoreform">
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
							</p>
						</c:if>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-5 col-sm-10">
						<button type="submit" class="btn btn-primary">
							<fmt:message key="CHANGE" bundle="${lang}" />
						</button>
					</div>
				</div>
			</form>
		</c:when>
	</c:choose>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		$('#restoreform').validate({
			rules : {
				password : {
					minlength : 6,
					required : true
				},
				repeatPassword : {
					minlength : 6,
					required : true
				}
			}
		});
	});
</script>
