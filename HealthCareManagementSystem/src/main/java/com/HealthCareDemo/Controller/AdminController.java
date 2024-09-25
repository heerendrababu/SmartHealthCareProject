package com.HealthCareDemo.Controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.HealthCareDemo.model.Doctor;
import com.HealthCareDemo.model.DocVerify;
import com.HealthCareDemo.dao.AdminDao;
import com.HealthCareDemo.dao.DoctorDao;

@Controller
public class AdminController 
{
    @RequestMapping("adminlogin")
    public ModelAndView insertAdminDetails(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String e = request.getParameter("tbEmail"); 
        String p = request.getParameter("tbPass");

        boolean result = AdminDao.checkAdminLogin(e, p);

        if (result) {
            // If admin details are correct, use the same "Admin.jsp" page but indicate that the admin is logged in.
        	mv.setViewName("WelcomeAdmin.jsp");                                                                                    // mv.addObject("isLoggedIn", true); // Add this attribute to indicate the login status.  
        } else {
            mv.addObject("ErrorMessage", "False information, please enter the right credentials.");
            mv.setViewName("Admin.jsp");
        }
        return mv;
    }
    
    // Entering doctor mail_id's & names
	 @RequestMapping(value = "adminpage" , params = "Ddetails")
    public ModelAndView DocVerify(HttpServletRequest req)
    {
    	ModelAndView mv = new ModelAndView();
    	 // mv.addObject("Dverify",dverify); // getAttribute uses("variable", object);
		  mv.setViewName("Dverify.jsp");
		return mv;	
    }
	  @RequestMapping("addDoctorDetails")
	    public ModelAndView addDoctorDetails(HttpServletRequest request) {
	        ModelAndView mv = new ModelAndView();
	        String name = request.getParameter("doctorName");
	        String email = request.getParameter("doctorEmail");

	        // Check if email already exists
	        if (AdminDao.checkDoctorEmailExists(email)) {
	            // If email exists, set error message and forward to JSP page
	            mv.addObject("errorMessage", "Email already exists. Please use a different email.");
	            mv.setViewName("Dverify.jsp");
	        } 
	        else
	        {
	            // Insert doctor details into the database
	            AdminDao.insertDoctorVerifyDetails(name, email);
	            // Set success message and forward to JSP page
	            mv.addObject("successMessage", "Doctor details inserted successfully.");
	            mv.setViewName("Dverify.jsp");
	        }

	        return mv;
	    }
// --------------------------------------------------------------------------------------------------------------------------------------------------------
	// Method to get the list of doctors
	// 1) Edit Doctor Data by Admin
	    @RequestMapping("getDataToEdit")
	    public ModelAndView getDataToEdit() {
	        ModelAndView mv = new ModelAndView();
	        
	        // Fetch doctor details from the database
	        // getAllDoctorDetails method is static so need to create object for Doctor class
	        ArrayList<Doctor> doctorList = DoctorDao.getDoctorDetailsToEdit();
	        
	        // Add the list to the model
	        mv.addObject("doctorList", doctorList);
	        mv.setViewName("DoctorListToEdit.jsp"); // Redirect to doctorList.jsp page
	        return mv;
	    }
//---------------------------------------------------------------------------------------------------------------------------------------------------------
	   // it will give data to form EditDoctor.jsp page 
	    @RequestMapping("editDoctor")
	    public ModelAndView editDoctor(HttpServletRequest request) {
	        ModelAndView mv = new ModelAndView();
	        int doctorId = Integer.parseInt(request.getParameter("did"));
	        Doctor doctor = DoctorDao.getDoctorById(doctorId); // Fetch the doctor by ID

	        mv.addObject("doctor", doctor); // Add the doctor object to the model
	        mv.setViewName("EditDoctorData.jsp"); // Forward to the edit page
	        return mv;
	    }

//----------------------------------------------------------------------------------------------------------------------------------------------------------
	    // 2) update Doctor Data
	 // 2) Update Doctor Data by Admin
	    @RequestMapping("updateDoctor")
	    public ModelAndView updateDoctor(HttpServletRequest request) {
	        ModelAndView mv = new ModelAndView();

	        // Read parameters from the request
	        int did = Integer.parseInt(request.getParameter("did"));
	        String name = request.getParameter("name");
	        String email = request.getParameter("email");
	        long mobile = Long.parseLong(request.getParameter("mobile"));

	        // 1) Create a new Doctor object with the updated details
	        Doctor docUpdate = new Doctor(did, name, email, mobile);

	        // 2) Update doctor details in the database
	        boolean isUpdated = DoctorDao.updateDoctorDetails(docUpdate);
	        if (isUpdated)
	        {
	            mv.addObject("message", "Doctor details updated successfully."); // Set the success message
	        } else
	        {
	            mv.addObject("message", "Failed to update doctor details.");
	        }

	        // Fetch updated doctor details to display them in the form
	        mv.addObject("doctor", docUpdate);
	        mv.setViewName("EditDoctorData.jsp"); // Redirect to the same JSP page
	        return mv;
	    }
}

//	        if (isUpdated)
//	        {
//	            // Optionally, update DocVerify table if needed (same as in your original code)
//                DocVerify docVerifyUpdate = new DocVerify(did, name, email);
//                boolean isDocVerifiedUpdated = AdminDao.updateDocVerifyDetails(docVerifyUpdate);
//	            if (isDocVerifiedUpdated) 
//	            {
//	                mv.addObject("message", "Doctor details updated successfully.");
//	            } else 
//	            {
//	                mv.addObject("message", "Doctor details updated, but verification record failed.");
//	            }
//	        } else {
//	            mv.addObject("message", "Failed to update doctor details.");
//	        }
//
//	        // Redirect to the doctor list page or edit page as needed
//	        mv.setViewName("EditDoctorData.jsp");
//	        return mv;
//	    }
//	}

// Note: The static method is called without needing an instance of DoctorDao.
// 1) It retrieves data from the database using SQL queries.
// 2) It creates and populates Doctor objects from the retrieved data and adds them to a list for further processing or returning to the caller.
