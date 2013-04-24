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

<%
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    if (user != null) {
      pageContext.setAttribute("user", user);
%>
<p>Hello, ${fn:escapeXml(user.nickname)}! (You can
<a href="<%= userService.createLogoutURL(request.getRequestURI()) %>">sign out</a>.)</p>
<%
    } else {
%>
<p>Hello!
<a href="<%= userService.createLoginURL(request.getRequestURI()) %>">Sign in</a>
to include your name with greetings you post.</p>
<%
    }
%>

<%
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    Key endUserKey = KeyFactory.createKey("EndUser", "anonymous");
    
    Query query = new Query("Transaction", endUserKey).addSort("timeStamp", Query.SortDirection.DESCENDING);
    List<Entity> transactions = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(10));
    if (transactions.isEmpty()) {
        %>
        <p>EndUser '${fn:escapeXml(user)}' has no messages.</p>
        <%
    } else {
        %>
        <h2>Transactions</h2>
        <%
        for (Entity transaction : transactions) {
            pageContext.setAttribute("transaction_amount",
                                     transaction.getProperty("amount"));
            pageContext.setAttribute("transaction_timeStamp",
                    				 transaction.getProperty("timeStamp")); 
            pageContext.setAttribute("transaction_balance",
   				 					 transaction.getProperty("balanceAfterTransaction"));      
            %>
            <blockquote>Purchase: $ ${fn:escapeXml(transaction_amount)} 
            			-- Date: ${fn:escapeXml(transaction_timeStamp)}
            			-- Balance After Transaction: ${fn:escapeXml(transaction_balance)}
            </blockquote>
            <%
        }
    }
%>	
	<form action="/prepaidcard.jsp" method="post">      
      <div><input type="submit" value="OK" /></div>      
    </form>
	
  </body>
</html>