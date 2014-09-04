
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@page import="java.util.List"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="myStyle.css" media="screen" />
    <title>Rooms</title>
    <script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
        
  <script type="text/javascript" src="details.js"></script>
  <script type="text/javascript" src="date_time.js"></script>
</head>
<body>


<span id="date_time"></span>
            <script type="text/javascript" >window.onload = date_time('date_time');</script>
            
            <br/>

<!-- <script>

  var ear = document.getElementsByTagName('div');
  
  ear[0].addEventListener("click", listener, false);

  function listener() {
   alert("Yay");
  }

</script> -->



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
<button type="submit" id="button2" name = "button2" > Profiles...  "</button>
</form>

<br/>
<br/>

<a href="logout.jsp">Click here to log out</a>

</body>


<%-- <html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="myStyle.css" media="screen" />
<title>Home Page</title>
</head>
<body>
<div class="container">
   
    <c:forEach var="p" items="${roomNames}" varStatus="rIndex">
    <div class="first">p</div>
    </c:forEach>
       
</div>

<%
List rooms = (List)session.getAttribute("roomNames"); %>
<h3>Rooms</h3>
<% for (Object room: rooms)
{

    out.println(room.toString());

}
%>

</body> --%>
</html>