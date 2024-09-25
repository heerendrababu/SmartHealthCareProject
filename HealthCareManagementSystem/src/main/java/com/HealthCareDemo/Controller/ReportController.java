package com.HealthCareDemo.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.HealthCareDemo.dao.PatientDao;  // Import your DAO class
import com.HealthCareDemo.dao.ReportDao;   // Import your DAO class
import com.HealthCareDemo.model.Patient;
import com.HealthCareDemo.model.Report;

@Controller
public class ReportController {

//    // Show the report form pre-filled with patient data using patientId
//    @RequestMapping(value = "reportForm", method = RequestMethod.GET)
//    public ModelAndView showReportForm(@RequestParam("patientId") int patientId) {
//        Patient patient = PatientDao.getPatientId(patientId); // Fetch patient by ID
//
//        ModelAndView mv = new ModelAndView("ReportForm"); // Use view name without .jsp
//        mv.addObject("patient", patient); // Pass patient details to the form
//        return mv;
//    }

    // Handle the report submission
	// Handle the report submission
    @RequestMapping(value = "sendReport", method = RequestMethod.POST)
    public ModelAndView sendReport(
            @RequestParam("pname") String pname,
            @RequestParam("email") String email,
            @RequestParam("bloodGroup") String bloodGroup,
            @RequestParam("description") String description) {

        Report report = new Report();
        report.setPatientName(pname);
        report.setPatientEmail(email);
        report.setBloodGroup(bloodGroup);
        report.setDescription(description);

        boolean reportSaved = ReportDao.saveReport(report);

        ModelAndView mv = new ModelAndView("Success.jsp");
        if (reportSaved) {
            mv.addObject("message", "Report sent successfully!");
        } else {
            mv.addObject("message", "Failed to send report.");
        }
        return mv;
    }
    }































//package com.HealthCareDemo.Controller;
//
//import javax.servlet.http.HttpServletRequest;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.HealthCareDemo.dao.PatientDao; // Import your PatientDao
//import com.HealthCareDemo.model.Patient;
//import com.HealthCareDemo.dao.ReportDao; // Assume you have a ReportDao for handling report storage
//
//@Controller
//public class ReportController {
//
//    // Show the report form pre-filled with patient data using patientId
//    @RequestMapping(value = "reportForm", method = RequestMethod.GET)
//    public ModelAndView showReportForm(@RequestParam("patientId") int patientId) {
//        Patient patient = PatientDao.getPatientId(patientId); // Fetch patient by ID
//
//        ModelAndView mv = new ModelAndView("ReportForm.jsp"); // Use view name without .jsp
//        mv.addObject("patient", patient); // Pass patient details to the form
//        return mv;
//    }
//
//    // Handle the report submission
//    @RequestMapping(value = "sendReport", method = RequestMethod.POST)
//    public ModelAndView sendReport(@RequestParam("patientId") int patientId,
//                                    @RequestParam("description") String description) {
//        // Logic to save the report to the database
//        boolean reportSaved = ReportDao.saveReport(patientId, description); // Call to save the report
//
//        ModelAndView mv = new ModelAndView("ReportSuccess"); // Redirect to a success page
//        if (reportSaved) {
//            mv.addObject("message", "Report sent successfully!"); // Pass a success message
//        } else {
//            mv.addObject("message", "Failed to send report."); // Pass an error message
//        }
//        return mv;
//    }
//}
