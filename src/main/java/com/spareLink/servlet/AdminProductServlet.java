package com.spareLink.servlet;

import com.spareLink.model.SparePart;
import com.spareLink.service.SparePartService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class AdminProductServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final SparePartService sparePartService = new SparePartService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Fetch product list
            List<SparePart> productList = sparePartService.getAllSpareParts();
            
            // Set the list to request scope
            request.setAttribute("spare_parts", productList);

            // Forward to JSP view
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/adminProductView.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
        
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
