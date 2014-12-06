<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form id="action" action="ActionRenderer" method="post">
   
  <%
  String str = (String) session.getAttribute("actionren");
	out.println("<div>");
	out.println("<label >" + str + "</label><br /> ");
	 out.println("</div> <br/>");
%>
</form>

</body>
</html>