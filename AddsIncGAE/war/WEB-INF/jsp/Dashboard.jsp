<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Dashboard</title>
</head>
<body>
	<h1>Customer Dashboard</h1>
	<h2>Active campaigns</h2>

	<table border="1" cellpadding="15" width="50%">
		<tr>
			<th>Name</th>
			<th>Days left</th>
			<th>Products</th>
		</tr>
		<c:forEach items="${campaigns}" var="campaign">
			<tr>
				<td><a href="/dashboard/campaign/${campaign.key.id}">${campaign.name}</a></td>
				<td>${campaign.daysLeft}</td>
				<td>${fn:length(campaign.product)}</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>