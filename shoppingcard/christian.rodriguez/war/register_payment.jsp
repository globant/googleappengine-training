<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<head>
<title>Register payment</title>
</head>
<body>

	<h1>Register payment</h1>

	<form action="/register_payment" method="post" accept-charset="utf-8">
		<table>
			<tr>
				<td><label for="user">User:</label></td>
				<td><input type="text" name="user" id="user" size="65"
					value="Christian Rodriguez" readonly /></td>
			</tr>
			<tr>
				<td><label for="value">Value:</label></td>
				<td><input type="text" name="value" id="value" size="65" /></td>
			</tr>
			<tr>
				<td><label for="card_number">Shopping card number:</label></td>
				<td><input type="text" name="card_number" id="card_number"
					readonly size="65" value="AX54E20-534BCD" /></td>
			</tr>
			<tr>
				<td colspan="2" align="right"><input type="submit"
					value="Register payment" /></td>
			</tr>
		</table>
	</form>

	<hr />
	<a href="index.jsp">Go to Home!!!</a>
</body>
</html>