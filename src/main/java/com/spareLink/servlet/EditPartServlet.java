package com.spareLink.servlet;

import com.spareLink.model.Brand;
import com.spareLink.model.Category;
import com.spareLink.model.SparePart;
import com.spareLink.service.BrandService;
import com.spareLink.service.CategoryService;
import com.spareLink.service.SparePartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/edit-part")
public class EditPartServlet extends HttpServlet {

    private final SparePartService sparePartService = new SparePartService();
    private final BrandService brandService = new BrandService();
    private final CategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));

            SparePart sparePart = sparePartService.getSparePartById(id);
            List<Brand> brands = brandService.getAllBrands();
            List<Category> categories = categoryService.getAllCategories();

            request.setAttribute("part", sparePart);
            request.setAttribute("brands", brands);
            request.setAttribute("categories", categories);

            request.getRequestDispatcher("edit-part.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            double price = Double.parseDouble(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String status = request.getParameter("status");
            int brandId = Integer.parseInt(request.getParameter("brand_id"));
            int categoryId = Integer.parseInt(request.getParameter("category_id"));
            String description = request.getParameter("description");

            // Get existing spare part to retain image
            SparePart existing = sparePartService.getSparePartById(id);
            String image = existing != null ? existing.getImage() : "";

            SparePart updatedSparePart = new SparePart(id, name, price, image, description, brandId, categoryId, quantity, status);
            boolean success = sparePartService.updateSparePart(updatedSparePart);

            if (success) {
                response.sendRedirect("parts-list.jsp?success=true");
            } else {
                response.sendRedirect("edit-part.jsp?id=" + id + "&error=true");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
