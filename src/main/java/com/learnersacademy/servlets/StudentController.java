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
import com.learnersacademy.entity.StudentObject;

/**
 * Servlet implementation class StudentController
 */
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentController() {
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
				List<StudentObject> sList=detail2.StudentList();
				session.setAttribute("StudentList", sList);
				response.sendRedirect("ShowStudent.jsp");
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
				Integer studentId = Integer.parseInt(request.getParameter("studentId_delete"));
				detail2.deleteStudent(studentId);
				List<StudentObject> sList=detail2.StudentList();
				session.setAttribute("sList", sList);
				response.sendRedirect("ShowStudent.jsp");
			} catch (ApplicationException e) {
				session.setAttribute("exception", e.getMessage());
				response.sendRedirect( "student");}
			catch(Exception e1) {

				session.setAttribute("exceptionSys", e1.getMessage());
				response.sendRedirect( "student");
			}
			
		}
		else {
			pw.print("<h3>Your session has expired..!!</h3>");
			response.setHeader("refresh", "url='/OnlineSchoolManagmentPortal'");
		}
		
	}
}


