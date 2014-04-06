<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<body>
	<div class="container">
		<div class="col-lg-12">
			<h1 class="page-header">
				<fmt:message key="SEND_PASSWORD" bundle="${lang}" />
			</h1>
		</div>
		<div class="col-lg-3" >
			<form method="post" action="sendrestorepassword" name="restorelogin"
				id="form">
				<div class="form-group">
					<label for="exampleInputEmail1"><fmt:message
							key="ENTER_EMAIL" bundle="${lang}" /></label> <input name="email"
						class="form-control" id="Email" placeholder="<fmt:message
							key="ENTER_EMAIL" bundle="${lang}" />"
						type="email">
				</div>

				<p>
					<button type="submit" class="btn btn-primary">
						<fmt:message key="SEND" bundle="${lang}" />
					</button>
				</p>

			</form>
		</div>
	</div>

	<script type="text/javascript">
		$(document).ready(function() {
			$('#form').validate({
				rules : {
					email : {
						required : true,
						email : true
					}
				}
			});
		});
	</script>
</body>
</html>