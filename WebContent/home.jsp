<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@page import="java.util.List"%>
 <%@ page session="true"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="myStyle.css" media="screen" />
    <title>Rooms</title>
    <script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
        
  <script type="text/javascript" src="details.js"></script>
  <script type="text/javascript" src="date_time.js"></script>
</head>
<body>

	<div>
		<span id="date_time"></span>
		<script type="text/javascript" >window.onload = date_time('date_time');</script>
        <br/>
    </div>
    
    <div>
    <input type="button" id="logout"  onclick="location.href = 'logout.jsp';" value = "Log out"/>
    </div>

<form id="testForm" action="SwitchDetails" method="post">

<div> </div>
<br/>
<br/>
   
  <%
String str = (String)session.getAttribute("rooms"); 
String[] names = str.split(";");
%>
<!-- out.println("<h2 style="color:white;background:blue;">Rooms</h2>"); -->
<% for (String room: names)
{

	 out.println("<button type=\"submit\" id=\"button1\" name = \"button1\" value= \"" +room.toString() +  "\">" + room.toString() + "</button>");

}
%>


</form>

<div> </div>
<br/>
<br/>

<form action="ProfilesRenderer" method="post">
<button type="submit" id="button2" name = "button2" > Profiles...</button>
</form>

</body>


</html>