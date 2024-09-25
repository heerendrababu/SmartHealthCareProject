<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Report Status</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: lightgreen; }
        h2 { color: darkgreen; text-align: center; }
    </style>
</head>
<body>
    <h2>
        <%
            String message = (String) request.getAttribute("message");
            if (message != null) {
                out.println(message);
            } else {
                out.println("No message available.");
            }
        %>
    </h2>
    <p style="text-align: center;">
        <a href="yourRedirectURL">Go back</a> <!-- Replace with your actual redirect URL -->
    </p>
</body>
</html>
