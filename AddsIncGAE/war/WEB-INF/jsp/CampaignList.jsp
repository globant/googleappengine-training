<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Campaigns List</title>
</head>
<h1>Campaign List</h1>
<a href="/campaign">Add new Campaign</a>
<body>
	<table>
		<tr>
			<th></th>
			<th>Campaign Name</th>
			<th>Start</th>
			<th>End</th>
			<th>Active</th>
			<th>Products</th>
			<th>Distribution Channel</th>
			<th></th>
		</tr>
		<c:forEach items="${campaigns}" var="campaign">
			<tr>
				<td><a href="/campaign/${campaign.keyString}">Edit</a></td>
				<td><c:out value="${campaign.name}" /></td>
				<td><c:out value="${campaign.startDate}" /></td>
				<td><c:out value="${campaign.endDate}" /></td>
				<td><c:out value="${campaign.active}" /></td>
				<td><c:out value="${campaign.product}" /></td>
				<td><c:out value="${campaign.distributionChannelKeys}" /></td>
				<td><a href="/campaigns/${campaign.key.id}">Del</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>