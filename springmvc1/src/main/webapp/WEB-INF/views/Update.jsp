<%@page import="java.util.List"%>
<%@page import="com.jspider.springmvc1.pojo.EmployeePojo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="NavBar.jsp" />
<%
EmployeePojo employee1 = (EmployeePojo) request.getAttribute("emp");
String msg = (String) request.getAttribute("msg");
List<EmployeePojo> employee = (List<EmployeePojo>) request.getAttribute("emplist");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
form {
	margin-top: 10px;
}

form table {
	margin: auto;
	width: 100%;
}

tr {
	text-align: center;
}

fieldset table {
	margin: auto;
	text-align: left;
}

fieldset {
	margin: 15px 520px;
	text-align: center;
}

legend {
	color: white;
	background-color: #333;
}

body {
	background-image:
		url('https://www.xmple.com/wallpaper/linear-blue-white-highlight-gradient-1920x1080-c2-ffffff-e0ffff-l-50-a-165-f-21.svg');
	background-size: 100%;
}

#data {
	background-color: white;
	border: 1px solid black;
	width: 100%;
	border: 1px solid black;
}

#data td {
	border: 1px solid black;
	text-align: center;
}
</style>
</head>
<body>
	<div>
		<%
		if (employee1!= null) {
		%>
		<form action="./updateData" method="post">
			<fieldset>
				<legend>:::Update Employee</legend>
				<table>
					<tr hidden="true">
						<td>ID</td>
						<td><input type="text" name="id"
							value="<%=employee1.getId() %>"></td>
					</tr>
					<tr>
						<td>Name</td>
						<td><input type="text" name="name"
							value="<%=employee1.getNameString() %>"></td>
					</tr>
					<tr>
						<td>Email</td>
						<td><input type="text" name="email"
							value="<%=employee1.getEmail() %>"></td>
					</tr>
					<tr>
						<td>Contact</td>
						<td><input type="text" name="contact"
							value="<%=employee1.getContact() %>"></td>
					</tr>
					<tr>
						<td>Designation</td>
						<td><input type="text" name="designation"
							value="<%=employee1.getDesignation() %>"></td>
					</tr>
					<tr>
						<td>Salary</td>
						<td><input type="text" name="salary"
							value="<%=employee1.getSalary() %>"></td>
					</tr>

				</table>
				<input type="submit" value="Update">
				</fieldset>
			</form>
			<%
			if (msg != null) {
			%>
			<h3><%=msg%></h3>

			<%
			}
			%>

			<%
			if (employee != null) {
			%>
			<table id="data">
				<thead>
					<tr>
						<td>ID</td>
						<td>Name</td>
						<td>Email</td>
						<td>Contact</td>
						<td>Designation</td>
						<td>Salary</td>


					</tr>

				</thead>
				<%
				for (EmployeePojo pojo : employee) {
				%>
				<tr>
					<td><%=pojo.getId()%></td>
					<td><%=pojo.getNameString()%></td>
					<td><%=pojo.getEmail()%></td>
					<td><%=pojo.getContact()%></td>
					<td><%=pojo.getDesignation()%></td>
					<td><%=pojo.getSalary()%></td>
				</tr>
				<%
				}
				%>

			</table>

			<%
			}
			%>

		<%
		} else {
		%>
		<form action="./update" method="post">
			<fieldset>
				<legend>:::Select Employee:::</legend>
				<table>
					<tr>
						<td>Empoyee ID</td>
						<td><input type="text" name="id"></td>
					</tr>
				</table>
				<input type="submit" value="Select">
			</fieldset>
			<%
			if (msg != null) {
			%>
			<h3><%=msg%></h3>

			<%
			}
			%>

			<%
			if (employee != null) {
			%>
			<table id="data">
				<thead>
					<tr>
						<td>ID</td>
						<td>Name</td>
						<td>Email</td>
						<td>Contact</td>
						<td>Designation</td>
						<td>Salary</td>


					</tr>

				</thead>
				<%
				for (EmployeePojo pojo : employee) {
				%>
				<tr>
					<td><%=pojo.getId()%></td>
					<td><%=pojo.getNameString()%></td>
					<td><%=pojo.getEmail()%></td>
					<td><%=pojo.getContact()%></td>
					<td><%=pojo.getDesignation()%></td>
					<td><%=pojo.getSalary()%></td>
				</tr>
				<%
				}
				%>

			</table>

			<%
			}
			%>
		</form>

		<%
		}
		%>
	</div>
	
			

</body>
</html>