<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Customer</title>
</head>
<body>
    <h1>Add customer</h1>
    <form action="/customers" method="POST" class="form">
        <label>Customer name:</label><input type="text" id="name" name="name" /><br/>
        <label>Legal name:</label><input type="text" id="legalName" name="legalName" /><br/>
        <label>Description:</label><textarea rows="2" cols="10" id="description" name="description"></textarea><br/>
        <label>Employees amount:</label><input type="text" id="employeesAmount" name="employeesAmount" /><br/>
        <input type="submit" value="Create" />
    </form>
</body>
</html>