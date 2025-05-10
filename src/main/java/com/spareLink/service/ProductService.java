//package com.spareLink.service;
//
//import com.spareLink.util.ConnectionData;
//import com.spareLink.model.Product;
//import com.spareLink.util.DBConnector;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ProductService {
//
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();

        String sql = "SELECT p.id, p.name, p.image, p.description, p.price, " +
                     "b.name AS brand_name, c.name AS category_name " +
                     "FROM spare_parts p " +
                     "JOIN brands b ON p.brand_id = b.id " +
                     "JOIN categories c ON p.category_id = c.id";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setImage(rs.getString("image"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setBrandName(rs.getString("brand_name"));
                product.setCategoryName(rs.getString("category_name"));
                productList.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }

//    public Product getProduct(int id) {
//        String query = "SELECT * FROM spare_parts WHERE id = ?";
//        try (Connection connection = DBConnector.getConnection();
//             PreparedStatement stmt = connection.prepareStatement(query)) {
//            stmt.setInt(1, id);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                Product product = new Product();
//                product.setId(rs.getInt("id"));
//                product.setName(rs.getString("name"));
//                product.setImage(rs.getString("image"));
//                product.setDescription(rs.getString("description"));
//                product.setPrice(rs.getDouble("price"));
//                product.setQuantity(rs.getInt("quantity"));
//                product.setStatus(rs.getString("status"));
//                product.setBrandId(rs.getInt("brand_id"));
//                product.setCategoryId(rs.getInt("category_id"));
//                return product;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    // ✅ ADD THIS METHOD for updating the product
//    public boolean updateProduct(Product product) {
//        String sql = "UPDATE spare_parts SET name = ?, price = ?, quantity = ?, status = ?, brand_id = ?, category_id = ?, description = ? WHERE id = ?";
//
//        try (Connection conn = DBConnector.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//
//            stmt.setString(1, product.getName());
//            stmt.setDouble(2, product.getPrice());
//            stmt.setInt(3, product.getQuantity());
//            stmt.setString(4, product.getStatus());
//            stmt.setInt(5, product.getBrandId());
//            stmt.setInt(6, product.getCategoryId());
//            stmt.setString(7, product.getDescription());
//            stmt.setInt(8, product.getId());
//
//            int rowsAffected = stmt.executeUpdate();
//            return rowsAffected > 0;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//}
