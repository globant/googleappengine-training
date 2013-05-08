<%@page import="com.globant.gaetraining.addsincgae.model.Customer"%>
<%@page import="com.google.appengine.api.datastore.Key"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%	
	// Take a BlobstoreService object for this view
    BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
<title>Edit Customers</title>
</head>
<body>
	<h3>Customer Edit</h3>
	<form action="<%=blobstoreService.createUploadUrl("/customers/"+((Customer)request.getAttribute("customer")).getKey().getId())%>" method="POST" enctype="multipart/form-data">
		<div>
			<div>
				Name
				<textarea name="name" rows="1" cols="15">${customer.name}</textarea>
			</div>
		</div>
		<div>
			<div>
				Legal Name
				<textarea name="legalName" rows="1" cols="15">${customer.legalName}</textarea>
			</div>
		</div>
		<div>
			<div>
				Amount of Employees
				<textarea name="employeesAmount" rows="1" cols="15">${customer.employeesAmount}</textarea>
			</div>
		</div>
		<div>
			<div>
				Description
				<textarea name="description" rows="1" cols="15">${customer.description}</textarea>
			</div>
		</div>
		<div>
			<div>
				Owners	
				<form:select multiple="true" path="lists.ownersString"  items="${users}" itemValue="keyString" itemLabel="userName" />
			</div>
		</div>
		<div>
			<div>
				Representative	
				<form:select multiple="true" path="lists.representativesString" items="${users}" itemValue="keyString" itemLabel="userName" />
			</div>
		</div>
		<div>
			<div>Logo</div>
			<img alt="Logo" src="${customer.logo}"/>
			<input type="file" name="logo"/>
		</div>

		<input type="submit" value="Submit">
	</form>
</body>
</html>