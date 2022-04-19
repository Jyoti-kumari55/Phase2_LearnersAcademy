package com.learnersacademy.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.error.exception.ApplicationException;
import com.learnersacademy.details.LearnersDetail2;
import com.learnersacademy.entity.TeacherObject;

/**
 * Servlet implementation class UpdateTeacher
 */
public class UpdateTeacher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateTeacher() {
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
		TeacherObject teacher=new TeacherObject();
		if(session!=null) {
			try {
				teacher.settId(Integer.parseInt(request.getParameter("id")));
				teacher.setTeacherName(request.getParameter("new_Name"));
				teacher.setTeacherCategory(request.getParameter("new_Category"));
				if(request.getParameter("new_Exp").isEmpty()) {teacher.setExperience(0);}
				else {
				teacher.setExperience(Integer.parseInt(request.getParameter("new_Exp")));}
				detail2.updateTeacher(teacher);
				List<TeacherObject> techList=detail2.TeacherList();
				session.setAttribute("techList", techList);
				response.sendRedirect("ShowTeacher.jsp");
			} catch (ApplicationException e) {
				session.setAttribute("exception", e.getMessage());
				response.sendRedirect( "teacher");
				}
			catch(Exception e1) {

				session.setAttribute("exception", e1.getMessage());
				response.sendRedirect( "teacher");
			}
		

					}
		else {
			pw.print("<center><h3>Your session has expired.. Navigating you to home page.....</h3></center>");
			response.setHeader("refresh", "5;url='/SchoolManagmentSystem'");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session=request.getSession(false);
		PrintWriter out=response.getWriter();
		LearnersDetail2 detail2 = new LearnersDetail2();
		TeacherObject teacher=new TeacherObject();
		if(session!=null) {
			try {
				teacher.setTeacherName(request.getParameter("teacher_Name"));
				teacher.setTeacherCategory(request.getParameter("teacher_Category"));
				if(request.getParameter("experience").isEmpty()) {
					teacher.setExperience(0);
				}else {
				teacher.setExperience(Integer.parseInt(request.getParameter("experience")));}
				detail2.addTeacher(teacher);
				List<TeacherObject> techList=detail2.TeacherList();
				session.setAttribute("techList", techList);
				response.sendRedirect("ShowTeacher.jsp");
			} catch (ApplicationException e) {
				session.setAttribute("exception", e.getMessage());
				response.sendRedirect( "teacher");}
			catch(Exception e1) {

				session.setAttribute("exception", e1.getMessage());
				response.sendRedirect( "teacher");
			}
		}
	}

}
