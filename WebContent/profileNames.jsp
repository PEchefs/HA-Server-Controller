<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true"%>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/CSS/jquery-ui.css">
	<title>Profiles</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
     <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/JqueryAjaxRequester.js"></script>
	   
	<script>
   		var contextPath='<%=request.getContextPath()%>';
    </script>
   
   	<script type="text/javascript" src="date_time.js"></script>
   
   <script type="text/javascript">
	$(function() {
		$("#popUpDialogDiv").dialog({
			autoOpen : false,
			modal : true,
			width : 400,
		});

		//$('#registerId').on('click', function() {
		//	$("#popUpDialogDiv").dialog('open');
		//});
		//$('#dialogBoxId').on('click','#registerButton',HelloModule.validateForm);
		//$('#dialogBoxId').dialog({
		//	beforeClose: HelloModule.beforeDialogClose
		//});
		$( "#popUpDialogDiv" ).dialog( "option", "buttons", [ { text: "Register", click: HelloModule.validateForm }  ] );
		$('#dialogBoxId span').css('color','red');
	});
</script>

</head>
<body>

	<span id="date_time"></span>
            <script type="text/javascript" >window.onload = date_time('date_time');</script>
            <br/> <br/>
            
    <input type="button" id="addProfile"  value="Add Profile" onclick= "doAsyncRequest()" >    
   
	<%
	String profileNames = (String)session.getAttribute("profile_name"); 
	String[] profs = profileNames.split(";");
	System.out.println("the profile length isssssssssss: " + profileNames.length());
	if(profileNames.length() > 0){
 		for(String profile : profs){
 			System.out.println("inside foorrrrrrrrrrrrrrrrrrrr" );
			out.println("<div>");
			out.println("<label >" + profile + "</label><br /> ");
	 		out.println("<input type='button' id='" + profile  + "' name = \"profileName\" value='Edit'  onclick=saveAction(this) />");
			out.println("</div> <br/>");
 		}
	}
	%>

<form action="home.jsp" >
<br/>
<br/>
<br/>
<button type="submit" value="Back" > Back!</button>
</form>

<div id = "popUpDialogDiv">
<!-- <label> Profile Name</label> <input type="text" name="firstname" title="Profile name" style="color:#888;" 
    value="Enter Profile name" onfocus="inputFocus(this)" onblur="inputBlur(this)" />
    <br/>
    <br/> -->
<!-- <div id = "popUpDialogDiv"> -->

</div>

</div>
</body>

</html>