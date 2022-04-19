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
 * Servlet implementation class UpdateClass
 */
public class UpdateClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateClass() {
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
		ClassObject cl = new ClassObject();
		if(session!=null) {
			cl.setClassId(Integer.parseInt(request.getParameter("classId")));
			cl.setDivision(request.getParameter("division"));
			cl.setStandard(Integer.parseInt(request.getParameter("standard")));
			
			try {
				List<Object>reList=detail2.generateReport(cl);
				session.setAttribute("reList", reList);
				session.setAttribute("class", cl);
				response.sendRedirect("ShowReport.jsp");
			} catch (ApplicationException e) {
				session.setAttribute("exception", e.getMessage());
				response.sendRedirect("class");
				
			}
			catch (Exception e1) {
				session.setAttribute("exception", e1.getMessage());
				response.sendRedirect("class");
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
		ClassObject cl = new ClassObject();
		if(session!=null) {
			try {
				if(!request.getParameter("standard").isEmpty())
				{
					cl.setStandard(Integer.parseInt(request.getParameter("standard")));
					}
				if(request.getParameter("division").isEmpty()) {
					cl.setDivision(request.getParameter("division"));
				}
				detail2.addClass(cl);
				List<ClassObject> cList=detail2.ClassList();
				session.setAttribute("cList", cList);
				response.sendRedirect("ShowClass.jsp");
					
			} catch (ApplicationException e) {
				session.setAttribute("exception", e.getMessage());
				response.sendRedirect("class");
			}
			catch (Exception e1) {
				session.setAttribute("exception", e1.getMessage());
				response.sendRedirect("class");
			}
		}
		else {
			pw.print("<h3>Your session has expired..!!</h3>");
			response.setHeader("refresh", "url='/OnlineSchoolManagementPortal'");
		}
		
		
	}

}
