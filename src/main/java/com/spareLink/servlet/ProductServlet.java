package com.spareLink.servlet;

import com.spareLink.model.Product;
import com.spareLink.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/home") // This maps the servlet to '/home'
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        productService = new ProductService();
    }

    // Handle GET requests
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get list of products from the service
        List<Product> spare_parts = productService.getAllProducts();
        
        // Set the product list as a request attribute for JSP
        request.setAttribute("spare_parts", spare_parts);
        
        // Forward the request to 'homePage.jsp' for displaying the products
        request.getRequestDispatcher("homePage.jsp").forward(request, response);
    }

    // Handle POST requests by forwarding to doGet method
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Simply forward the POST request to the GET method to display products
        doGet(request, response);
    }
}
