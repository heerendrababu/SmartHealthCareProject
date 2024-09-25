<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Dashboard</title>
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
    .admin-container {
        background-color: white;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        text-align: center;
    }
    input[type="submit"] {
        width: 100%;
        padding: 8px;
        margin: 5px 0;
        box-sizing: border-box;
        border: 1px solid lightgray;
        border-radius: 4px;
        background-color: forestgreen;
        color: white;
        border: none;
        cursor: pointer;
    }
    input[type="submit"]:hover {
        background-color: mediumseagreen;
    }
    h2 {
        text-align: center;
        color: darkslategray;
    }
</style>
</head>
<body>
   <div class="admin-container">
       <h2>Welcome Admin</h2>
       <form action="getDataToEdit" method="post">
           <input type="submit" name="getdoctorslist" value="Show Doctors List" />
       </form>
       <form action="Dverify.jsp" method="post">
           <input type="submit" name="Ddetails" value="Add Doctors Details" />
       </form>
   </div>
</body>
</html>
