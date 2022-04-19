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
import com.learnersacademy.entity.SubjectObject;

/**
 * Servlet implementation class UpdateSubject
 */
public class UpdateSubject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateSubject() {
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
		SubjectObject subject=new SubjectObject();
		if(session!=null) {
			try {
				subject.setSubjectId(Integer.parseInt(request.getParameter("id")));
				subject.setSubjName(request.getParameter("newName"));
				subject.setSubjLang(request.getParameter("newLang"));
				if(!request.getParameter("new_ClassId").isEmpty()) {
				subject.setClassId(Integer.parseInt(request.getParameter("new_ClassId")));}
				else {subject.setClassId(0);}
				if(!request.getParameter("newTeacherId").isEmpty()) {
					subject.setTeacherId(Integer.parseInt(request.getParameter("newTeacherId")));}
					else {subject.setTeacherId(0);
					}
				detail2.updateSubject(subject);
				List<SubjectObject> subjList = detail2.SubjectList();
				session.setAttribute("subjList", subjList);
				response.sendRedirect("ShowSubject.jsp");
			} 
			catch (ApplicationException e) {
				session.setAttribute("exception", e.getMessage());
				response.sendRedirect( "subject");
				}
			catch(Exception e1) {

				session.setAttribute("exception", e1.getMessage());
				response.sendRedirect( "subject");
			}}
		else {
			pw.print("<center><h3>Your session has expired.. Navigating you to home page.....</h3></center>");
			response.setHeader("refresh", "url='/OnlineSchoolManagmentPortal'");
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
		SubjectObject subject=new SubjectObject();
		if(session!=null) {
			try {
				subject.setSubjName(request.getParameter("subName"));
				subject.setSubjLang(request.getParameter("subLang"));
				detail2.addSubject(subject);
				List<SubjectObject> subjList = detail2.SubjectList();
				session.setAttribute("subjList", subjList);
				response.sendRedirect("ShowSubject.jsp");
			}
			catch (ApplicationException e) {
				session.setAttribute("exception", e.getMessage());
				response.sendRedirect( "subject");
				}
			catch(Exception e1) {

				session.setAttribute("exceptionSys", e1.getMessage());
				response.sendRedirect( "subject");
			}
					}
		else {
			pw.print("<h3>Your session has expired..!!</h3>");
			response.setHeader("refresh", "url='/OnlineSchoolManagmentPortal'");
		}
	}
}
