 function doAsyncRequest() {
	
	alert("async called for profiles");

	var article = new Object();
    article.add ="Testtttt";
    
		$.ajax({
			url : contextPath.concat("/AddProfilesDataProvider"),
			cache : false,
			type : "POST",
			dataType : "json",
			data : JSON.stringify(article),
			 contentType: 'application/json',
		       mimeType: 'application/json',
			success : function (data) { 
//			showModalDialogPopUp(data){
				 alert("profile add success");
//			}
			},
			error:function(data,status,er) {
		           alert("error: "+data+" status: "+status+" er:"+er);
		       }
		});
   }
   
//   function showModalDialogPopUp(data) {
//	   alert("profile add success");
//   }
   
