
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@page import="java.util.List"%>
 <%@ page session="true"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="myStyle.css" media="screen" />
    <title>Switches</title>
    
     <script type="text/javascript"
src="<%=request.getContextPath()%>/js/jquery.js"></script>

   
   <script type="text/javascript"
	src="<%=request.getContextPath()%>/switchDetailHelper.js"></script>

	   
	<script>
   var contextPath='<%=request.getContextPath()%>';
   </script>
   
   
   <script type="text/javascript" src="date_time.js"></script>
   
</head>
<body>

	<div>
		<span id="date_time"></span>
        <script type="text/javascript" >window.onload = date_time('date_time');</script>
            
            <br/>
            <br/>
     </div>
     
     <div>
    	<input type="button" id="logout"  onclick="location.href = 'logout.jsp';" value = "Log out"/>
    </div>
       
            <br/>
            <br/>      	
   
  <%
List swts = (List)session.getAttribute("swts"); 
 /* out.println("<h2 style="color:white;background:blue;">Rooms</h2>"); */
for (Object str: swts)
{
	String[] swit = ((String)str).split(";");
	out.println("<div>");
	out.println("<label >" + swit[0] + "</label><br /> ");
	 out.println("<input type='button' id='" + swit[1]+";"+swit[2]+";"+swit[3]  + "' name = \"swit\" value=\"" +swit[3] +"\" onclick=saveAction(this) />");
	 out.println("</div> <br/>");
}
%>

<form action="home.jsp" >
<br/>
<br/>
<br/>
<button type="submit" value="Back" > Back!</button>

</form>
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