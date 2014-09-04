 function saveAction(button){
	  /*  alert("inside save action");
	   alert("/ActionRenderer"+ "?swit="+ $(button).attr('value')); */
	   $.ajax({
		   url : contextPath.concat("/ActionRenderer", "?swit=", $(button).attr('id')),
		   type : 'POST',
		   dataType : 'html',
		   success : function(){
			   successCallBack(button);
		   },
		   error : errorCallBack
		   });
   }
   
   function successCallBack(button){
	   alert("success");
	   var $button=$(button);
	   /* alert($(button).attr('value')); */
	   /* alert(button[value*='ON']); */
	   /* $( "button[value*='ON']" ).prop('value', "OFF"); */
	    var text = $button.attr('id');
	  /*  alert($("button:contains('ON')")); */
	  /*  alert(text); */
	   if(text.indexOf("ON") >= 0){
		   text = text.replace("ON", "OFF");
		   /* alert("inside if condition"); */
		  /* $("button").val(text);*/
		   $button.attr('id',text);
		   $button.prop('value', "OFF");
	   }else {
		   text = text.replace("OFF", "ON");
		  /*  alert("inside else condition"); */
		  /* $("button").val(text);*/
		   $button.attr('id',text);
		   $button.prop('value', "ON");
	   }
	 /*   $( "button[value*='OFF']" ).prop('value', "ON"); */
	   
	  /*  if(($(button).attr('value')).contains("ON"))
	     $(button).prop('value', "OFF"); */
   }
   
   function errorCallBack(){
	   alert("fail");
   }
   
   
   /*function addProfiles(){
	   doAsyncRequest("/ProfilesRenderer", "POST",
				"json", {'operation':'add'}, saveSettingsSuccessResponse,saveSettingsFailureResponse);
   }
   
   
   function doAsyncRequest(url, requestType, type, dataToBeSubmitted,
			successCallBack, errorCallBack) {
	
	alert("async called for profiles");
		$.ajax({
			url : url,
			cache : false,
			type : requestType,
			dataType : type,
			data : dataToBeSubmitted,
			success : successCallBack,
			error : errorCallBack
		});
	}*/
   
/*   function doAsyncRequest() {
	
	alert("async called for profiles");
	
		$.ajax({
			url : contextPath.concat("/AddProfilesDataProvider"),
			cache : false,
			type : "POST",
			dataType : "json",
			data : JSON.stringify({'operation':'add'}),
			success : showModalDialogPopUp,
			error : errorCallBack
		});
   }
   
   function showModalDialogPopUp() {
	   alert("profile add success");
   }
*/   
