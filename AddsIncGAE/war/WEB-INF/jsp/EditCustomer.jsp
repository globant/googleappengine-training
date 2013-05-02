<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Customers</title>
</head>
<body>
	<h3>Customer Edit</h3>
	<form name="input" action="/customers/${customer.id}" method="POST">
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
				<textarea name="amountOfEmployees" rows="1" cols="15">${customer.employeesAmount}</textarea>
			</div>
		</div>
		<div>
			<div>
				Description
				<textarea name="description" rows="1" cols="15">${customer.description}</textarea>
			</div>
		</div>
		<div>
			Owners
			<div>
				<div>Available:</div>
				<div>Aca iria el available</div>
			</div>
			<div>
				<div>Selected:</div>
				<div>Aca iria el selected</div>
			</div>
		</div>
		<div>
			<div>Logo</div>
			<div>Aca iria la url</div>
		</div>

		<input type="submit" value="Submit">
	</form>
</body>
</html>