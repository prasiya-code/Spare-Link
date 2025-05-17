package com.spareLink.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spareLink.service.SupportService;

@WebServlet("/supportInsertServlet")
public class SupportInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String issueType = request.getParameter("issueType");
		String fullName =request.getParameter("fullName");
		String email = request.getParameter("email");
		String contactNo = request.getParameter("contactNo");
		String inquiry = request.getParameter("inquiry");
		
		boolean isTrue;
		
		isTrue = SupportService.insertdata(issueType, fullName, email, contactNo, inquiry);
		
		if(isTrue == true) {
			
			String alertMessage = "Data insert successful";
			response.getWriter().println("<script> alert('"+alertMessage+"'); window.location.href = 'supportGetAll'</script>");
			
		}
		
		else {
			
			RequestDispatcher dis2 = request.getRequestDispatcher("wrong.jsp");
			dis2.forward(request, response);
		}
	
	}
   
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       RequestDispatcher dispatcher = request.getRequestDispatcher("supportForm.jsp");
       dispatcher.forward(request, response);
   }


}


