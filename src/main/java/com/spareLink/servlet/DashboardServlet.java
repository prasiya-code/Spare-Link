package com.spareLink.servlet;

import com.spareLink.service.SparePartService;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    private SparePartService productService = new SparePartService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int totalParts = productService.getTotalParts();
        int lowStockCount = productService.getLowStockCount();
        int totalCategories = productService.getTotalCategories();
        int totalUsers = productService.getTotalUsers();

        request.setAttribute("totalParts", totalParts);
        request.setAttribute("lowStockCount", lowStockCount);
        request.setAttribute("totalCategories", totalCategories);
        request.setAttribute("totalUsers", totalUsers);

        RequestDispatcher dispatcher = request.getRequestDispatcher("Admin/dashboard.jsp");
        dispatcher.forward(request, response);
    }
}
