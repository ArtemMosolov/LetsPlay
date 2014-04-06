<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin Login</title>

<link href="resourse/css/bootstrap.css" rel="stylesheet">
<script src="resourse/js/jquery-1.10.2.js"></script>
<script src="resourse/js/bootstrap.js"></script>
<script src="resourse/js/validation.js"></script>
<script
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.js"></script>
</head>
<body>


	<div class="col-lg-4 center">
		<div class="well">
			<form class="form-horizontal" role="form" action="admin"
				method="post" id="adminloginform">
				<div class="form-group">
					<div class="col-sm-12">
						<h2 class="form-heading">Please sign in</h2>
					</div>
				</div>

				<c:if test="${message != null}">
					<p class="text-danger">
						<fmt:message key="${message}" bundle="${lang}" />
					</p>
				</c:if>

				<div class="form-group">
					<div class="col-sm-12">
						<input name="login" type="text" class="form-control" id="login"
							placeholder="Login">
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-12">
						<input name="password" type="password" class="form-control"
							id="password" placeholder="Password">
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-offset-5">
						<button type="submit" class="btn btn-primary">Enter</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<script type="text/javascript">
		$(document).ready(function() {
			$('#adminloginform').validate({
				rules : {
					login : {
						minlength : 6,
						required : true
					},
					password : {
						minlength : 6,
						required : true
					}

				}
			});
		});
	</script>



</body>
</html>