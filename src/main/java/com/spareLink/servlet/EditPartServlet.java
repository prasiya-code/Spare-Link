package com.spareLink.servlet;

import com.spareLink.model.Brand;
import com.spareLink.model.Category;
import com.spareLink.model.SparePart;
import com.spareLink.service.BrandService;
import com.spareLink.service.CategoryService;
import com.spareLink.service.SparePartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/EditPartServlet")
@MultipartConfig
public class EditPartServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final SparePartService sparePartService = new SparePartService();
    private final BrandService brandService = new BrandService();
    private final CategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String action = request.getParameter("action");

    	if("edit".equals(action)){
    		try {
    			
    			//System.out.println("Received id: " + request.getParameter("id"));

    			
	            int id = Integer.parseInt(request.getParameter("id"));
	
	            SparePart sparePart = sparePartService.getSparePartById(id);
	            List<Brand> brands = brandService.getAllBrands();
	            List<Category> categories = categoryService.getAllCategories();
	
	            request.setAttribute("part", sparePart);
	            request.setAttribute("brands", brands);
	            request.setAttribute("categories", categories);
	
	            request.getRequestDispatcher("Admin/edit-part.jsp").forward(request, response);
	        } catch (Exception e) {
	        	
	        	e.printStackTrace();
	        	response.sendRedirect("Admin/error.jsp");
	        }
    	}
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            double price = Double.parseDouble(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            int brandId = Integer.parseInt(request.getParameter("brand_id"));
            int categoryId = Integer.parseInt(request.getParameter("category_id"));
            String description = request.getParameter("description");

            // Validation
            if (price <= 1500) {
                request.setAttribute("error", "Price must be greater than 1500 LKR.");
                response.sendRedirect("edit-part.jsp?id=" + id + "&error=price");
                return;
            }

            if (description == null || description.trim().length() <= 20) {
                request.setAttribute("error", "Description must be more than 20 characters.");
                response.sendRedirect("edit-part.jsp?id=" + id + "&error=desc");
                return;
            }

            // Auto stock status
            String status;
            if (quantity == 0) {
                status = "OutOfStock";
            } else if (quantity >= 5 && quantity <= 15) {
                status = "LowStock";
            } else {
                status = "InStock";
            }

            // Retain existing image
            SparePart existing = sparePartService.getSparePartById(id);
            String image = existing != null ? existing.getImage() : "";

            SparePart updatedSparePart = new SparePart(id, name, price, image, description, brandId, categoryId, quantity, status);
            boolean success = sparePartService.updateSparePart(updatedSparePart);

            if (success) {
                response.sendRedirect("products?success=true");
            } else {
                response.sendRedirect("edit-part.jsp?id=" + id + "&error=true");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

}
