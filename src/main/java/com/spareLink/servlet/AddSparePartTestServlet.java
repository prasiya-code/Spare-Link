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
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.spareLink.util.DBConnector;

@WebServlet("/AddSparePartTestServlet")
@MultipartConfig
public class AddSparePartTestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set encoding
        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String priceStr = request.getParameter("price");
        String description = request.getParameter("description");
        String brandIdStr = request.getParameter("brandId");
        String categoryIdStr = request.getParameter("categoryId");

        double price = Double.parseDouble(priceStr);
        int brandId = Integer.parseInt(brandIdStr);
        int categoryId = Integer.parseInt(categoryIdStr);

        // Step 1: Get image part
        Part filePart = request.getPart("image");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        // Step 2: Resolve path to /images in webapp
        String uploadDir = request.getServletContext().getRealPath("/images");
        File imageDir = new File(uploadDir);
        if (!imageDir.exists()) {
            imageDir.mkdirs(); // Create /images if not exists
        }

        // Step 3: Save file to /images
        String filePath = uploadDir + File.separator + fileName;
        filePart.write(filePath);

        // Step 4: Save relative path for DB (not full path)
        String imagePath = "images/" + fileName;

        // Step 5: Insert into database
        try (Connection conn = DBConnector.getConnection()) {
            String sql = "INSERT INTO spare_parts (name, price, image, description, brand_id, category_id) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setDouble(2, price);
            stmt.setString(3, imagePath);
            stmt.setString(4, description);
            stmt.setInt(5, brandId);
            stmt.setInt(6, categoryId);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                response.sendRedirect("test-add-spare-part.jsp?success=true");
            } else {
                response.sendRedirect("test-add-spare-part.jsp?error=true");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("test-add-spare-part.jsp?error=true");
        }
    }
}
