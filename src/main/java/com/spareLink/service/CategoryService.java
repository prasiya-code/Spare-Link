package com.spareLink.service;

import com.spareLink.model.Category;
import com.spareLink.util.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryService {

    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();

        String sql = "SELECT id, name FROM categories";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                categoryList.add(category);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categoryList;
    }
    
    public boolean insertCategory(String name) {
        String sql = "INSERT INTO categories (name) VALUES (?)";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            int rowsInserted = stmt.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean categoryExists(String name) {
        String sql = "SELECT COUNT(*) FROM categories WHERE LOWER(name) = LOWER(?)";
        
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, name.trim());
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }

    
    
}
