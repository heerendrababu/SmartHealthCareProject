package com.HealthCareDemo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.HealthCareDemo.model.DocVerify;

public class AdminDao 
{
	private static String dbUrl = "jdbc:mysql://localhost:3306/practiceproject";
	private static String dbUsername = "root";
	private static String dbPassword = "Babu@123";

	private static Connection cn = null;
	private static PreparedStatement ps = null;
	private static Statement st = null;
	private static ResultSet rs = null;

	// (1) Check if admin login details are correct
	public static boolean checkAdminLogin(String e, String p) {

		try
		{
            // 1) Load Driver class
			Class.forName("com.mysql.cj.jdbc.Driver");
            // 2) Establish connection to DB
			cn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            // 3) Prepare Sql statement ( used to store sql queries)
			st = cn.createStatement();
            // 4) Execute query
			ResultSet rs = st.executeQuery("select * from admin");

			if (rs != null)
			{
				while (rs.next()) 
				{
					String dbemail = rs.getString(2);
					String dbpass = rs.getString(3);
					if (dbemail.equals(e) && dbpass.equals(p))
					{
						return true; // Admin login success
					}
				}
			}
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (cn != null)
					cn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return false; // Admin login failure
	}

	// (1) Check if doctor email already exists
    public static boolean checkDoctorEmailExists(String email) {
        boolean exists = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            
            String query = "select demail from DocVerify where demail = ?";
            ps = cn.prepareStatement(query);
            ps.setString(1, email); // `email` is the method parameter
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
    

    // (2) Insert doctor details into the database
    public static void insertDoctorVerifyDetails(String name, String email)
    {
        Connection cn = null;
        PreparedStatement ps = null;
 // No need to create statement because to insert data we will use prepare statement, so for creating table also we can use PrepareStatement to decrease code. 

        try 
        {
            // 1) Load Driver class
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // 2) Establish connection to DB
            cn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            
            // Ensure the DocVerify table exists before inserting data
            String createTableSQL = "create table if not exists DocVerify ("
                    + "did int primary key auto_increment, "
                    + "dname varchar(40) not null, "
                    + "demail varchar(40) not null unique)";
            // 3) Prepare Sql statement ( used to store sql queries)
            ps = cn.prepareStatement(createTableSQL);
            // 4) Execute query
            ps.executeUpdate();  // Use PreparedStatement to execute the create table query
            
            // Insert doctor details
            String query = "insert into DocVerify (dname, demail) values (?, ?)";
            ps = cn.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } 
        // 5) Close Connection
        finally // Deleting objects in RAM
        {
            try {
                if (ps != null) ps.close(); // destroy or close the query
                if (cn != null) cn.close(); // close connection that nobody can enter into RAM and take data 
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
   // Update DocVerify details By Admin
    public static boolean updateDocVerifyDetails(DocVerify docVerify) {
       
        boolean isUpdated = false;

        String query = "update DocVerify set dname = ?, demail = ? where did = ?";

        try {
            // 1) Get connection to DB
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

            // 2) Prepare the SQL update statement
            ps = cn.prepareStatement(query);
            ps.setString(1, docVerify.getName());
            ps.setString(2, docVerify.getEmail());
            ps.setInt(3, docVerify.getDid());

            // 3) Execute the update query
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                isUpdated = true; // Success if at least one row is affected
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            // 4) Close resources
            try {
                if (ps != null) ps.close();
                if (cn != null) cn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isUpdated;
    }
}
    



