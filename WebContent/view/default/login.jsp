<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<div class="modal fade" id="loginModal">
		<div class="modal-dialog">
			<div class="modal-content"></div>
		</div>
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">
						<fmt:message key="LOG_IN" bundle="${lang}" />
					</h4>
				</div>

				<div class="modal-body">
					<c:if test="${message != null}">
						<p class="text-danger">
							<fmt:message key="${message}" bundle="${lang}" />
						</p>
					</c:if>
					<form method="post" action="login" name="loginForm" id="form">
						<div class="form-group">
							<label for="exampleInputEmail1"><fmt:message
									key="ENTER_LOGIN" bundle="${lang}" /></label> <input name="login"
								class="form-control" id="exampleInputEmail1"
								placeholder="<fmt:message
											key="ENTER_LOGIN" bundle="${lang}"/>"
								type="text">
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1"><fmt:message
									key="PASSWORD" bundle="${lang}" /></label> <input name="password"
								class="form-control" id="exampleInputPassword1"
								placeholder="<fmt:message
											key="PASSWORD" bundle="${lang}"/>"
								type="password">
						</div>
						<p class="text-right">
							<a href="sendrestorepassword"><fmt:message key="FORGOT_PASSWORD"
									bundle="${lang}" /></a>
						</p>
						<div class="modal-footer">

							<p>
								<button type="submit" class="btn btn-primary">
									<fmt:message key="LOG_IN" bundle="${lang}" />
								</button>
							</p>
						</div>
					</form>
				</div>
			</div>

		</div>
	</div>

	<script type="text/javascript">
		$(document).ready(function() {
			$('#form').validate({
				rules : {
					password : {
						minlength : 6,
						required : true
					},
					login : {
						minlength : 6,
						required : true,
					}
				}
			});
		});
	</script>
</body>
</html>