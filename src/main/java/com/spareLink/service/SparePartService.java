package com.spareLink.service;

import com.spareLink.model.SparePart;
import com.spareLink.util.DBConnector;
import com.spareLink.model.Category;
import com.spareLink.model.Brand;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SparePartService {

    public SparePart getSparePartById(int id) {
        String query = "SELECT * FROM spare_parts WHERE id = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new SparePart(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getString("image"),
                    rs.getString("description"),
                    rs.getInt("brand_id"),
                    rs.getInt("category_id"),
                    rs.getInt("quantity"),
                    rs.getString("status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateSparePart(SparePart part) {
        String sql = "UPDATE spare_parts SET name = ?, price = ?, quantity = ?, status = ?, brand_id = ?, category_id = ?, description = ? WHERE id = ?";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, part.getName());
            stmt.setDouble(2, part.getPrice());
            stmt.setInt(3, part.getQuantity());
            stmt.setString(4, part.getStatus());
            stmt.setInt(5, part.getBrandId());
            stmt.setInt(6, part.getCategoryId());
            stmt.setString(7, part.getDescription());
            stmt.setInt(8, part.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<SparePart> getAllSpareParts() {
        List<SparePart> spareParts = new ArrayList<>();

        String sql = "SELECT p.id, p.name, p.image, p.description, p.price, p.quantity, p.status, " +
                     "p.brand_id, b.name AS brand_name, " +
                     "p.category_id, c.name AS category_name " +
                     "FROM spare_parts p " +
                     "JOIN brands b ON p.brand_id = b.id " +
                     "JOIN categories c ON p.category_id = c.id";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                SparePart part = new SparePart();
                
                part.setId(rs.getInt("id"));
                part.setName(rs.getString("name"));
                part.setImage(rs.getString("image"));
                part.setDescription(rs.getString("description"));
                part.setPrice(rs.getDouble("price"));
                part.setQuantity(rs.getInt("quantity"));
                part.setStatus(rs.getString("status"));
                part.setBrandId(rs.getInt("brand_id"));
                part.setCategoryId(rs.getInt("category_id"));
                part.setBrandName(rs.getString("brand_name"));
                part.setCategoryName(rs.getString("category_name"));

                spareParts.add(part);
                
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return spareParts;
    }
    
    public boolean deleteSparePartById(int id) {
        try (Connection conn = DBConnector.getConnection()) {
            String sql = "DELETE FROM spare_parts WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

        public int getTotalParts() {
            int total = 0;
            try (Connection con = DBConnector.getConnection();
                 Statement stmt = con.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM spare_parts")) {
                if (rs.next()) total = rs.getInt(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return total;
        }

        public int getLowStockCount() {
            int count = 0;
            try (Connection con = DBConnector.getConnection();
                 Statement stmt = con.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM spare_parts WHERE quantity <= 5")) {
                if (rs.next()) count = rs.getInt(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return count;
        }

        public int getTotalCategories() {
            int count = 0;
            try (Connection con = DBConnector.getConnection();
                 Statement stmt = con.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM categories")) {
                if (rs.next()) count = rs.getInt(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return count;
        }
        
        public int getTotalUsers() {
            int count = 0;
            try (Connection con = DBConnector.getConnection();
                 Statement stmt = con.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM users")) {
                if (rs.next()) count = rs.getInt(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return count;
        }

}
