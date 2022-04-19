package com.learnersacademy.display;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.learnersacademy.entity.TeacherObject;

public class DisplayTeacher extends SimpleTagSupport {

	private List<TeacherObject> techList;

	public void setTeacherList(List<TeacherObject> tecList) {
		this.techList=tecList;
		
	}
	public void doTag() throws IOException{
		JspWriter jw = getJspContext().getOut();
		String content[]= {"id","Name","Category","Experience"};
		
		jw.print("<div style='text-align:center'>");
		jw.print("<b>Student Details</b>");
		jw.print("<br/>");
		jw.print("<table border='1px' style='text-align:center; margin-left:auto;margin-right:auto;'>");		
		jw.print("<tr>");
		for (String cont : content) {
			jw.print("<th>" + cont + "</th>");
	}
	jw.print("</tr>");
	for(TeacherObject tea:techList) 
	{

		jw.print("<tr>");
	jw.print("<form action='teacher' method='post'>");
	jw.print("<td><input type='hidden'  name='Subjectid_delete' value='" + tea.gettId()+"'/>" +tea.gettId()+"</td>");
	jw.print("<td>"+tea.getTeacherName()+"</td>");
	jw.print("<td>"+tea.getTeacherCategory()+"</td>");
	jw.print("<td>"+tea.getExperience()+"</td>");

	jw.print("<td><input type='submit' value='Delete'/></td>");
	jw.print("</tr>");
	jw.print("</form>");

	jw.print("<tr bgcolor='grey'>");
	jw.print("<form action='UpdateSubject' method='post'>");
	jw.print("<td><input type='text' name='id' value='" +tea.gettId()+"'/></td>");
	jw.print("<td><input type='text' name='new_name' placeholder='new Teacher name'></td>");
	jw.print("<td><input type='text' name='new_category' placeholder='new Category'></td>");


	jw.print("<td><input type='number' name='new_Experience' value='"+tea.getExperience() +"' ></td>");
	jw.print("<td><input type='submit' value='Update'/></td>");
	jw.print("</tr>");
	jw.print("</form>");
	}
	jw.print("</table>");
	}
}
