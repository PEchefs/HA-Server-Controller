<%-- <%@ page session="true"%>

User '<%=request.getRemoteUser()%>' has been logged out. --%>

<% session.invalidate(); %>

<meta http-equiv="refresh" content="0; URL=ServerController">
<!-- 
<br/><br/>
<a href="ServerController">Sign In</a> -->