<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>

<div class="modal fade" id="createPlaylist">
	<div class="modal-dialog">
		<div class="modal-content"></div>
	</div>
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">
					<fmt:message key="ADD_PLAYLIST" bundle="${lang}" />
				</h4>
			</div>

			<div class="modal-body">
				<form method="post" action="createplaylist" name="create" id="form">
					<div class="form-group">
						<label for="exampleInput"><fmt:message key="ENTER_NAME"
								bundle="${lang}" /></label> <input name="name" class="form-control"
							id="exampleInputName" value="${playlist.namePlayList}"
							placeholder="<fmt:message
											key="ENTER_NAME" bundle="${lang}"/>"
							type="text"> <input name="playlistId"
							class="form-control" id="exampleInputId"
							value="${playlist.idUserPlayList}" type="hidden">
					</div>
					<div class="modal-footer">
						<p>
							<button type="submit" class="btn btn-primary">
								<fmt:message key="CREATE_PLAYLIST" bundle="${lang}" />
							</button>
						</p>
					</div>
				</form>
			</div>
		</div>

	</div>
</div>


<c:if test="${edit != null}">
	<script type="text/javascript">
		$(document).ready(function() {
			$('#createPlaylist').modal('show');
		});
	</script>
</c:if>

<script type="text/javascript">
	$(document).ready(function() {
		$('#form').validate({
			rules : {
				name : {
					minlength : 2,
					required : true
				}
			}
		});
	});
</script>
