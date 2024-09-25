package com.HealthCareDemo.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.HealthCareDemo.dao.DoctorDao;
import com.HealthCareDemo.dao.BookingDao; 
import com.HealthCareDemo.model.Doctor;

@Controller
public class BookingController
{
//1)-------------------------------------------------------------------------------------------------------------------------------
//    @RequestMapping("bookingDoctor")
//    public ModelAndView showBookingPage() 
//    {
//        List<Doctor> doctors = DoctorDao.getAllDoctorDetails(); // Fetch doctor details from the database
//        
//        ModelAndView mv = new ModelAndView();
//        mv.addObject("doctors", doctors); // Pass doctors to the JSP
//        mv.setViewName("BookingPage.jsp"); // Use view name without .jsp
//        return mv;
//    }
    

//2)---------------------------------------------------------------------------------------------------------------------------------
 /*   @RequestMapping(value = "confirmBooking", method = RequestMethod.POST)
    public ModelAndView confirmBooking(@RequestParam("doctorId") int doctorId, @RequestParam("bookingDate") String bookingDate) 
    {
        Doctor doctor = DoctorDao.getDoctorById(doctorId);
        
        ModelAndView mv = new ModelAndView();
        mv.addObject("doctorName", doctor.getName());
        mv.addObject("bookingDate", bookingDate); // Pass booking date to the confirmation page
        mv.setViewName("BookingConfirmation"); // Use view name without .jsp
        return mv;
    }*/
// --------------------------------------------------------------------------------------------------------------------------------------------------------------    
// 3)   
   // Confirm the booking by inserting data into the database
    @RequestMapping(value = "/confirmBooking", method = RequestMethod.POST)
    public ModelAndView confirmBooking(@RequestParam("doctorId") int doctorId,
                                       @RequestParam("patientId") int patientId,
                                       @RequestParam("bookingDate") String bookingDate)
    {
    	 // Manually create an instance of BookingDao
        BookingDao bookingDao = new BookingDao(); 

        // Call the DAO to insert the booking data
        boolean isBooked = bookingDao.insertBooking(doctorId, patientId, bookingDate);

        ModelAndView mv = new ModelAndView();
        if (isBooked) 
        {
            mv.setViewName("BookingConfirmation.jsp"); // Redirect to the success page
            mv.addObject("bookingDate", bookingDate); // Pass the booking date
        } 
        else
        {
            mv.addObject("BookingFail","Booking Failed Try again");
            mv.setViewName("BookingPage.jsp");// Redirect to the failure page
        }
        return mv;
    }
    
    // exit code which will keep all doctor data in session for getting doctor details again 
    @RequestMapping(value = "loadBookingPage", method = RequestMethod.GET)
    public ModelAndView loadBookingPage(HttpSession session)  // 1)Session used to access user-specific data 
    {                                                         // 2)HttpSession parameter, as it's not needed to access the doctor data. 
    	                                                     //The doctors will still be fetched from the database and passed to the view.

        // Retrieve the list of doctors
        List<Doctor> doctors = DoctorDao.getAllDoctorDetails(); // Replace with your method to fetch doctors

        // Add the list of doctors to the model so it can be accessed in the JSP
        ModelAndView mv = new ModelAndView("BookingPage.jsp");
        mv.addObject("doctors", doctors); // Set the list of doctors in the model

        // Return the ModelAndView object to render the JSP with the doctor data
        return mv;
    }


//    // Show the report form pre-filled with patient data using patientId
//    @RequestMapping("/reportForm")
//    public ModelAndView showReportForm(@RequestParam("patientId") int patientId) {
//        Patient patient = new BookingDao().getPatientById(patientId); // Fetch patient by ID
//        
//        ModelAndView mv = new ModelAndView("ReportForm.jsp");
//        mv.addObject("patient", patient); // Pass patient details to the form
//        return mv;
//    } 
//----------------------------------------------------------------------------------------------------------------------------------------------------------------   
   // 1) By clicking bookDoctor button it will Re-Direct user to BookingPage.jsp 
 // Note: You can access the same session across different controllers in a Spring application.
    // When a user logs in through the PatientController, the patientId is stored in the session. 
    //Later, when the BookingController is accessed, you can retrieve that same patientId from the session.
    @RequestMapping("bookingDoctor")
    public ModelAndView showBookingPage(HttpSession session) 
    {
        List<Doctor> doctors = DoctorDao.getAllDoctorDetails();
        int patientId = (int) session.getAttribute("loggedInPatientId"); // Retrieve patientId from session

        ModelAndView mv = new ModelAndView();
        mv.addObject("doctors", doctors);
        mv.addObject("patientId", patientId); // Pass patientId to JSP
        mv.setViewName("BookingPage.jsp");
        return mv;
    }

}

