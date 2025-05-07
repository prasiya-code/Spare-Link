//package com.spareLink.servlet;
//
//import com.spareLink.model.TestSparePart;
//import com.spareLink.service.TestSparePartService;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.MultipartConfig;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.*;
//import java.io.File;
//import java.io.IOException;
//
//@WebServlet("/AddSparePartTestServlet")
//@MultipartConfig
//public class AddSparePartTestServlet extends HttpServlet {
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String name = request.getParameter("name");
//        double price = Double.parseDouble(request.getParameter("price"));
//        String description = request.getParameter("description");
//        int brandId = Integer.parseInt(request.getParameter("brandId"));
//        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
//
//        // Handle image upload
//        Part filePart = request.getPart("image");
//        String contentDisp = filePart.getHeader("content-disposition");
//        String[] items = contentDisp.split(";");
//        String fileName = "";
//        for (String s : items) {
//            if (s.trim().startsWith("filename")) {
//                fileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
//            }
//        }
//
//        String uploadPath = getServletContext().getRealPath("") + File.separator + "images";
//        File uploadDir = new File(uploadPath);
//        if (!uploadDir.exists()) uploadDir.mkdir();
//
//        filePart.write(uploadPath + File.separator + fileName);
//        String imagePath = "images/" + fileName;
//
//        // Create object and save
//        TestSparePart part = new TestSparePart(name, price, imagePath, description, brandId, categoryId);
//        TestSparePartService service = new TestSparePartService();
//        boolean success = service.addSparePart(part);
//
//        if (success) {
//            response.sendRedirect("test-add-spare-part.jsp?success=true");
//        } else {
//            response.sendRedirect("test-add-spare-part.jsp?error=true");
//        }
//    }
//}


package com.spareLink.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

import com.spareLink.util.DBConnector;

@WebServlet("/AddSparePartTestServlet")
@MultipartConfig
public class AddSparePartTestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String priceStr = request.getParameter("price");
        String description = request.getParameter("description");
        String brandIdStr = request.getParameter("brandId");
        String categoryIdStr = request.getParameter("categoryId");

        double price = Double.parseDouble(priceStr);
        int brandId = Integer.parseInt(brandIdStr);
        int categoryId = Integer.parseInt(categoryIdStr);

        Part filePart = request.getPart("image");

        // Step 1: Insert the record without the image path
        int generatedId = -1;
        try (Connection conn = DBConnector.getConnection()) {
            String insertSql = "INSERT INTO spare_parts (name, price, image, description, brand_id, category_id) VALUES (?, ?, '', ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, name);
            stmt.setDouble(2, price);
            stmt.setString(3, description);
            stmt.setInt(4, brandId);
            stmt.setInt(5, categoryId);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    generatedId = generatedKeys.getInt(1);
                }
            } else {
                response.sendRedirect("test-add-spare-part.jsp?error=true");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("test-add-spare-part.jsp?error=true");
            return;
        }

        // Step 2: Save the image file with the generated ID as the name
        if (generatedId > 0 && filePart != null && filePart.getSize() > 0) {
            String uploadDir = request.getServletContext().getRealPath("/images");
            File imageDir = new File(uploadDir);
            if (!imageDir.exists()) {
                imageDir.mkdirs();
            }

            String imageName = generatedId + ".jpg";
            String fullPath = uploadDir + File.separator + imageName;
            filePart.write(fullPath);

            String imagePath = "images/" + imageName;

            // Step 3: Update the record with the image path
            try (Connection conn = DBConnector.getConnection()) {
                String updateSql = "UPDATE spare_parts SET image = ? WHERE id = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setString(1, imagePath);
                updateStmt.setInt(2, generatedId);
                updateStmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect("test-add-spare-part.jsp?error=true");
                return;
            }
        }

        response.sendRedirect("test-add-spare-part.jsp?success=true");
    }
}
