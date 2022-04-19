package com.learnersacademy.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.error.exception.ApplicationException;
import com.learnersacademy.details.LearnersDetail2;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		PrintWriter pw = response.getWriter();
		RequestDispatcher rd = null;
		
		LearnersDetail2 detail2= new LearnersDetail2();
		try {
			if(detail2.userLogin(username,password)) {
				HttpSession session=request.getSession();
				session.setAttribute("username",username);
				session.setMaxInactiveInterval(60);
				response.sendRedirect("Dashboard.jsp");
				//rd = request.getRequestDispatcher("Dashboard.jsp");
			}
		} catch (ApplicationException e) {
			rd=request.getRequestDispatcher("login.html");
			rd.include(request, response);
			pw.print("<center><span style='color:red'>" +e.getMessage() +"</span></center>");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}


}
