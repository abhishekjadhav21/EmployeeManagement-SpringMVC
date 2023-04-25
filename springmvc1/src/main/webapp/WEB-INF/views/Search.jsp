<%@page import="com.jspider.springmvc1.pojo.EmployeePojo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="NavBar.jsp" />
<%
String msg = (String) request.getAttribute("msg");
EmployeePojo employee = (EmployeePojo) request.getAttribute("emp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div>
		<form action="./search" method="post">
			<fieldset>
				<legend>:::Search Employee:::</legend>
				<table>
					<tr>
						<td>Empoyee ID</td>
						<td><input type="text" name="id"></td>
					</tr>
				</table>
				<input type="submit" value="Search">
			</fieldset>
		</form>
	</div>
	<%
	if (msg != null) {
	%>
	<%=msg%>
	<%
	}
	%>
	<%
	if (employee != null) {
	%>
	<table>
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
		<tr>
			<td><%=employee.getId()%></td>
			<td><%=employee.getNameString()%></td>
			<td><%=employee.getEmail()%></td>
			<td><%=employee.getContact()%></td>
			<td><%=employee.getDesignation()%></td>
			<td><%=employee.getSalary()%></td>
		</tr>

	</table>
	<%
	}
	%>

</body>
</html>