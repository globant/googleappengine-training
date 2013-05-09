<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Products List</title>
</head>
<h1>Products List</h1>
<a href="/product/prodgoto/AddProduct">Add new product</a>
<body>
	<table>
		<tr>
			<th></th>
			<th>Name</th>
			<th>Family</th>
			<th>Short description</th>
			<th>Long description</th>
			<th>Url</th>
			<th>Country</th>
			<th>Campaign</th>
			<th></th>
		</tr>
		<c:forEach items="${products}" var="product">
			<tr>
				<td><a href="/products/${product.key}">Edit</a></td>
                <td><c:out value="${product.name}" /></td>
				<td><c:out value="${product.productFamily}" /></td>
				<td><c:out value="${product.shortDescription}" /></td>
				<td><c:out value="${product.longDescription}" /></td>
				<td><c:out value="${product.url}" /></td>
				<td><c:out value="${product.country}" /></td>
				<td><c:out value="${product.campaign.name}" /></td>
				<td><a href="/products/${product.key}">Del</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>