package com.spareLink.service;

import com.spareLink.model.Support;
import com.spareLink.util.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupportService {

    // Insert data function
    public static boolean insertdata(String issueType, String fullName, String email, String contactNo, String inquiry) {
        boolean isSuccess = false;
        try {
        	Connection conn = DBConnector.getConnection();
            String sql = "INSERT INTO support (issueType, fullName, email, contactNo, inquiry) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, issueType);
            ps.setString(2, fullName);
            ps.setString(3, email);
            ps.setString(4, contactNo);
            ps.setString(5, inquiry);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                isSuccess = true;
            }
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    // GetById Function
    public static List<Support> getById(String Id) {
        List<Support> supportList = new ArrayList<>();
        try {
            int convertedID = Integer.parseInt(Id);
            Connection conn = DBConnector.getConnection();
            String sql = "SELECT * FROM support WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, convertedID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int supportId = rs.getInt("id");
                String issueType = rs.getString("issueType");
                String fullName = rs.getString("fullName");
                String email = rs.getString("email");
                String contactNo = rs.getString("contactNo");
                String inquiry = rs.getString("inquiry");

                Support sup = new Support(supportId, issueType, fullName, email, contactNo, inquiry);
                supportList.add(sup);
            }
            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return supportList;
    }

    // GetAll Data Function
    public static List<Support> getAllSupport() {
        List<Support> supports = new ArrayList<>();
        try {
        	Connection conn = DBConnector.getConnection();
            String sql = "SELECT * FROM support";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int supportId = rs.getInt("supportId");
                String issueType = rs.getString("issueType");
                String fullName = rs.getString("fullName");
                String email = rs.getString("email");
                String contactNo = rs.getString("contactNo");
                String inquiry = rs.getString("inquiry");

                Support sup = new Support(supportId, issueType, fullName, email, contactNo, inquiry);
                supports.add(sup);
            }
            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return supports;
    }

    // Update Data Function
    public static boolean updatedata(String supportId, String issueType, String fullName, String email, String contactNo, String inquiry) {
        boolean isSuccess = false;
        
        try {
        	Connection conn = DBConnector.getConnection();
            String sql = "UPDATE support SET issueType = ?, fullName = ?, email = ?, contactNo = ?, inquiry = ? WHERE supportId = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, issueType);
            ps.setString(2, fullName);
            ps.setString(3, email);
            ps.setString(4, contactNo);
            ps.setString(5, inquiry);
            ps.setInt(6, Integer.parseInt(supportId));

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                isSuccess = true;
            }
            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
    
    public static boolean deleteSupport(String supportId) {
        boolean isSuccess = false;

        try {
        	Connection conn = DBConnector.getConnection();
            String sql = "DELETE FROM support WHERE supportId = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(supportId));

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                isSuccess = true;
            }

            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isSuccess;
    }

    
   
}