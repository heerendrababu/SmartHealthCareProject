<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.HealthCareDemo.model.Doctor" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Edit Doctor</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: lightgray;
            margin: 0;
            padding: 20px;
        }
        h2 {
            color: darkblue;
        }
        .form-container {
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            margin: 0 auto;
        }
        input[type="text"], input[type="email"] {
            width: 100%;
            padding: 10px;
            border: 1px solid lightgray;
            border-radius: 4px;
            margin-bottom: 10px;
            font-size: 14px;
        }
        input[type="submit"] {
            background-color: blue;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            width: 100%;
            font-size: 16px;
        }
        input[type="submit"]:hover {
            background-color: darkblue;
        }
        .message {
            margin-bottom: 10px;
            font-weight: bold;
            color: green; /* Change color to green for success */
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>Edit Doctor Details</h2>

        <!-- Display a success/error message, if any -->
        <%
            String message = (String) request.getAttribute("message");
            if (message != null) {
        %>
            <div class="message"><%= message %></div>
        <%
            }
        %>

        <form action="updateDoctor" method="post">
            <!-- Hidden ID -->
            <input type="hidden" name="did" value="<%= ((Doctor) request.getAttribute("doctor")).getDid() %>" /> 

            <!-- Name field with a placeholder -->
            <input type="text" name="name" placeholder="Doctor Name" value="<%= ((Doctor) request.getAttribute("doctor")).getName() %>" required />

            <!-- Email field with a placeholder -->
            <input type="email" name="email" placeholder="Doctor Email" value="<%= ((Doctor) request.getAttribute("doctor")).getDemail() %>" required />

            <!-- Mobile field with a placeholder -->
            <input type="text" name="mobile" placeholder="Doctor Mobile" value="<%= ((Doctor) request.getAttribute("doctor")).getDmob() %>" required />

            <!-- Submit button -->
            <input type="submit" value="Update" />
        </form>
    </div>
</body>
</html>
