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
    
    public boolean insertBrand(String name) {
    	String sql = "INSERT INTO brands (name) VALUES (?)";
    	
    	try(Connection conn = DBConnector.getConnection();
    		PreparedStatement stmt = conn.prepareStatement(sql)){
    		
    		stmt.setString(1, name);
    		int rowInserted = stmt.executeUpdate();
    		
    		return rowInserted > 0;
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return false;
    }
    
    public boolean brandExists(String name) {
        String sql = "SELECT COUNT(*) FROM brands WHERE LOWER(name) = LOWER(?)";
        
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

