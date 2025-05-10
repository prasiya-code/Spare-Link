package com.spareLink.servlet;

import com.spareLink.model.Brand;
import com.spareLink.model.Category;
import com.spareLink.service.BrandService;
import com.spareLink.service.CategoryService;
import com.spareLink.util.DBConnector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.List;

@MultipartConfig
@WebServlet("/add-part-form")
public class AddPartFormServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final BrandService brandService = new BrandService();
    private final CategoryService categoryService = new CategoryService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try (Connection conn = DBConnector.getConnection()) {
            // Read form inputs
            String name = request.getParameter("name");
            double price = Double.parseDouble(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String status = request.getParameter("status");
            int brandId = Integer.parseInt(request.getParameter("brand_id"));
            int categoryId = Integer.parseInt(request.getParameter("category_id"));
            String description = request.getParameter("description");
            Part filePart = request.getPart("image");

            // Step 1: Insert product without image
            int productId = 0;
            String insertSql = "INSERT INTO spare_parts (name, price, quantity, status, brand_id, category_id, description, image) VALUES (?, ?, ?, ?, ?, ?, ?, '')";
            PreparedStatement stmt = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, name);
            stmt.setDouble(2, price);
            stmt.setInt(3, quantity);
            stmt.setString(4, status);
            stmt.setInt(5, brandId);
            stmt.setInt(6, categoryId);
            stmt.setString(7, description);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                productId = rs.getInt(1);
            }
            rs.close();
            stmt.close();

            // Step 2: Save image as productId.jpg
            if (productId > 0 && filePart != null && filePart.getSize() > 0) {
                String uploadDir = request.getServletContext().getRealPath("/images");
                File imageDir = new File(uploadDir);
                if (!imageDir.exists()) imageDir.mkdirs();

                String imageName = productId + ".jpg";
                String fullPath = uploadDir + File.separator + imageName;
                filePart.write(fullPath);

                String dbPath = "images/" + imageName;
                PreparedStatement updateStmt = conn.prepareStatement("UPDATE spare_parts SET image = ? WHERE id = ?");
                updateStmt.setString(1, dbPath);
                updateStmt.setInt(2, productId);
                updateStmt.executeUpdate();
                updateStmt.close();
            }

            //Redirect to product list after success
            response.sendRedirect(request.getContextPath() + "/products");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/add-part-form?error=true");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Brand> brands = brandService.getAllBrands();
        List<Category> categories = categoryService.getAllCategories();

        request.setAttribute("brands", brands);
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("Admin/add-part.jsp").forward(request, response);
    }
}
