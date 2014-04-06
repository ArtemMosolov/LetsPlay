/**
 * 
 */
//function disableEnterKey(e) {
//	var key;
//	if (window.event)
//		key = window.event.keyCode; //IE
//	else
//		key = e.which; //firefox
//	if (key == 13)
//		return false;
//	else
//		return true;
//}
//
//function searchFiles() {
//	var searchInput = $("#searchinput").val();
//	var url = $('#searchinput').attr('url');
//	if (searchInput.length == 0) {
//		$.ajax({
//			type : "GET",
//			url : url,
//			data : {
//			},
//			success : function(data) {
//				$("#browser").html(data);
//			},
//		});
//		loadBrowserContent();
//	} else {
//		$.ajax({
//			type : "POST",
//			url : url,
//			data : {
//				"searchInput" : searchInput,
//				"isadmin":isadmin
//			},
//			success : function(data) {
//				$("#browser").show();
//				$("#browser").html(data);
//			},
//		});
//	}
//}
$(function($) {
	$('#myTab a:last').tab('show');
	$('.fileinput').fileinput();
	$('#searchinput').keyup(function(){
		var searchInput = $(this).val();
		var url = $(this).attr('url');
		var isadmin = $(this).attr('isadmin');
		if (searchInput.length == 0) {
			if(!isadmin){
				$.ajax({
					type : "GET",
					url : url,
					data : {
					},
					success : function(data) {
						$("#browser").html(data);
					},
				});
				loadBrowserContent();
			}
		} else {
			$.ajax({
				type : "POST",
				url : url,
				data : {
					"isadmin":isadmin,
					"searchInput" : searchInput,
				},
				success : function(data) {
					if(isadmin){
						$("#browser").show(function() {
							$('body').click(function() {
								$("#browser").hide();
							});
						});
					}
					$("#browser").html(data);
				},
			});
		}
	});
	$('#searchinput').keydown(function(event){
		var key;
		if (window.event)
			key = window.event.keyCode; //IE
		else
			key = e.which; //firefox
		if (key == 13)
			return false;
		else
			return true;
	});
});