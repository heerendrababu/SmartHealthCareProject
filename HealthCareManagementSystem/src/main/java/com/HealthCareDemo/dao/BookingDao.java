package com.HealthCareDemo.dao;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.HealthCareDemo.model.Patient;

public class BookingDao {

	private static String dbUrl = "jdbc:mysql://localhost:3306/practiceproject";
	private static String dbUsername = "root";
	private static String dbPassword = "Babu@123";

	// Method to insert booking data into the database
	public boolean insertBooking(int doctorId, int patientId, String bookingDate) {
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
			String insertQuery = "insert into Booking (doctorId, patientId, bookingDate) VALUES (?, ?, ?)";
			ps = cn.prepareStatement(insertQuery);
			ps.setInt(1, doctorId);
			ps.setInt(2, patientId);
			
			// Convert String to java.sql.Date
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        java.util.Date parsedDate = sdf.parse(bookingDate);
	        java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
	        ps.setDate(3, sqlDate);
	        
			int rowsAffected = ps.executeUpdate();
			return rowsAffected > 0; // Return true if the insertion is successful
		} catch (ClassNotFoundException | SQLException | ParseException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (cn != null)
					cn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false; // Return false if there was an error
	}
//----------------------------------------------------------------------------------------------------	

	// Method to get patients by doctor ID
	public List<Patient> getPatientsByDoctorId(int doctorId)
	{
	    List<Patient> patients = new ArrayList<>();
	   /// It's best to write the SQL query outside the try block for better readability and clearer error handling.
	    // This way, if there's a problem with the query, it's easier to identify. 
	    
	    String query = "select p.pid, p.pname, p.age, p.bloodGroup, p.email, p.mobile " +
	               "from Booking b "
	               + "join Patient p on b.patientId = p.pid " + 
	               "where b.doctorId = ?";


	    Connection cn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        cn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
	        ps = cn.prepareStatement(query);
	        ps.setInt(1, doctorId);
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            Patient patient = new Patient(); // Create a new Patient object
	            patient.setPid(rs.getInt("pid")); // Set patient ID
	            patient.setPname(rs.getString("pname")); // Set patient name
	            patient.setAge(rs.getInt("age")); // Set patient age
	            patient.setBloodGroup(rs.getString("bloodGroup")); // Set blood group
	            patient.setEmail(rs.getString("email")); // Set email
	            patient.setMobno(rs.getLong("mobile")); // Set mobile number
	            patients.add(patient); // Add patient to the list
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); // Consider logging instead of printing
	    } finally {
	        try {
	            if (rs != null) rs.close(); // Close ResultSet
	            if (ps != null) ps.close(); // Close PreparedStatement
	            if (cn != null) cn.close(); // Close Connection
	        } catch (SQLException e) {
	            e.printStackTrace(); // Handle exception during closing
	        }
	    }
	    return patients; // Return the list of patients
	}
}
   

