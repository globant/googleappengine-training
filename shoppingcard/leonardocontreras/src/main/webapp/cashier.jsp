<%@page import="com.google.appengine.api.datastore.Entity"%>
<%@page import="com.globant.precard.dao.PrepaidCardDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cashier</title>
</head>
<body>
<h1>Cashier <%=request.getAttribute("cashierid") %></h1>
<p style="color: red"><%=request.getAttribute("message")==null?"":request.getAttribute("message") %></p>
<a href="/">Home</a>
<h3>purchase</h3>
<form action="/cashier/<%=request.getAttribute("cashierid") %>/purchase" method="post">
	<select name="card">
		<option>select</option>
		<%
	 Iterable<Entity> itera =
	PrepaidCardDAO.INSTANCE.getCards();
	for (Entity ent:itera){
		out.print("<option value=\"");
		out.print(ent.getKey().getName());
		out.print("-");
		out.print(ent.getParent().getName());
		out.print("\">");
		out.print(ent.getKey().getName());
		out.print(" - ");
		out.print(ent.getParent().getName());
		out.print("</option>");
	}
	%>
	</select>
	<input type="text" name="amount"  default="0"/>
	<button type="submit">Register Purchase</button>
</form>



<h3>payment</h3>
<form action="/cashier/<%=request.getAttribute("cashierid") %>/asyncpayment" method="post">
	<select name="card">
		<option>select</option>
		<%
	 itera =
	PrepaidCardDAO.INSTANCE.getCards();
	for (Entity ent:itera){
		out.print("<option value=\"");
		out.print(ent.getKey().getName());
		out.print("-");
		out.print(ent.getParent().getName());
		out.print("\">");
		out.print(ent.getKey().getName());
		out.print(" - ");
		out.print(ent.getParent().getName());
		out.print("</option>");
	}
	%>
	</select>
	<input type="text" name="amount"  default="0"/>
	<button type="submit">Register Payment</button>
</form>

	
<br>
<a href="/cashier/<%=request.getAttribute("cashierid") %>/seed">Populate Data</a>

</body>
</html>