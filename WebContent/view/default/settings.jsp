<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="lp"%>

<!DOCTYPE html>

<div class="container">
	<div class="col-lg-12">
		<h1 class="page-header">
			<fmt:message key="SETTINGS" bundle="${lang}" />
		</h1>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="#personal" data-toggle="tab"><fmt:message
					key="PERSONAL" bundle="${lang}" /></a></li>
		<li><a href="#security" data-toggle="tab"><fmt:message
					key="SECURITY" bundle="${lang}" /></a></li>
		<li><a href="#avatar" data-toggle="tab"><fmt:message
					key="AVATAR" bundle="${lang}" /></a></li>
	</ul>


	<div class="tab-content">
		<div class="tab-pane active" id="personal">
			<br>
			<form class="form-horizontal" role="form" action="settings"
				method="post" id="settingform">

				<div class="form-group">
					<label for="FirstName" class="col-sm-2 control-label"> <fmt:message
							key="FIRST_NAME_ENTER" bundle="${lang}" />
					</label>
					<div class="col-sm-4">
						<input name="firstName" type="text" class="form-control"
							id="FirstName"
							placeholder="<fmt:message key="FIRST_NAME" bundle="${lang}" />"
							value="${userInfo.firstName}">
					</div>
				</div>
				<div class="form-group">
					<label for="Surname" class="col-sm-2 control-label"> <fmt:message
							key="SURNAME_ENTER" bundle="${lang}" />
					</label>
					<div class="col-sm-4">
						<input name="lastName" type="text" class="form-control"
							id="Surname"
							placeholder="<fmt:message key="SURNAME" bundle="${lang}" />"
							value="${userInfo.lastName}">
					</div>
				</div>


				<div class="form-group">
					<label for="Gender" class="col-sm-2 control-label"> <fmt:message
							key="GENDER" bundle="${lang}" />
					</label>


					<div class="col-lg-1">
						<div class="input-group">
							<span class="input-group-addon"> <input id="Male"
								type="radio" name="gender" value="male"
								<c:if test="${userInfo.gender eq 'male'}">checked</c:if>>
							</span> <label for="Male" class="col-sm-1 control-label"> <fmt:message
									key="MALE" bundle="${lang}" />
							</label> <span class="input-group-addon"> <input id="Female"
								type="radio" name="gender" value="female"
								<c:if test="${userInfo.gender eq 'female'}">checked</c:if>>
							</span> <label for="Female" class="col-sm-1 control-label"> <fmt:message
									key="FEMALE" bundle="${lang}" />
							</label> <span class="input-group-addon"> <input id="Geek"
								type="radio" name="gender" value="geek"
								<c:if test="${userInfo.gender eq 'geek'}">checked</c:if>>
							</span> <label for="Geek" class="col-sm-1 control-label"> <fmt:message
									key="GEEK" bundle="${lang}" />
							</label>

						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="Date" class="col-sm-2 control-label"> <fmt:message
							key="DATE_CHOOSE" bundle="${lang}" /></label>
					<div class="col-sm-4">
						<input type="text" class="datepicker form-control" name="date"
							id="Date"
							value=<fmt:formatDate value="${userInfo.dateOfBirth}" pattern="MM/dd/yyyy" />>
						<p class="text-danger">
							<c:if test="${birthDateError != null}">
								<fmt:message key="${birthDateError}" bundle="${lang}" />
							</c:if>
						</p>
					</div>
				</div>

				<div class="form-group">
					<label for="Country" class="col-sm-2 control-label"> <fmt:message
							key="COUTRY_ENTER" bundle="${lang}" />
					</label>
					<div class="col-sm-4">
						<input name="country" type="text" class="form-control"
							id="Country"
							placeholder="<fmt:message key="COUNTRY" bundle="${lang}" />"
							value="${userInfo.country}">
					</div>
				</div>
				<div class="form-group">
					<label for="City" class="col-sm-2 control-label"> <fmt:message
							key="CITY_ENTER" bundle="${lang}" />
					</label>
					<div class="col-sm-4">
						<input name="city" type="text" class="form-control" id="City"
							placeholder="<fmt:message key="CITY" bundle="${lang}" />"
							value="${userInfo.city}">
					</div>
				</div>

				<div class="form-group">
					<label for="About" class="col-sm-2 control-label"> <fmt:message
							key="ABOUT_ENTER" bundle="${lang}" />
					</label>
					<div class="col-sm-3">
						<textarea id="About"
							placeholder="<fmt:message key="ABOUT" bundle="${lang}" />"
							name="userAbout" class="form-control" rows="3"><c:out
								value="${userInfo.description}" /></textarea>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-5 col-sm-10">
						<button type="submit" class="btn btn-primary">
							<fmt:message key="ACCEPT" bundle="${lang}" />
						</button>
					</div>
				</div>
			</form>
		</div>


		<div class="tab-pane" id="security">
			<br>
			<form class="form-horizontal" role="form" action="security"
				method="post" id="settingform2">

				<div class="form-group">

					<label for="Password" class="col-sm-2 control-label"> <fmt:message
							key="NEW_PASSWORD" bundle="${lang}" />
					</label>
					<div class="controls">
						<div class="input-append">
							<div class="col-sm-4">
								<input id="Password" type="password" class="form-control"
									name="password"
									placeholder="<fmt:message key="PASSWORD" bundle="${lang}"/>" />

								<p class="text-danger">
									<c:if test="${passwordWrong != null}">
										<fmt:message key="${passwordWrong}" bundle="${lang}" />
									</c:if>
								</p>

							</div>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label for="ConfirmPassword" class="col-sm-2 control-label">
						<fmt:message key="CONFIRM_NEW_PASSWORD" bundle="${lang}" />
					</label>
					<div class="controls">
						<div class="input-append">
							<div class="col-sm-4">
								<input id="ConfirmPassword" type="password" class="form-control"
									name="confirmPassword"
									placeholder="<fmt:message key="PASSWORD" bundle="${lang}"/>" />
								<p class="text-danger">
									<c:if test="${passwordEqual != null}">
										<fmt:message key="${passwordEqual}" bundle="${lang}" />
									</c:if>
								</p>
							</div>

						</div>
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<div class="checkbox">
							<label> <input type="checkbox" id="ShowPassword"
								onchange="document.getElementById('Password').type=
									this.checked? 'text' : 'password'; 
									document.getElementById('ConfirmPassword').type=
									this.checked? 'text' : 'password'";  >
								<fmt:message key="SHOW_PASSWORD" bundle="${lang}" />
							</label>
						</div>
					</div>
				</div>


				<div class="form-group">
					<div class="col-sm-offset-5 col-sm-10" align="left">
						<button type="submit" class="btn btn-primary">
							<fmt:message key="ACCEPT" bundle="${lang}" />
						</button>
					</div>
				</div>
			</form>
		</div>
		<div class="tab-pane" id="avatar">
			<br>
			<form enctype="multipart/form-data" class="form-horizontal"
				role="form" action="<c:url value="/avatar"/>" method="post"
				id="settingform">


				<div class="fileinput fileinput-new" data-provides="fileinput">
					<div class="fileinput-new thumbnail"
						style="width: 200px; height: 300px;">
						<c:if test="${userInfo.image.fileName != null}">
							<img src='<lp:fileTag fileName="${userInfo.image.fileName} "/>' />
						</c:if>
					</div>
					<div class="fileinput-preview fileinput-exists thumbnail"
						style="max-width: 200px; max-height: 300px;"></div>
					<div>

						<span class="btn btn-default btn-file"><span
							class="fileinput-new">Select image</span><span
							class="fileinput-exists">Change</span><input type="file"
							name="avatar"></span> <a href="#"
							class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-5 col-sm-10">
						<button type="submit" class="btn btn-primary">
							<fmt:message key="ACCEPT" bundle="${lang}" />
						</button>
					</div>
				</div>
			</form>


		</div>
	</div>

	<c:if test="${passwordEqual != null || passwordWrong != null}">
		<script>
			$(document).ready(function() {
				activaTab('security');
			});

			function activaTab(tab) {
				$('.nav-tabs a[href="#' + tab + '"]').tab('show');
			};
		</script>
	</c:if>
</div>
</body>


<script>
	$(document).ready(function() {
		$('#myTab a:last').tab('show');
	});
</script>
<script type="text/javascript">
	$('.datepicker').datepicker();
</script>
<script type="text/javascript">
	$('.fileinput').fileinput();
</script>

