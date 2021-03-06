<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<title>Users List</title>
<script type="text/javascript">
function deleteUser(userKey){
	$.ajax({url:'/people/'+userKey, dataType:'json', type:'DELETE', success: function() {
		window.location.href = "/people";
	}});
}
</script>
</head>
<body>
<%
UserService userService = UserServiceFactory.getUserService();
%>
<h4>Me: <%=request.getUserPrincipal().getName() %></h4>
<h1>Users List</h1>
<a href="/people/add">Add new user</a>
	<table>
		<tr>
			<th></th>
			<th>User Name</th>
			<th>Role</th>
			<th></th>
		</tr>
		<c:forEach items="${users}" var="user">
			<tr>
				<td><a href="/people/${user.keyString}">Edit</a></td>
				<td><c:out value="${user.userName}" /></td>
				<td><c:out value="${user.roles}" /></td>
				<td><input type="button" onclick="deleteUser('${user.keyString}')" value="X"/></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>