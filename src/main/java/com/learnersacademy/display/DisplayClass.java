package com.learnersacademy.display;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import com.learnersacademy.entity.ClassObject;

public class DisplayClass extends SimpleTagSupport {
	private List<ClassObject> cList;
	public void setClassList(List<ClassObject> cList) {
		this.cList=cList;	
	}
	
	@Override 
	public void doTag() throws IOException{
		JspWriter jw= getJspContext().getOut();
		String content[]= {"id","Division","Standard"};
		
		jw.print("<table border='1px' style='text=align:center; margin-left:auto; margin-right:auto;'>");
		jw.print("<tr>");
		for(String cont: content) {
			jw.print("<th>" +cont + "</th>");
		}
		jw.print("</tr>");
				
		for(ClassObject cl:cList)
		{
			jw.print("<tr>");
			jw.print("<form action='UpdateClass' method='post'>");
			jw.print("<td><input type='hidden'  name='classId' value='" + cl.getClassId() + "'/>" 
			+ "<input type='hidden' name='division' value='" +cl.getDivision()+"'/>" 
			+"<input type='hidden' name='standard' value='" +cl.getStandard()
			+"'/><input type='submit' value='Generate Report'/></td>");
			jw.print("</form>");
			
			
			
			jw.print("<form action='class' method='post'>");
			jw.print("<td><input type='hidden' name='classId_delete' value='" + cl.getClassId() + "</td>");
			jw.print("<td>"+cl.getStandard()+"</td>");
			jw.print("<td>"+cl.getDivision()+"</td>");
			jw.print("<td><input type='submit' value='Delete'/></td>");
			jw.print("</form>");
			jw.print("</tr>");
		}
		jw.print("</table>");
	}
}
