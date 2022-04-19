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
 * Servlet implementation class SubjectController
 */
public class SubjectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubjectController() {
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
		if(session!=null) {
			try {
				List<SubjectObject> subjList=detail2.SubjectList();
				session.setAttribute("subjList", subjList);
				response.sendRedirect("ShowSubject.jsp");
			}catch (ApplicationException e) {
				session.setAttribute("exception", e.getMessage());
				response.sendRedirect( "Dashboard.jsp");}
			catch(Exception e1) {

				session.setAttribute("exceptionSys", e1.getMessage());
				response.sendRedirect( "Dashboard.jsp");
			}

		}
		else {
			pw.print("<h3>Your session has expired..!!</h3>");
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
		
		if(session!=null) {
			try {
				Integer subjectId = Integer.parseInt(request.getParameter("subjectId_delete"));
				detail2.deleteSubject(subjectId);
				List<SubjectObject> subjList=detail2.SubjectList();
				session.setAttribute("subjList", subjList);
				response.sendRedirect("ShowSubject.jsp");
				
			} 
			  catch (ApplicationException e) {
				session.setAttribute("exception", e.getMessage());
				response.sendRedirect( "subject");}
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


