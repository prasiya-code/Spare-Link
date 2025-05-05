package com.spareLink.servlet;

import com.spareLink.model.Product;
import com.spareLink.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService;

    @Override
    public void init() throws ServletException {
        productService = new ProductService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> spare_parts = productService.getAllProducts();
        request.setAttribute("spare_parts", spare_parts);
        request.getRequestDispatcher("homePage.jsp").forward(request, response);
    }
}
