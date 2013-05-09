<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
        
<head>
<style type="text/css">
	table {border-collapse:collapse}
	td {border:1px solid gray}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Campaign</title>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
  <script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
  <link rel="stylesheet" href="/resources/demos/style.css" />
  <script>
  $(function() {
    $( "#startDate" ).datepicker();
    $( "#endDate" ).datepicker();
  });
  </script>
</head>
<body>
	<h1>Add Campaign</h1>
	<form action="/campaigns" method="POST">
		Campaign name: <input type="text" id="name" name="name" value="${campaign.name}" />
		Active: <input type="checkbox" id="active" name="active" checked="${campaign.active}" />
		<p>Start Date: <input type="text" id="startDate" name ="startDate" value="${campaign.startDate}"/></p>
		<p>End Date: <input type="text" id="endDate" name ="endDate" value="${campaign.endDate}"/></p>
		<input type="submit" value="Save" />
	</form>
	<table>
		<thead>
			<tr>
				<th>Product Name</th>
				<th>Product Short Description</th>
				<th>Product Country</th>
				<th>Product URL</th>
				<th>Product Actions</th>
			</tr>
		</thead>		
	<c:forEach items="${campaign.product}" var="product">
		<tr>
			<td>${product.name}</td>
			<td>${product.shortDescription}</td>
			<td>${product.country}</td>
			<td>${product.url}</td>
			<td><a href="/product/">Edit</a>&nbsp;
				<a href="">Del</a>
			</td>
		</tr>
	</c:forEach>
	</table>
	<a href="/product/addProduct/${campaign.key.id}">Add Product to this Campaign</a>
</body>
</html>