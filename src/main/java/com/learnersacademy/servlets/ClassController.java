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
import com.learnersacademy.entity.ClassObject;

/**
 * Servlet implementation class ClassController
 */
public class ClassController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClassController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(false);
		PrintWriter pw =response.getWriter();
		LearnersDetail2 detail2 = new LearnersDetail2();
		if(session!=null) {
			try {
				List<ClassObject> cList=detail2.ClassList();
				session.setAttribute("clist",cList);
				response.sendRedirect("ShowClass.jsp");
				
			} catch (ApplicationException e) {
				session.setAttribute("exception", e.getMessage());
				response.sendRedirect("Dashboard.jsp");
			}
			catch (Exception e1) {
				session.setAttribute("exceptionSys", e1.getMessage());
				response.sendRedirect("Dashboard.jsp");
			}
			}
		else {
			pw.print("<h2>Session has expired...!!!</h2>");
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
				Integer classId = Integer.parseInt(request.getParameter("classId_delete"));
				detail2.deleteClass(classId);
				List<ClassObject> cList=detail2.ClassList();
				session.setAttribute("cList", cList);
				response.sendRedirect("ShowClass.jsp");
			}  catch (ApplicationException e) {
				session.setAttribute("exception", e.getMessage());
				response.sendRedirect( "class");}
			catch(Exception e1) {

				session.setAttribute("exceptionSys", e1.getMessage());
				response.sendRedirect( "class");
			}
				
		}
		else {
			pw.print("<h3>Your session has expired..!!</h3>");
			response.setHeader("refresh", "url='/OnlineSchoolManagmentPortal'");
		}
			}
	}


