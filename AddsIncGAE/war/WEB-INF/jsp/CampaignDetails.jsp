<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Campaign Details</title>
</head>
<body>
	<h1>${campaign.name}</h1>

	<p>
		Start date:
		<fmt:formatDate value="${campaign.startDate}" pattern="MM/dd/yyyy" />
	</p>
	<p>
		End date:
		<fmt:formatDate value="${campaign.endDate}" pattern="MM/dd/yyyy" />
	</p>

	<h2>Products</h2>

	<table border="1" cellpadding="5" width="50%">
		<tr>
			<th>Name</th>
			<th>Hits</th>
			<th>Views</th>
		</tr>
		<c:forEach items="${campaign.product}" var="product">
			<tr>
				<td>${product.name}</td>
				<td>${product.name}</td>
				<td>${product.name}</td>
			</tr>
		</c:forEach>
	</table>

	<h2>Media channels</h2>

</body>
</html>