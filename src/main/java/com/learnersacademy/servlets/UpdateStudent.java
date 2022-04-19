package com.learnersacademy.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.error.exception.ApplicationException;
import com.learnersacademy.details.LearnersDetail2;
import com.learnersacademy.entity.StudentObject;

/**
 * Servlet implementation class UpdateStudent
 */
public class UpdateStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateStudent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session=request.getSession(false);
		PrintWriter pw=response.getWriter();
		LearnersDetail2 detail2 = new LearnersDetail2();
		StudentObject student = new StudentObject();
		if(session!=null)
		{
			try 
			{
				student.setStudentName(request.getParameter("new_Name"));
				if(!request.getParameter("new_dob").isEmpty())
				{
					Date date = new Date();
					Calendar calendar = Calendar.getInstance();
				
					student.setStudentdob(date);
				}
					student.setStudentId(Integer.parseInt(request.getParameter("id")));	
			        if(!request.getParameter("new_ClassId").isEmpty())
			        {
					student.setClassId(Integer.parseInt(request.getParameter("new_ClassId")));	
				}
				else 
				{
					student.setClassId(0);
				}
				detail2.updateStudent(student);
				List<StudentObject>sList=detail2.StudentList();
				session.setAttribute("sList",sList);
				response.sendRedirect("ShowStudent.jsp");
				
			} 
			   catch ( ApplicationException e)
			{
				session.setAttribute("exception", e.getMessage());
				response.sendRedirect("student");
			}
			catch (Exception e1)
			{
				session.setAttribute("exceptionSys", e1.getMessage());
				response.sendRedirect("student");
				
			}
		}
		else 
		{
			pw.print("<h3>Your session has expires..detail2..!!</h3>");
			response.setHeader("refresh", "url='/OnlineSchoolManagementPortal'");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session=request.getSession(false);
		PrintWriter pw=response.getWriter();
		LearnersDetail2 detail2 = new LearnersDetail2();
		StudentObject student = new StudentObject();
		if(session!=null) {
			try {
				student.setStudentName(request.getParameter("studentName"));
				if(!request.getParameter("studentdob").isEmpty()) {
				Date date=new Date();
				student.setStudentdob((date));}
				detail2.addStudent(student);
				List<StudentObject> studentList=detail2.StudentList();
				session.setAttribute("studentList", studentList);
				response.sendRedirect("ShowStudent.jsp");
			}
			catch (ApplicationException e) {
				session.setAttribute("exception", e.getMessage());
				response.sendRedirect( "student");
				}
			
			catch(Exception e1) {

				session.setAttribute("exception", e1.getMessage());
				response.sendRedirect( "student");
			}			
		}
		else {
			pw.print("<h3>Your session has expired..!!</h3>");
			response.setHeader("refresh", "url='/OnlineSchoolManagmentPortal'");
		}
			}
			

}
