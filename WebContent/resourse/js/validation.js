$(document).ready(
		function() {
			jQuery.validator.setDefaults({
				errorPlacement : function(error, element) {
					// if the input has a prepend or append element, put
					// the
					// validation msg after the parent div
					if (element.parent().hasClass('input-prepend')
							|| element.parent().hasClass('input-append')) {
						error.insertAfter(element.parent());
						// else just place the validation message
						// immediatly
						// after the input
					} else {
						error.insertAfter(element);
					}
				},
				errorElement : "small", // contain the error msg in a
				// small tag
				wrapper : "div", // wrap the error message and small
				// tag in a
				// div
				highlight : function(element) {
					$(element).closest('.control-group').addClass('error'); // add
					// the
					// Bootstrap
					// error
					// class
					// to
					// the
					// control
					// group
				},
				success : function(element) {
					$(element).closest('.control-group').removeClass('error');// remove
					// the
					// Boostrap
					// error
					// class
					// from
					// the
					// control
					// group
				}
			});

			$('#artistForm').validate({
				rules : {
					artistName : {
						minlength : 2,
						required : true
					},
					title : {
						minlength : 6,
						required : true
					},
					about : {
						minlength : 6,
						required : true
					}
				}
			});

			$('#addAlbumForm').validate({
				rules : {
					artistName : {
						minlength : 6,
						required : true
					},
					albumName : {
						minlength : 2,
						required : true
					},
					albumTitle : {
						minlength : 6,
						required : true
					},
					about : {
						minlength : 6,
						required : true
					}
				}
			});

			$('#regform').validate({
				rules : {
					login : {
						minlength : 6,
						required : true
					}, 
					password : {
						minlength : 6,
						required : true
					},
					repeatPassword : {
						minlength : 6,
						required : true
					},
					email : {
						required : true,
						email : true
					}
				}
			});

			$('#settingform').validate({
				rules : {
					firstName : {
						minlength : 3,
					},
					surname : {
						minlength : 3,
					},
					country : {
						minlength : 2,
					},
					city : {
						minlength : 2,
					},
					about : {
						minlength : 6,
					}
				}
			});

			$('#settingform2').validate({
				rules : {
					password : {
						minlength : 6,
						required : true
					},
					confirmPassword : {
						minlength : 6,
						required : true
					}
				}
			});

		});
