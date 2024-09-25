package com.HealthCareDemo.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.HealthCareDemo.dao.AdminDao;
import com.HealthCareDemo.dao.BookingDao;
import com.HealthCareDemo.dao.DoctorDao;
import com.HealthCareDemo.model.Doctor;
import com.HealthCareDemo.model.Patient;


@Controller
public class DoctorController 
{
	//1) verify doctor email to re-direct to Registration page
     @RequestMapping("verifyEmail")
	 public ModelAndView DoctorRegistration(String email) // it simplifies our code or else /*public ModelAndView verifyEmail(HttpServletRequest request)
	{
                                                                                                               // String email = request.getParameter("email");  
        boolean exists = AdminDao.checkDoctorEmailExists(email);
	    ModelAndView mv = new ModelAndView();

	    if (exists)
	    {
	        mv.setViewName("DoctorRegistration.jsp");
	    }
	    else
	    {
	        mv.addObject("verifyMessage", "Email address not found. Please check and try again.");
	        mv.setViewName("VerifyDocEmail.jsp");
	    }

	    return mv;
	}

	 // Inserting Doctor Details
	 @RequestMapping("insertdoctor")
	 // 1] Reading data from FrontENd 
	 public ModelAndView insertDoctorDetails(HttpServletRequest request) // it will return all data in string format
	 {
		String n = request.getParameter("docName");
		String e = request.getParameter("docEmail");
	    long m = Long.parseLong(request.getParameter("mobile"));
		String pass = request.getParameter("password");
        String gen = request.getParameter("gender");
        String prof = request.getParameter("profession");

			    
	    Doctor d = new Doctor(n,e,m,pass,gen,prof); //2] read data  store it in model
	    // 1st Check if email already exists
	    boolean exists = DoctorDao.checkDoctorEmailExists(e);	    
	     ModelAndView mv = new ModelAndView();
	     if (exists)
	     {
	         mv.addObject("verifyMessage", "Email is already registered.");
	         mv.setViewName("DoctorRegistration.jsp");
	     }
	     // if email not found duplicate then insert new record into table
	     else
	     {
	         DoctorDao.insertDoctorDetails(d);
	         mv.addObject("verifyMessage", "Registration successful.");//verifyMessage is an attribute added in requestObject
	         mv.addObject("showBackToLogin", true); // Indicate successful registration
	         mv.setViewName("DoctorRegistration.jsp");
	     }
		return mv;
	    
	   
	 }
	// Checking doctor login and redirect to "welcomeDoctor.jsp" page
	 @RequestMapping("doctor")
	 public ModelAndView checkDoctorCredentials(String email,String password,HttpSession session)
	 {
		 boolean isAuthenticated = DoctorDao.checkDoctorLogin(email, password);
	        ModelAndView mv = new ModelAndView();  
	        if (isAuthenticated) 
	        {
	        	 // Get doctorId from the database using email
	            Integer doctorId = DoctorDao.getDoctorIdByEmail(email);
	            
	            // Store doctorId in the session
	            session.setAttribute("doctorId", doctorId);
	            mv.setViewName("WelcomeDoctor.jsp"); // Redirect to welcomeDoctor.jsp on successful login
	        }
	        else 
	        {
	            mv.addObject("DoctorloginError", "Invalid email or password. Please try again.");
	            mv.setViewName("Doctor.jsp"); // Redirect to login.jsp on failed login
	        } 
		
		return mv;
		 
	 }
	 
	    // Method to get patients by doctor ID
	 @RequestMapping("getPatientDetails")
	 public ModelAndView getPatientDetails(HttpSession session) {
	     // Get doctorId from session
	     Integer doctorId = (Integer) session.getAttribute("doctorId");

	     // If doctorId is null, handle the case
	     if (doctorId == null) {
	         ModelAndView mv = new ModelAndView("errorPage.jsp");
	         mv.addObject("message", "You must log in first.");
	         return mv;
	     }

	     // Fetch patient details using doctorId
	     List<Patient> patients = new BookingDao().getPatientsByDoctorId(doctorId);
	     
	     ModelAndView mv = new ModelAndView("BookedPatientDetails.jsp");
	     mv.addObject("patients", patients); // Pass the list of patients to the view
	     return mv;
	 }

	}
	 
//Yes, even though they are different methods, the session allows you to share data between them.
//When you store the doctorId in the session during the login process in the checkDoctorCredentials method,
// that doctorId remains available in the session for subsequent requests.
