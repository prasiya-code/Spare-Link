package com.spareLink.service;

import com.spareLink.model.Product;
import com.spareLink.util.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
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
}
