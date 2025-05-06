package com.spareLink.service;

import com.spareLink.model.TestSparePart;
import com.spareLink.util.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TestSparePartService {

    public boolean addSparePart(TestSparePart part) {
        String sql = "INSERT INTO spare_parts (name, price, image, description, brand_id, category_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, part.getName());
            stmt.setDouble(2, part.getPrice());
            stmt.setString(3, part.getImage());
            stmt.setString(4, part.getDescription());
            stmt.setInt(5, part.getBrandId());
            stmt.setInt(6, part.getCategoryId());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
