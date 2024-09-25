<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Booking Confirmation</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: lightgray;
            padding: 20px;
        }
        .confirmation {
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        .button {
            background-color: green;
            color: white;
            border: none;
            padding: 10px 15px;
            border-radius: 5px;
            cursor: pointer;
            margin-top: 20px;
        }
        .button:hover {
            background-color: darkgreen;
        }
    </style>
</head>
<body>
    <div class="confirmation">
        <h2>Booking Confirmation</h2>
        <p>Your appointment has been successfully booked!</p>
        <p><strong>Doctor:</strong> <%= request.getAttribute("doctorName") %></p>
        <p><strong>Date:</strong> <%= request.getAttribute("bookingDate") %></p>
    
    <div style="text-align: center; margin-top: 20px;">
        <form action="loadBookingPage" method="get">
        <input type="submit" class="button" value="Exit" />
    </form>
</div>

 </div>
</body>
</html>
