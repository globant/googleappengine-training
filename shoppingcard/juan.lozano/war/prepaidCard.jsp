<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreServiceFactory" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreService" %>
<%@ page import="com.google.appengine.api.datastore.Query" %>
<%@ page import="com.google.appengine.api.datastore.Entity" %>
<%@ page import="com.google.appengine.api.datastore.FetchOptions" %>
<%@ page import="com.google.appengine.api.datastore.Key" %>
<%@ page import="com.google.appengine.api.datastore.KeyFactory" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
  <head>
    <link type="text/css" rel="stylesheet" href="/stylesheets/main.css" />
  </head>

  <body>
	<h2>Prepaid Card</h2>

    <form action="/registerPayment.jsp" method="post">
      <div>Register Payment:</div>
      <div><input type="submit" value="Go!" /></div>      
    </form>
    
    <form action="/registerPurchase.jsp" method="post">
      <div>Register Purchase:</div>
      <div><input type="submit" value="Go!" /></div>      
    </form>
    
    <form action="/showTransactions.jsp" method="post">
      <div>Show Transactions:</div>
      <div><input type="submit" value="Go!" /></div>      
    </form>

  </body>
</html>