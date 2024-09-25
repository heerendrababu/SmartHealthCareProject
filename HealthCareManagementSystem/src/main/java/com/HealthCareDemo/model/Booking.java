package com.HealthCareDemo.model;



	public class Booking 
	{
	    private int bookingId;
	    private int doctorId;
	    private int patientId;
	    private String bookingDate;

	    // Constructors
	    public Booking() {}

	    public Booking(int bookingId, int doctorId, int patientId, String bookingDate) {
	        this.bookingId = bookingId;
	        this.doctorId = doctorId;
	        this.patientId = patientId;
	        this.bookingDate = bookingDate;
	    }
	    
	    // Getters and Setters
	    public int getBookingId() {
	        return bookingId;
	    }

	    public void setBookingId(int bookingId) {
	        this.bookingId = bookingId;
	    }

	    public int getDoctorId() {
	        return doctorId;
	    }

	    public void setDoctorId(int doctorId) {
	        this.doctorId = doctorId;
	    }

	    public int getPatientId() {
	        return patientId;
	    }

	    public void setPatientId(int patientId) {
	        this.patientId = patientId;
	    }

	    public String getBookingDate() {
	        return bookingDate;
	    }

	    public void setBookingDate(String bookingDate) {
	        this.bookingDate = bookingDate;
	    }
	}


