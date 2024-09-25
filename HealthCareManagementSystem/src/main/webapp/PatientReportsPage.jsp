<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.HealthCareDemo.model.Report" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Your Reports</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: lightgray; /* Background color */
            margin: 20px;
        }
        h2 {
            text-align: center;
            color: darkslategray; /* Heading color */
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
            background-color: white; /* Table background color */
        }
        th, td {
            padding: 10px;
            text-align: left;
            border: 1px solid lightgray; /* Cell border color */
        }
        th {
            background-color: mediumseagreen; /* Header background color */
            color: white; /* Header text color */
        }
        tr:nth-child(even) {
            background-color: lightyellow; /* Even row color */
        }
        tr:hover {
            background-color: lightgray; /* Hover row color */
        }
        td[colspan="4"] {
            text-align: center;
            color: gray; /* No reports text color */
        }
    </style>
</head>
<body>
    <h2>Your Reports</h2>
    <table>
        <tr>
            <th>Patient Name</th>
            <th>Email</th>
            <th>Blood Group</th>
            <th>Description</th>
        </tr>
        <%
            List<Report> reports = (List<Report>) request.getAttribute("reports");
            if (reports != null && !reports.isEmpty()) {
                for (Report report : reports) {
        %>
        <tr>
             <td><%= report.getPatientName() %></td>
            <td><%= report.getPatientEmail() %></td>
            <td><%= report.getBloodGroup() %></td>
            <td><%= report.getDescription() %></td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="4">No reports found.</td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>
