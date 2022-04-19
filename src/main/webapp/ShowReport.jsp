<%@page import="com.learnersacademy.entity.ClassObject"%>
<%@page import="java.util.Date" %>
<%@page import="java.util.List" %>
<%@page import="javax.servlet.http.HttpSession"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display Report</title>
</head>
<body>
<div style="text-align:centre">
<%
HttpSession mySession=request.getSession(false);
if(mySession==null){
	out.print("<center><h3>Your session is expired..!!</h3></center>");
	response.setHeader("refresh", "url='/OnlineSchoolManagementPortal'");
}
else{
	ClassObject cl=(ClassObject)mySession.getAttribute("class");
	@SuppressWarnings("unchecked")
	List<Object> reList=(List<Object>)mySession.getAttribute("reportList");
	if(reList !=null){
	Date regisTime = new Date(mySession.getCreationTime());
	%>
<h1 style="Color:blue">Learner's Academy</h1><b style="Color:blue">You Logged in at <%=regisTime %></b>
	<table  style="text-align:center; margin-left:auto;">
<tr><td>&nbsp;&nbsp;<a href="logout">logout</a>&nbsp;&nbsp;</td></tr>
<tr><td>&nbsp;&nbsp;<a href="/OnlineSchoolManagmentPortal/Dashboard.jsp">Dashboard</a>&nbsp;&nbsp;</td></tr>
</table>
<br/>
<br/>
<h3 style="text-align:center">@@@@@@@@ Class Report @@@@@@@@@@</h3>
<h3 style="text-align:center">ID: <%=cl.getClassId() %> Division: <%=cl.getDivision() %>Standard: <%=cl.getStandard() %></h3>
<jy:displayReport relist="<%=reList %>"/>
<br/>
<br/>
</div>
<%@include file='index.html' %>
<%}else{
	response.sendRedirect("/OnlineSchoolManagementPortal/Dashboard.jsp"); 
%>
<%}} %>
<%mySession.removeAttribute("exception");
mySession.removeAttribute("exceptionSys");%>
</body>
</html>