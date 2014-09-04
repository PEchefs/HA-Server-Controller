<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Profiles</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/JqueryAjaxRequester.js"></script>
	   
	<script>
   		var contextPath='<%=request.getContextPath()%>';
    </script>
   
   	<script type="text/javascript" src="date_time.js"></script>
   
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

</body>

</html>