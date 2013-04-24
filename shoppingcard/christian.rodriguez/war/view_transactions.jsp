<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.globant.training.gae.model.Transaction"%>
<%@ page import="com.globant.training.gae.model.ShoppingCard"%>
<%@ page import="com.globant.training.gae.dao.DaoTransaction"%>
<%@ page import="com.globant.training.gae.dao.DaoShopingCard"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<head>
<title>Transactions list</title>
</head>
<body>

	<h1>Transactions list - Christian Rodriguez</h1>

	<%
		ShoppingCard shoppingCard = DaoShopingCard.INSTANCE
				.findByCode("AX54E20-534BCD");
		List<Transaction> transactions = DaoTransaction.INSTANCE.findAll();
	%>

	<div>
		<h2>Shopping card</h2>
		<ul>
			<li><b>Number:</b> <%=shoppingCard.getCode()%></li>
			<li><b>Balance:</b> <%=shoppingCard.getBalance()%></li>
		</ul>
	</div>
	
	<hr />
	<a href="index.jsp">Go to Home!!!</a>
	<hr />
	
	<h2>Transactions</h2>
	<table cellpadding="5" width="100%" border="1">
		<tr>
			<th>Date</th>
			<th>Amount</th>
			<th>Balance after transaction</th>
		</tr>

		<%
			for (Transaction transaction : transactions) {
		%>
		<tr>
			<td><%=transaction.getTimeStamp()%></td>
			<td><%=transaction.getAmount()%></td>
			<td><%=transaction.getBalanceAfterTransaction()%></td>
		</tr>
		<%
			}
		%>
	</table>

	<hr />
	<a href="index.jsp">Go to Home!!!</a>

</body>
</html>