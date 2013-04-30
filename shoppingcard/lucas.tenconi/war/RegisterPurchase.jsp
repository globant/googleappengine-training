<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
  <head>
    <link type="text/css" rel="stylesheet" href="/stylesheets/main.css" />
  </head>

  <body>
    <form action="/registerPurchase" method="post">
      <div>
      	<label>Purchase amount:</label>
      	<textarea name="amount" rows="3" cols="60"></textarea>
      	<label>Card Number:</label>
      	<input name="cardNumber"/></div>
      	<div><input type="submit" value="Purchase" /></div>
    </form>
  </body>
</html>