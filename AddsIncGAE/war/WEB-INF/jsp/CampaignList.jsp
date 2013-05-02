<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Users List</title>
</head>
<h1>Campaign List</h1>
<a href="/campaign">Add new Campaign</a>
<body>
	<table>
		<tr>
			<th></th>
			<th>User Name</th>
			<th>Role</th>
			<th></th>
		</tr>
		<c:forEach items="${users}" var="user">
			<tr>
				<td><a href="/users/${user.key.id}">Edit</a></td>
				<td><c:out value="${user.userName}" /></td>
				<td><c:out value="${user.roles}" /></td>
				<td><a href="/users/${user.key.id}">Del</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>