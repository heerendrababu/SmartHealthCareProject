<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="com.HealthCareDemo.model.Doctor"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Doctor List to Edit</title>
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

table {
	width: 100%;
	border-collapse: collapse;
	margin-bottom: 20px;
	background-color: white;
}

th, td {
	padding: 10px;
	text-align: left;
	border-bottom: 1px solid lightgray;
}

th {
	background-color: lightblue;
}

tr:hover {
	background-color: lightyellow;
}

.edit-button {
	background-color: green;
	color: white;
	border: none;
	padding: 5px 10px;
	cursor: pointer;
	border-radius: 4px;
}

.edit-button:hover {
	background-color: darkgreen;
}

.hidden {
	display: none;
}
</style>
</head>
<body>
	<h2>Doctor List</h2>
	<table>
		<tr>

			<th>Name</th>
			<th>Email</th>
			<th>Mobile</th>
			<th>Action</th>
		</tr>
		<%
		// Assuming 'doctorList' is populated in the request
		List<Doctor> doctorList = (List<Doctor>) request.getAttribute("doctorList");
		for (Doctor doctor : doctorList) {
		%>
		<tr>
			<td class="hidden"><%=doctor.getDid()%></td>
			<!-- Hidden ID -->
			<td><%=doctor.getName()%></td>
			<td><%=doctor.getDemail()%></td>
			<td><%=doctor.getDmob()%></td>
			<td>
				<form action="editDoctor" method="post">
					<input type="hidden" name="did" value="<%=doctor.getDid()%>" />
					<!-- Hidden input for ID -->
					<input type="submit" class="edit-button" value="Edit" />
				</form>

			</td>
		</tr>
		<%
		}
		%>
	</table>
</body>
</html>
