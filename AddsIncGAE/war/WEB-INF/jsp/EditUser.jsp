<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit User</title>
</head>
<body>
	<h1>Edit User</h1>
	<form action="/users/${user.key.id}" method="POST">
		User name: <input type="text" id="userName" name="userName" value="${user.userName}"/>

		 <form:select multiple="true" path="user.roles" items="${roles}" />

		<input type="submit" value="Update" />
	</form>
</body>
</html>