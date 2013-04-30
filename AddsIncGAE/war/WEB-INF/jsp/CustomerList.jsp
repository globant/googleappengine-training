<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customers List</title>
</head>
<h1>Customers List</h1>
<a href="/addCustomer">Add new customer</a>
<body>
	<table>
		<tr>
			<th></th>
			<th>Name</th>
			<th>Legal name</th>
			<th>Employee</th>
			<th></th>
		</tr>
		<c:forEach items="${customers}" var="customer">
			<tr>
				<td><a href="/customers/${customer.id}">Edit</a></td>
				<td><c:out value="${customer.name}" /></td>
				<td><c:out value="${customer.legalName}" /></td>
				<td><c:out value="${customer.employeesAmount}" /></td>
				<td><a href="/customers/${customer.id}">Del</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>