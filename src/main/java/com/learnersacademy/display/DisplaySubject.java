package com.learnersacademy.display;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.learnersacademy.entity.SubjectObject;

public class DisplaySubject extends SimpleTagSupport{

	private List<SubjectObject> subjList;
	public void setSubjectList(List<SubjectObject> subjList) {
		this.subjList=subjList;
	}
	@Override
	public void doTag() throws IOException{
		JspWriter jw = getJspContext().getOut();
			String content[]= {"id","Name","Language","Teacher id","Class id"};
			
			jw.print("<div style='text-align:center'>");
			jw.print("<b>Subject Details</b>");
			jw.print("<br/>");
			jw.print("<table border='1px' style='text-align:center; margin-left:auto;margin-right:auto;'>");		
			jw.print("<tr>");
			for (String cont : content) {
				jw.print("<th>" + cont + "</th>");
	  }
jw.print("</tr>");
for(SubjectObject sub:subjList) 
{
	jw.print("<tr>");
jw.print("<form action='subject' method='post'>");
jw.print("<td><input type='hidden'  name='Subjectid_delete' value='" + sub.getSubjectId()+"'/>" +sub.getSubjectId()+"</td>");
jw.print("<td>"+sub.getClassId()+"</td>");
jw.print("<td>"+sub.getTeacherId()+"</td>");
jw.print("<td>"+sub.getSubjName()+"</td>");
jw.print("<td>"+sub.getSubjLang()+"</td>");
jw.print("<td><input type='submit' value='Delete'/></td>");
jw.print("</tr>");
jw.print("</form>");

jw.print("<tr bgcolor='grey'>");
jw.print("<form action='UpdateSubject' method='post'>");
jw.print("<td><input type='text' name='id' value='" +sub.getSubjectId()+"'/></td>");
jw.print("<td><input type='text' name='new_name' placeholder='new Subject name'></td>");
jw.print("<td><input type='text' name='new_lang' placeholder='new Lang name'></td>");

jw.print("<td><input type='number' name='newClassId' placeholder='New Class_id' ></td>");

jw.print("<td><input type='number' name='newTeacherid' placeholder='New Teacher_id' ></td>");
jw.print("<td><input type='submit' value='Update'/></td>");
jw.print("</tr>");
jw.print("</form>");
}
jw.print("</table>");
	}
}
