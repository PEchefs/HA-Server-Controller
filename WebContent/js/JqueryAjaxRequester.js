 function doAsyncRequest() {
	
//	alert("async called for profiles");

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
			success : function (data1, statusData, dataContainer) { 
				var dat = JSON.parse(dataContainer.responseText);
//				JSON.parse(dataContainer);
//			showModalDialogPopUp(data){
				$("#popUpDialogDiv").html('');
				var popUpTitle = getProfilePopUpTitle();
				$("#popUpDialogDiv").append(popUpTitle);
				$.each(dat.roomsCollection, function(i, val) {
//					 alert(val);
					var element = structureHtml(val);
					$("#popUpDialogDiv").append(element);
					$("#popUpDialogDiv").dialog('open');
//					 return false;   
//					$(document.body).append(element);
					});
				
				var saveCancelButtons = getSaveCancelButtons();
				$("#popUpDialogDiv").append(saveCancelButtons);
				 alert("profile add success");
//			}
			},
			error:function(data,status,er) {
		           alert("error: "+data1 +" status: "+statusData+" er:"+er);
		       }
		});
   }
   
 
 function getSaveCancelButtons(){
	 return '<input type="button" id="saveProfile" value = "Save"/>   <input type="button" onclick="profileNames.jsp" value = "Cancel"/>';
 }
 
 function getProfilePopUpTitle(){
	 var title = "<label> Profile Name</label><input type='text' name='firstname' title='Profile name' style='color:#888;' value='Enter Profile name' onfocus='inputFocus(this)' onblur='inputBlur(this)' /> <br/><br/>";
	 return title;
 }
   function structureHtml(data) {
	   var roomName = data.roomName;
	   var popup_html_txt = "<div class='";
	   popup_html_txt = popup_html_txt + data.roomName + "'>";
	   
	   var popup_html_txt = popup_html_txt + "<div> ";
	   popup_html_txt = popup_html_txt + "<label value ='"  +roomName + "'> "+ roomName;
	   popup_html_txt = popup_html_txt + "</label> </div>" + "<br/>";
	   
	   var nodess = data.nodes;
	   var idElement;
	   var nodeIdd;
	   for (m=0; m< nodess.length; m++) {
		   idElement = nodess[m].nodeId;
		   nodeIdd = nodess[m].nodeId;
		   var switchesObj = nodess[m].switches;
		   for (n=0; n< switchesObj.length; n++) {
			   idElement = idElement + ";"+ switchesObj[n].switchId;
			   var popup_html_txt = popup_html_txt + "<div>";
			   popup_html_txt = popup_html_txt + "<input type='checkbox' id='";
			   popup_html_txt = popup_html_txt + idElement + "'/>";
			   popup_html_txt = popup_html_txt + switchesObj[n].switchName + "</input>"/* + "&emsp"*/;
			   popup_html_txt = popup_html_txt + " <select> <option value='ON'>ON</option> <option value='OFF'>OFF</option> </select>";
			   
			   popup_html_txt = popup_html_txt + "</div> <br/>";
//			   switchesObj[n].switchName;
			   idElement = nodeIdd;
		   }
		   
	   }
	   
//	   +roomName + "'>" + "<br/>";
	   	
//	   <input type="checkbox" id="Bike">I have a bike<br>
//	   <input type="checkbox" name="vehicle" value="Car">I have a car 
	   
	   popup_html_txt = popup_html_txt + "</div>";
	   return popup_html_txt;
   }
   
   
   function inputFocus(i){
	   var defaultValue = "Enter Profile name";
	    if(i.value==defaultValue){ i.value=""; i.style.color="#000"; }
	}
	function inputBlur(i){
		 var defaultValue = "Enter Profile name";
	    if(i.value==""){ i.value=defaultValue; i.style.color="#888"; }
	}
