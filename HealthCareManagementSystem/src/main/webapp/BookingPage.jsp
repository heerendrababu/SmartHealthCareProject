<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.HealthCareDemo.model.Doctor" %>
<%@ page import="javax.servlet.http.HttpSession" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Booking Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: lightgray;
            padding: 20px;
        }
        .doctor-list {
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            color: darkslategray;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid lightgray;
            padding: 12px;
            text-align: left;
        }
        th {
            background-color: dodgerblue;
            color: white;
            font-weight: bold;
        }
        tr:nth-child(even) {
            background-color: lightyellow;
        }
        tr:hover {
            background-color: lightblue;
        }
        .date-input {
            padding: 5px;
            border: 1px solid gray;
            border-radius: 4px;
            width: 100%;
        }
        input[type="submit"] {
            background-color: mediumseagreen;
            color: white;
            border: none;
            padding: 10px 15px;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        input[type="submit"]:hover {
            background-color: forestgreen;
        }
        .exit-button {
            background-color: tomato;
            color: white;
            border: none;
            padding: 10px 15px;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .exit-button:hover {
            background-color: red;
        }
        .error-message {
            color: red;
            margin-top: 5px;
        }
    </style>
</head>
<body>
    <div class="doctor-list">
        <h2>Available Doctors</h2>
        <table>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Mobile</th>
                    <th>Profession</th>
                    <th>Select Date</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <%
                    Integer patientId = (Integer) request.getSession().getAttribute("loggedInPatientId"); 
                    @SuppressWarnings("unchecked")
                    List<Doctor> doctors = (List<Doctor>) (List<?>) request.getAttribute("doctors");
                    String bookingFail = (String) request.getAttribute("BookingFail");

                    if (doctors != null && !doctors.isEmpty()) {
                        for (Doctor doctor : doctors) {
                %>
                    <tr>
                        <td><%= doctor.getName() %></td>
                        <td><%= doctor.getDemail() %></td>
                        <td><%= doctor.getDmob() %></td>
                        <td><%= doctor.getDprofess() %></td>
                        <td>
                            <form action="<%= request.getContextPath() %>/confirmBooking" method="post">
                                <input type="hidden" name="doctorId" value="<%= doctor.getDid() %>"/>
                                <input type="hidden" name="patientId" value="<%= patientId %>"/>
                                <input type="date" name="bookingDate" class="date-input" required />
                        </td>
                        <td>
                                <input type="submit" value="Book This Doctor"/>
                            </form>
                            <% if (bookingFail != null) { %>
                                <div class="error-message">
                                    <%= bookingFail %>
                                </div>
                            <% } %>
                        </td>
                    </tr>
                <% 
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="6">No doctors available.</td>
                    </tr>
                <% 
                    }
                %>
            </tbody>
        </table>
    </div>

    <div style="text-align:center; margin-top:20px;">
        <form action="WelcomeUser.jsp" method="get">
            <input type="submit" class="exit-button" value="Exit" />
        </form>
    </div>
</body>
</html>
