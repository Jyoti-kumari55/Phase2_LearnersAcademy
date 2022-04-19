package com.learnersacademy.display;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.learnersacademy.entity.StudentObject;

public class DisplayStudent extends SimpleTagSupport{

	private List<StudentObject> sList;
	public void setStudentList(List<StudentObject> sList) {
		this.sList=sList;	
	}
	@Override
public void doTag() throws IOException{
SimpleDateFormat sdf= new SimpleDateFormat("MM-dd-yyyy");
	
	JspWriter jw = getJspContext().getOut();
	String content[]= {"id","Name","DOB","Class id"};
	
	jw.print("<div style='text-align:center'>");
	jw.print("<b>Student Details</b>");
	jw.print("<br/>");
	jw.print("<table border='1px' style='text-align:center; margin-left:auto;margin-right:auto;'>");		
	jw.print("<tr>");
	for (String cont : content) {
		jw.print("<th>" + cont + "</th>");
	}
	jw.print("</tr>");
	for(StudentObject st:sList) 
	{
		jw.print("<tr>");
	jw.print("<form action='student' method='post'>");
	jw.print("<td><input type='hidden'  name='Studentid_delete' value='" + st.getStudentId()+"'/>" +st.getStudentId()+"</td>");
	jw.print("<td>"+st.getStudentName()+"</td>");
	jw.print("<td>"+sdf.format(st.getStudentdob())+"</td>");
	jw.print("<td>"+st.getClassId()+"</td>");
	jw.print("<td><input type='submit' value='Delete'/></td>");
	jw.print("</tr>");
	jw.print("</form>");
	
	jw.print("<tr bgcolor='grey'>");
	jw.print("<form action='UpdateStudent' method='post'>");
	jw.print("<td><input type='text' name='id' value='" +st.getStudentId()+"'/></td>");
	jw.print("<td><input type='text' name='new_name' placeholder='new name'></td>");

	jw.print("<td><input type='date' name='newdob' placeholder='mm-dd-yyyy' ></td>");
	
	jw.print("<td><input type='text' name='newClassid' placeholder='New class' ></td>");
	jw.print("<td><input type='submit' value='Update'/></td>");
	jw.print("</tr>");
	
	jw.print("</form>");
	}
	jw.print("</table>");
	}
}
