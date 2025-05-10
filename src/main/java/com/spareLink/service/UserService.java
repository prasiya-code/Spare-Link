package com.spareLink.service;

import com.spareLink.model.User;
import com.spareLink.util.DBConnector;

import java.sql.*;

public class UserService {

    public boolean registerUser(User user) {
        if (emailExists(user.getEmail())) {
            System.out.println("Email already exists in the database.");
            return false;
        }

        String sql = "INSERT INTO users (full_name, email, password) VALUES (?, ?, ?)";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getFullName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());

            int rows = stmt.executeUpdate();
            System.out.println("Inserted rows: " + rows);
            return rows > 0;

        } catch (Exception e) {
            System.err.println("Database insertion error: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    private boolean emailExists(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            return rs.next();

        } catch (Exception e) {
            System.err.println("Email check failed: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean validateUser(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // True if user exists
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}



