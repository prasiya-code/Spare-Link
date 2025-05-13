package com.spareLink.servlet;

import com.spareLink.service.BrandService;
import com.spareLink.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/AddBrandCategoryServlet")
public class AddBrandCategoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/Admin/add-brand-category.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        String name = request.getParameter("name");

        boolean success = false;
        String message = "";

        if (type != null && name != null && !name.trim().isEmpty()) {
            name = name.trim();

            if (type.equals("brand")) {
                BrandService brandService = new BrandService();
                
                if (brandService.brandExists(name)) {
                    message = "Brand '" + name + "' already exists!";
                } else {
                    success = brandService.insertBrand(name);
                    message = success ? "Brand '" + name + "' added successfully!" : "Failed to add brand.";
                }

            } else if (type.equals("category")) {
                CategoryService categoryService = new CategoryService();
                
                if (categoryService.categoryExists(name)) {
                    message = "Category '" + name + "' already exists!";
                } else {
                    success = categoryService.insertCategory(name);
                    message = success ? "Category '" + name + "' added successfully!" : "Failed to add category.";
                }
            }

        } else {
            message = "Please enter a valid name.";
        }

        request.setAttribute("message", message);
        request.getRequestDispatcher("Admin/add-brand-category.jsp").forward(request, response);
    }
}
