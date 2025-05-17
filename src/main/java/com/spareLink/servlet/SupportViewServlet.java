package com.spareLink.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spareLink.model.Support;
import com.spareLink.service.SupportService;



@WebServlet("/supportGetAll")
public class SupportViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	
        List <Support> allSupports = SupportService.getAllSupport();
		request.setAttribute("allSupports", allSupports);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("supportView.jsp");
		dispatcher.forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	doGet(request, response);
	}
    
    
   
}
