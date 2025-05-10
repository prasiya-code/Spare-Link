package com.spareLink.service;

import com.spareLink.model.Brand;
import com.spareLink.util.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BrandService {

    public List<Brand> getAllBrands() {
        List<Brand> brandList = new ArrayList<>();

        String sql = "SELECT id, name FROM brands";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Brand brand = new Brand();
                brand.setId(rs.getInt("id"));
                brand.setName(rs.getString("name"));
                brandList.add(brand);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return brandList;
    }
}

