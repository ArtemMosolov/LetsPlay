jQuery(function($) {
	var url = $('#fileuploader_many').attr('urlsite');
	var temp = $('#fileuploader_many').parent().attr('enctype');
	var uploader = new qq.FileUploader({
		element : $('#fileuploader_many')[0],
		allowedExtensions : [ 'mp3' ],
		maxConnections : 1,
		button_text : "Добавить музыку",
		multiple:true,
		forceMultipart:true,
		action : url,
		onComplete: function(id, fileName, responseJSON){
		}
	});
	
});