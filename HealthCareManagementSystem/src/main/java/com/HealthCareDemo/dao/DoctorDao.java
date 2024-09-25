package com.HealthCareDemo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.HealthCareDemo.model.Doctor;

public class DoctorDao {

    private static String dbUrl = "jdbc:mysql://localhost:3306/practiceproject";
    private static String dbUsername = "root";
    private static String dbPassword = "Babu@123";

    // Check Email Exists or Not For Verification Purpose
    public static boolean checkDoctorEmailExists(String email) 
    {
        boolean exists = false;
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            String query = "SELECT demail FROM Doctor WHERE demail = ?";
            ps = cn.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();

            if (rs.next()) {
                exists = true; // Email already exists
            }
        } catch (ClassNotFoundException | SQLException e) {
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

        return exists;
    }

 // Insert Doctor Details
    public static void insertDoctorDetails(Doctor d) {
        Connection cn = null;
        PreparedStatement ps = null;
        Statement st = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            st = cn.createStatement();

            // Create table if it does not exist
            String createTableQuery = "CREATE TABLE IF NOT EXISTS doctor(did INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(40), demail VARCHAR(40) UNIQUE, dmob BIGINT UNIQUE, dpass VARCHAR(40), gender VARCHAR(40), dprofess VARCHAR(40))";
            st.executeUpdate(createTableQuery);

            // Insert doctor details
            String insertQuery = "INSERT INTO doctor (name, demail, dmob, dpass, gender, dprofess) VALUES (?, ?, ?, ?, ?, ?)";
            ps = cn.prepareStatement(insertQuery);

            // Set values from the Doctor object
            ps.setString(1, d.getName());
            ps.setString(2, d.getDemail());
            ps.setLong(3, d.getDmob());
            ps.setString(4, d.getDpass());
            ps.setString(5, d.getGender());
            ps.setString(6, d.getDprofess());

            // Execute insert query
            ps.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (st != null) st.close();
                if (cn != null) cn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
// Check Doctor Login
    public static boolean checkDoctorLogin(String email, String password) {
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            st = cn.createStatement();
            rs = st.executeQuery("SELECT * FROM Doctor");

            while (rs.next()) {
                String demail = rs.getString("demail");
                String dpass = rs.getString("dpass");
                if (demail.equals(email) && dpass.equals(password)) {
                    return true; // Doctor login success
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (cn != null) cn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
// For Booking Information
    public static ArrayList<Doctor> getAllDoctorDetails() // The methods in Dao layer class creates the objects which will send to Controller with method call from controller.
    {
        ArrayList<Doctor> al = new ArrayList<Doctor>();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            st = cn.createStatement();
            
            // Specify the columns explicitly
            String query = "select did, name, demail, dmob, dpass, gender, dprofess from Doctor";
            rs = st.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("did");
                String name = rs.getString("name");
                String email = rs.getString("demail");
                long mobile = rs.getLong("dmob");
                String password = rs.getString("dpass");
                String gender = rs.getString("gender");
                String profession = rs.getString("dprofess");
  // If you don't create the Doctor object, you wouldn't have a way to represent or store the data fetched from the database in a structured manner.
                Doctor doctor = new Doctor(id, name, email, mobile, password, gender, profession);  // For example, if the ResultSet contains three rows, the DAO will create three Doctor objects:
                al.add(doctor);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // Consider logging instead of printing
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (cn != null) cn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return al;
    }
    
    // for Editing of Doctor Data by Admin
    public static ArrayList<Doctor> getDoctorDetailsToEdit()
    {
        ArrayList<Doctor> al = new ArrayList<Doctor>();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            st = cn.createStatement();
            
            // Specify only the necessary columns
            String query = "select did, name, demail, dmob from Doctor"; // Excluded dpass, gender, and dprofess
            rs = st.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("did");
                String name = rs.getString("name");
                String email = rs.getString("demail");
                long mobile = rs.getLong("dmob");
                
                // Create Doctor object without password, gender, and profession
                Doctor doctor = new Doctor(id, name, email, mobile); // Adjusted constructor
                al.add(doctor);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // Consider logging instead of printing
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (cn != null) cn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return al;
    }

    
    // for BookingData Purpose
    public static Doctor getDoctorById(int doctorId) {
        Doctor doctor = null;
        Connection cn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Doctor WHERE did = ?";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            pstmt = cn.prepareStatement(query);
            pstmt.setInt(1, doctorId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                doctor = new Doctor();
                doctor.setDid(rs.getInt("did"));
                doctor.setName(rs.getString("name"));
                doctor.setDemail(rs.getString("demail"));
                doctor.setDmob(rs.getLong("dmob"));
                doctor.setDprofess(rs.getString("dprofess"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (cn != null) cn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return doctor;
    }



 // Method to get doctorId by email
    public static Integer getDoctorIdByEmail(String email) {
        Integer doctorId = null;
        Connection cn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String query = "SELECT did FROM Doctor WHERE demail = ?";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            pstmt = cn.prepareStatement(query);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                doctorId = rs.getInt("did");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (cn != null) cn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return doctorId; // Returns doctorId or null if not found
    }
 //----------------------------------------------------------------------------------------------------------------------------------------------------------
  // Update Doctor Data By Admin
 // Method to update doctor details (excluding dpass, dprofess, and gender)
    public static boolean updateDoctorDetails(Doctor doctor)
    {
        Connection cn = null;
        PreparedStatement ps = null;

        String query = "UPDATE Doctor SET name = ?, demail = ?, dmob = ? WHERE did = ?";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            ps = cn.prepareStatement(query);

            // Set values from the Doctor object
            ps.setString(1, doctor.getName());
            ps.setString(2, doctor.getDemail());
            ps.setLong(3, doctor.getDmob());
            ps.setInt(4, doctor.getDid()); // ID of the doctor to be updated

            int rowsAffected = ps.executeUpdate(); // Check how many rows were affected
            return rowsAffected > 0; // Return true if at least one row was updated
        } catch (ClassNotFoundException | SQLException e) {
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
 //---------------------------------------------------------------------------------------------------------------------------------------------------------
    // Delete Doctor data based on Id
    public static void deleteDoctor(int doctorId) {
        Connection cn = null;
        PreparedStatement ps = null;

        String query = "DELETE FROM Doctor WHERE did = ?";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            ps = cn.prepareStatement(query);
            ps.setInt(1, doctorId); // ID of the doctor to be deleted

            // Execute delete query
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (cn != null) cn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

	public static boolean updateDocVerifyDetails(int did, String name, long mobile) {
		// TODO Auto-generated method stub
		return false;
	}
    }
/* generally we create object in controller right or it is the right way ? 
 * which way is better when i want to getall details and send to controller and later browser give simple answer ?
Ans:
1)It's generally better to create objects in the DAO. This keeps the controller focused on handling requests and responses, 
  while the DAO manages data access. 
  2)The controller can then simply receive the list of objects from the DAO and pass them to the view.*/
