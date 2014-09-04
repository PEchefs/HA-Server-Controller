function sendAjax() {
 
	alert("Inside sending ajax");
    // get inputs
//    var article = new Object();
//    article.title = $('#title').val();
//    article.url = $('#url').val();
//    article.categories = $('#categories').val().split(";");
//    article.tags = $('#tags').val().split(";");
	var $containr = $('.containr');
	var $password = $('#password');
	$('#login').hide();
    $.ajax({
        url: "ServerController",
        type: 'POST',
        dataType: 'text',
        data: $password,
//        contentType: 'application/json',
//        mimeType: 'application/json',
 
        success: function (response) {
        	
        	alert("inside success : " + $password);
//            $("tr:has(td)").remove();
        	alert(response);
            $.each(response.split(";"), function (index, roomName) {
            	var test = "<div id =\"" + roomName + "\"" + ">" + roomName + "</div>";
            	alert(test);
            	$containr.append(test);
 
            }); 
        },
        error:function(data,status,er) {
            alert("error: "+data+" status: "+status+" er:"+er);
        }
    });
}