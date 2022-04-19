package com.learnersacademy.display;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.learnersacademy.entity.StudentObject;
import com.learnersacademy.entity.SubjectObject;
import com.learnersacademy.entity.TeacherObject;

public class DisplayReport extends SimpleTagSupport {

	private List<Object> reList;

	public void setReportList(List<Object> reList) {
		this.reList=reList;
		
	}
	@Override
	public void doTag() throws IOException{
		SimpleDateFormat sdf= new SimpleDateFormat("MM-dd-yyyy");
		
		JspWriter jw = getJspContext().getOut();
		String content[]= {"id","Name","DOB"};
		jw.print("<div style='text-align:center'>");
		jw.print("<b>Student Details</b>");
		jw.print("<br/>");
		jw.print("<table border='1px' style='text-align:center; margin-left:auto;margin-right:auto;'>");		
		jw.print("<tr>");
		for (String cont : content) {
			jw.print("<th>" + cont + "</th>");
		}
		jw.print("</tr>");
		
		for(Object studObj:reList)
		{	
			if(studObj.getClass()==StudentObject.class) {
			StudentObject std=(StudentObject)studObj;
			jw.print("<tr>");
			jw.print("<form action='student' method='post'>");
			jw.print("<td>" +std.getStudentId() + "</td>");
			jw.print("<td>"+std.getStudentName()+"</td>");
			jw.print("<td>"+sdf.format(std.getStudentdob())+"</td>");
			jw.print("<tr>");
			}
		}
		
		jw.print("</table>");
	    jw.print("<br/>");
		jw.print("<br/>");
		jw.print("<b>Subject Details</b>");
		jw.print("<br/>");
		
		String content2[]= {"id","Name","Language","Teacher id"};
		
		jw.print("<table border='1px' style='text-align:center;  margin-left:auto;margin-right:auto;'>");
		jw.print("<tr>");
		for (String cont : content2) {
			jw.print("<th>" + cont + "</th>");
		}
		jw.print("</tr>");
		
		for(Object su:reList)
			{	
			if(su.getClass()==SubjectObject.class) {
				SubjectObject sub=(SubjectObject)su;
			jw.print("<tr>");
			jw.print("<form action='subject' method='post'>");
			jw.print("<td>"+sub.getSubjectId() +"</td>");
			jw.print("<td>"+sub.getSubjName()+"</td>");
			jw.print("<td>"+sub.getSubjLang()+"</td>");
			jw.print("<td>"+sub.getTeacherId()+"</td>");
			jw.print("<tr>");
			}
		}
		jw.print("</table>");
		jw.print("<br/>");
		jw.print("<br/>");
		jw.print("<b>Teacher Details</b>");
		jw.print("<br/>");
		String content3[]= {"id","Name","Category","Experience"};
		jw.print("<table border='1px' style='text-align:center;  margin-left:auto;margin-right:auto;'>");
		jw.print("<tr>");
		for (String cont : content3) {
			jw.print("<th>" + cont + "</th>");
		}
		jw.print("</tr>");
		for(Object obj:reList) {
			if(obj.getClass()==TeacherObject.class){
				TeacherObject tch = (TeacherObject)obj;
				jw.print("<tr>");
				jw.print("<form action='teacher' method='post'>");
				jw.print("<td>"+tch.gettId() +"</td>");
				jw.print("<td>"+tch.getTeacherName()+"</td>");
				jw.print("<td>"+tch.getTeacherCategory()+"</td>");
				jw.print("<td>"+tch.getExperience()+"</td>");
				jw.print("<tr>");
			}
		}
		jw.print("</table>");
		jw.print("</div>");
	}

}
