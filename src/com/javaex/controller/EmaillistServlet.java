package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.EmaillistDao;
import com.javaex.vo.EmailVo;

@WebServlet("/el")
public class EmaillistServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("\nHI? This is Servlet.");
		
		String actionName = request.getParameter("a");
		
		if("form".equals(actionName)) {
			System.out.println("This is form.");
			RequestDispatcher rd = request.getRequestDispatcher("form.jsp");
			rd.forward(request, response);
			
		} else if("insert".equals(actionName)) {
			System.out.println("This is insert.");
			
			request.setCharacterEncoding("UTF-8");
			String lastName = request.getParameter("ln");
			String firstName = request.getParameter("fn");
			String email = request.getParameter("email");

			EmailVo vo = new EmailVo();
			vo.setLastName(lastName);
			vo.setFirstName(firstName);
			vo.setEmail(email);

			EmaillistDao dao = new EmaillistDao();
			dao.insert(vo);
			
			response.sendRedirect("el?a=list");

		} else if("list".equals(actionName)) {
			System.out.println("This is list.");
			
			EmaillistDao dao = new EmaillistDao();
			List<EmailVo> eList = dao.getList();
			
			request.setAttribute("eList", eList); // setAttribute(이름, 데이터)
			RequestDispatcher rd = request.getRequestDispatcher("list.jsp");
			rd.forward(request, response);
			
		} else {
			System.out.println("A has wrong value");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
