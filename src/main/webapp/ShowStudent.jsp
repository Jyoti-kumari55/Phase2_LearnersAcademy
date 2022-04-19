<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="com.learnersacademy.entity.StudentObject"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
 <%@page session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display All Student</title>
</head>
<body>
<div style="text-align:centre">
<%HttpSession mySession=request.getSession(false);
if(mySession==null){
	out.print("<center><h3>Your session has expired..!!</h3></center>");
	response.setHeader("refresh", "url='/OnlineSchoolManagmentPortal'");
}
else{
@SuppressWarnings("unchecked")
List<StudentObject> sList=(List<StudentObject>)mySession.getAttribute("sList");
if(sList !=null){
Date regisTime = new Date(mySession.getCreationTime());
%>
<h1 style="Color:blue">Learner's Academy</h1><b style="Color:blue">You Logged in at <%=regisTime %></b>
<table  style="text-align:center; margin-left:auto;">
<tr><td>&nbsp;&nbsp;<a href="logout">logout</a>&nbsp;&nbsp;</td></tr>
<tr><td>&nbsp;&nbsp;<a href="/OnlineSchoolManagmentPortal/Dashboard.jsp">Dashboard</a>&nbsp;&nbsp;</td></tr>
</table>
<br/>
<div style="color:red;text-align: center; border: 1px outset red;
  background-color: lightblue;" >
<%if(mySession.getAttribute("exception")!=null) {%>
<%=mySession.getAttribute("exception") %>

<%}if(mySession.getAttribute("exceptionSys")!=null) {%>
System Error <%=mySession.getAttribute("exceptionSys") %>
<%} %>
</div>
<br/>
<center><b>Student Details</b></center>
<br/>
<jy:displayStudent sList="<%=sList %>"/>
<br/>
<br/>
<form action="UpdateStudent" method="post">
<table border="1" style="text-align:center; margin-left:auto;margin-right:auto;">
<tr>
<td>Student Name </td><td><input type="text" name="studentName"  placeholder="Student Name"></td>
</tr>
<tr>
<td>DOB</td><td><input type="date" name="studentDob" placeholder="Student DOB"></td>
</tr>
<tr>
<td colspan="2"><input type="submit" name="add" value="ADD STUDENT"></td>
</tr>
</table>
</form>
</div>
<%@include file='index.html' %>
<%} else{
response.sendRedirect("/OnlineSchoolManagmentPortal/Dashboard.jsp");
%>

<%}} %>

<%mySession.removeAttribute("exception");
mySession.removeAttribute("exceptionSys");
%>
</body>
</html>