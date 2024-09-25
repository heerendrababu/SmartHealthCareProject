<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Send Report</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: lightgray;
            margin: 0;
            padding: 20px;
        }
        h2 {
            text-align: center;
            color: darkblue;
        }
        form {
            background-color: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            margin: auto;
        }
        form div {
            margin: 10px 0;
        }
        label {
            display: block;
            margin-bottom: 5px;
            color: black;
        }
        input[type="text"],
        input[type="email"],
        textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid darkgray;
            border-radius: 3px;
            box-sizing: border-box;
        }
        textarea {
            height: 100px;
        }
        button {
            background-color: darkgreen;
            color: white;
            border: none;
            padding: 10px 15px;
            cursor: pointer;
            border-radius: 3px;
            width: 100%;
        }
        button:hover {
            background-color: green;
        }
    </style>
</head>
<body>
    <h2>Send Report</h2>
    <form action="sendReport" method="POST">
        <div>
            <label for="pname">Patient Name:</label>
            <input type="text" id="pname" name="pname" required>
        </div>
        <div>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
        </div>
        <div>
            <label for="bloodGroup">Blood Group:</label>
            <input type="text" id="bloodGroup" name="bloodGroup" required>
        </div>
        <div>
            <label for="description">Description:</label>
            <textarea id="description" name="description" required></textarea>
        </div>
        <button type="submit">Send Report</button>
    </form>
</body>
</html>
