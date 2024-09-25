<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.HealthCareDemo.model.Patient" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Booked Patient Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: lightgray;
        }
        h2 {
            text-align: center;
            color: darkblue;
        }
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: white;
        }
        th, td {
            border: 1px solid black;
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: lightblue;
            color: black;
        }
        td {
            background-color: lightyellow;
        }
        tr:nth-child(even) {
            background-color: lightcyan;
        }
        button {
            background-color: darkgreen;
            color: white;
            border: none;
            padding: 8px 12px;
            cursor: pointer;
        }
        button:hover {
            background-color: green;
        }
    </style>
</head>
<body>
    <h2>Booked Patient Details</h2>
    
    <table>
        <thead>
            <tr>
                <th>Patient Name</th>
                <th>Age</th>
                <th>Blood Group</th>
                <th>Email</th>
                <th>Mobile Number</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <% 
            List<Patient> patients = (List<Patient>) request.getAttribute("patients");
            if (patients != null && !patients.isEmpty()) {
                for (Patient patient : patients) {
            %>
                <tr>
                    <td><%= patient.getPname() %></td>
                    <td><%= patient.getAge() %></td>
                    <td><%= patient.getBloodGroup() %></td>
                    <td><%= patient.getEmail() %></td>
                    <td><%= patient.getMobno() %></td>
                    <td>
                        <form action="ReportForm.jsp" method="GET">
                             <input type="hidden" name="patientId" value=" <%= patient.getPid() %> "> 
                            <button type="submit">Send Report</button>
                        </form>
                    </td>
                </tr>
            <% 
                }
            } else { 
            %>
                <tr>
                    <td colspan="6">No patients found.</td>
                </tr>
            <% 
            } 
            %>
        </tbody>
    </table>
</body>
</html>
