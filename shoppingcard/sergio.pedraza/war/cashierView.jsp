<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cashier View</title>
</head>
<body>
	<form action="/cashier.do" method="post">
		<h3>Save Payment</h3>
		Payment Value<input type="text" name="paymentValue" id="paymentValue" required="true" /> </br> 
		Client username<input type="text" name="username" id="username" required="true" /> </br> 
		client email address<input type="text" name="address" id="address" required="true" /> </br> 
		<input type="submit" value="Save Payment" />
	</form>
	<form action="/cashier.do" method="post">
		<h3>Save Purchase</h3>
		Purchase Value<input type="text" name="purchaseValue" id="purchaseValue" required="true" /> </br> 
		Client username<input type="text" name="username" id="username" required="true" /> </br> 
		<input type="submit" value="Save Purchase" />
	</form>
</body>
</html>