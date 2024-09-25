<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome User</title>
<style>
    body {
        font-family: Arial, sans-serif;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
        background-color: lightgray;
    }
    .container {
        background-color: white;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        text-align: center;
    }
    h2 {
        color: black;
    }
    input[type="submit"] {
        width: 200px;
        padding: 10px;
        margin: 10px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        font-size: 16px;
    }
    input[type="submit"].book-doctor {
        background-color: green;
        color: white;
    }
    input[type="submit"].book-doctor:hover {
        background-color: darkgreen;
    }
    input[type="submit"].see-reports {
        background-color: blue;
        color: white;
    }
    input[type="submit"].see-reports:hover {
        background-color: navy;
    }
</style>
</head>
<body>
<div class="container">
    <h2>Welcome User</h2>
    <form action="bookingDoctor" method="post">
        <input type="submit" class="book-doctor" name="bookDoctor" value="Book Doctor"/>
           </form>
        
        <form action="viewReports" method="post">
    <input type="submit" class="see-reports" name="seeReports" value="See Reports"/>
</form>
        
</div>
</body>
</html>
