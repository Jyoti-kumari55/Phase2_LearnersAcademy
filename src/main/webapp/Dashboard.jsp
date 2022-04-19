<%@page import="java.util.Date"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Learner's Academy</title>
</head>
<body>

<%HttpSession mySession=request.getSession(false);
if(mySession==null){
	out.print("Your session has expired..!!");
	response.setHeader("refresh", "url='/OnlineSchoolManagmentPortal'");
}
else{
	Date regisTime = new Date(mySession.getCreationTime());
%>
<h1 style="Color:Crimson">Learner's Academy</h1>

<div style="text-align:center;">
<h1 style="color:DarkGreen"><strong><b> Welcome To Indias's Largest Learning Platform</strong></b></h1>

<table  style="text-align:center; margin-left:auto;">
<tr><td>&nbsp;&nbsp;<a href="logout" style="color:Navy"><b>logout</b></a>&nbsp;&nbsp;</td></tr>
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
 <table border="1" style="text-align:center;color:SaddleBrown; margin-left:auto; margin-right:auto;">
 <tr><td>&nbsp;&nbsp;<a href="class" style="color:DeepPink"><b>Class Controller</b></a>&nbsp;&nbsp;</td><td> Get a list of all Classes
<br/>Add New Class
<br/>Delete an existing Class
<br/>Generate Class Report</td></tr>

<tr><td>&nbsp;&nbsp;<a href="student" style="color:DeepPink"><b>Student Controller</b></a>&nbsp;&nbsp;</td><td> Get a list of all students
<br/>Add New Students
<br/>Update Student Details
<br/>Delete an existing student</td></tr>

<tr><td>&nbsp;&nbsp;<a href="subject" style="color:DeepPink"><b>Subject Controller</b></a>&nbsp;&nbsp;</td><td>
 Get a list of all Subjects
<br/>Add New Subject
<br/>Update Subject Details
<br/>Delete an existing Subject</td></tr>

<tr><td>&nbsp;&nbsp;<a href="teacher" style="color:DeepPink"><b>Teacher Controller</b></a>&nbsp;&nbsp;</td><td> Get a list of all Teachers
<br/>Add New Teachers
<br/>Update Teacher Details
<br/>Delete an existing Teacher</td></tr>


</table>

</div>
<%@include file='index.html' %>
<%} %>
<%mySession.removeAttribute("exception");
mySession.removeAttribute("exceptionSys");%>
</body>
</html>