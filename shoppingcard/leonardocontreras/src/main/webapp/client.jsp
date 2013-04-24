<%@page import="com.google.appengine.api.datastore.Entity"%>
<%@page import="com.globant.precard.dao.PrepaidCardDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Client</title>
</head>
<body>
<h1>Client <%=request.getAttribute("clientid") %></h1>
<a href="/">Home</a>
<%
	Iterable<Entity> cards = PrepaidCardDAO.INSTANCE.getUserCards((String)request.getAttribute("clientid"));
	for(Entity card:cards){
%>
	<h3><%=card.getKey().getName() %></h3>
	<h4>balance <%=card.getProperty("balance") %></h4>
	<table style="border: 1"><tr><th>Date</th><th>Amount</th><th>New Balance</th></tr>
	<tbody>
		<%
		Iterable<Entity> transacts = PrepaidCardDAO.INSTANCE.getCardTransactions((String)request.getAttribute("clientid") ,card.getKey().getName());
		for(Entity trans:transacts){
			%>
			<tr><td><%=trans.getProperty("timestamp") %></td><td><%=trans.getProperty("amount") %></td><td><%=trans.getProperty("balanceAfterTransaction") %></td></tr>
			<%
		}
		%>
	</tbody>
	</table>
<%		
	}
%>
</body>
</html>