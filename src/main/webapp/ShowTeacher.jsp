<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="com.learnersacademy.entity.TeacherObject"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display All Teacher</title>
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
List<TeacherObject> techList=(List<TeacherObject>)mySession.getAttribute("techList");
if(techList !=null){
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
<center><b>Teacher Details</b></center>
<br/>
<jy:displayTeacher techList="<%=techList %>"/>
<br/>
<br/>
<form action="UpdateTeacher" method="post">
<table border="1" style="text-align:center; margin-left:auto;margin-right:auto;">
<tr>
<td>Teacher Name </td><td><input type="text" name="teacherName"  placeholder="Teacher Name"></td>
</tr>
<tr>
<td>Teacher Category</td><td><input type="text" name="teacherCategory" placeholder="Teacher Category"></td>
</tr>
<tr>
<td>Experience</td><td><input type="number" name="experience" placeholder="Experience"></td>
</tr>
<tr>
<td colspan="2"><input type="submit" name="add" value="ADD TEACHER"></td>
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