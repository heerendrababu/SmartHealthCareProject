package com.HealthCareDemo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.HealthCareDemo.model.Report;

public class ReportDao {
	private static String dbUrl = "jdbc:mysql://localhost:3306/practiceproject";
	private static String dbUsername = "root";
	private static String dbPassword = "Babu@123";
	
	   private static Connection cn = null;
	    private static PreparedStatement ps = null;
       private  static ResultSet rs = null;

	// Method to save report to the database
	public static boolean saveReport(Report report) {
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        cn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
	        String sql = "INSERT INTO Report (patientName, patientEmail, bloodGroup, description) VALUES (?, ?, ?, ?)";
            ps = cn.prepareStatement(sql);
            ps.setString(1, report.getPatientName());
            ps.setString(2, report.getPatientEmail());
            ps.setString(3, report.getBloodGroup());
            ps.setString(4, report.getDescription());
            int rows = ps.executeUpdate();
            return rows > 0; // Return true if the report was saved
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found Exception: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (cn != null) cn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
	

//	 2)  *  Method to retrieve reports by patient email
    public static List<Report> getReportsByEmail(String email) {
        List<Report> reports = new ArrayList<>();
       

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            String sql = "SELECT * FROM report WHERE patientEmail = ?";
            ps = cn.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();

            while (rs.next()) {
                Report report = new Report();
                report.setPatientName(rs.getString("patientName"));
                report.setPatientEmail(rs.getString("patientEmail"));
                report.setBloodGroup(rs.getString("bloodGroup"));
                report.setDescription(rs.getString("description")); // Adjust according to your Report model
                reports.add(report);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (cn != null) cn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return reports;
    }


}