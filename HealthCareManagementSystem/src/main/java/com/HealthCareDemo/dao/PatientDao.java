package com.HealthCareDemo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.HealthCareDemo.model.Doctor;
import com.HealthCareDemo.model.Patient;

public class PatientDao 
{
	  private static String  dbUrl = "jdbc:mysql://localhost:3306/practiceproject";
	    private static String dbUsername = "root";
	    private static String dbPassword = "Babu@123";

	    private static Connection cn = null;
	    private static PreparedStatement ps = null;
	    private static Statement st = null;
	    private static ResultSet rs = null;
	    
	    public static void insertPatientDetails(Patient p) {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            cn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
	            st = cn.createStatement();

	            // Create table if it does not exist
	            String createTableQuery = "create table if not exists patient(pid int primary key auto_increment,pname varchar(40),age int,"
	            		+ "bloodGroup varchar(40),email varchar(40),"
	            		+ "mobile bigint,password varchar(40))";
	            		
	                   
	            st.executeUpdate(createTableQuery);

	            // Insert doctor details
	            String insertQuery = "insert into patient (pname, age, bloodGroup,email,mobile,password) VALUES (?, ?, ?, ?, ?, ?)";
	            ps = cn.prepareStatement(insertQuery);

	            // Set values from the Doctor object
	            ps.setString(1, p.getPname());
	            ps.setInt(2, p.getAge());
	            ps.setString(3, p.getBloodGroup());
	            ps.setString(4, p.getEmail());
	            ps.setLong(5, p.getMobno());
	            ps.setString(6, p.getPassword());
	            
	            // Execute insert query
	            ps.executeUpdate();

	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        } finally
	        {
	            // Close resources
	            if (ps != null) {
	                try {
	                    ps.close();
	                } catch (SQLException e1) {
	                    e1.printStackTrace();
	                }
	            }
	            if (st != null) {
	                try {
	                    st.close();
	                } catch (SQLException e1) {
	                    e1.printStackTrace();
	                }
	            }
	            if (cn != null) {
	                try {
	                    cn.close();
	                } catch (SQLException e1)
	                {
	                    e1.printStackTrace();
	                }
	            }
	        }
	    }  
   
  
	    // check patient Email-id to tell already registered if data is stored in database
	     
		    public static boolean checkPatientEmailExists(String email) {
		        boolean exists = false;
		        try {
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            cn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		            
		            String query = "select email from patient where email = ?";
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
		   
  // 3) checking Patient login credentials
		  public static boolean checkPatientLogin(String email,String password)
		  {
			  try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					cn = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
					st = cn.createStatement();
					rs = st.executeQuery("select*from Patient");
					
					if (rs != null)
					{
						while (rs.next()) 
						{
							/*String dbemail = rs.getString("email");
							String dbpass = rs.getString("password");*/
		                             //(or)
							String dBemail = rs.getString(5);
							String dBpass = rs.getString(7);
							if (dBemail.equals(email) && dBpass.equals(password))
							{
								return true; // Admin login success
							}
						}
					}
					
				} 
		    	catch (ClassNotFoundException | SQLException e) 
		    	{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return false;
		    	
		     }
		  
		  // Method to retrieve patient ID (pid) based on email
		    public static int getPatientId(String email)
		    {
		        Connection cn = null;
		        PreparedStatement ps = null;
		        ResultSet rs = null;
		        int patientId = -1; // Initialize patientId to -1, indicating not found

		        try {
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            cn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

		            String query = "SELECT pid FROM Patient WHERE email = ?";
		            ps = cn.prepareStatement(query);
		            ps.setString(1, email);
		            rs = ps.executeQuery();

		            if (rs.next()) {
		                patientId = rs.getInt("pid"); // Retrieve pid as patientId
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
		        return patientId; // Return the patientId or -1 if not found
		    }
		    public static Patient getPatientById(int patientId) {
		        Patient patient = null;
		        Connection cn = null;
		        PreparedStatement ps = null;
		        ResultSet rs = null;

		        try {
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            cn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

		            String query = "SELECT * FROM patient WHERE pid = ?";
		            ps = cn.prepareStatement(query);
		            ps.setInt(1, patientId);
		            rs = ps.executeQuery();

		            if (rs.next()) {
		                // Create a new Patient object and set its properties
		                patient = new Patient();
		                patient.setPid(rs.getInt("pid"));
		                patient.setPname(rs.getString("pname"));
		                patient.setAge(rs.getInt("age"));
		                patient.setBloodGroup(rs.getString("bloodGroup"));
		                patient.setEmail(rs.getString("email"));
		                patient.setMobno(rs.getLong("mobile"));
		                patient.setPassword(rs.getString("password"));
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
		        return patient; // Return the Patient object or null if not found
		    }


	    }
