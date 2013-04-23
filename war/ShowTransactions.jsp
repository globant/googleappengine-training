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
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    Key endUserKey = KeyFactory.createKey("EndUser", "anonymous");

    Query query = new Query("Transaction", endUserKey).addSort("timeStamp", Query.SortDirection.DESCENDING);
    List<Entity> transactions = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());
    
    if (transactions.isEmpty()) {
        %>
        <p>EndUser '${fn:escapeXml(user)}' has no messages.</p>
        <%
    } else {
        %>
        <h1>Transactions</h1>
        <%
        for (Entity transaction : transactions) {
            pageContext.setAttribute("transaction_amount",
                                     transaction.getProperty("amount"));
            pageContext.setAttribute("transaction_timeStamp",
                    transaction.getProperty("timeStamp"));
            pageContext.setAttribute("transaction_balance",
                    transaction.getProperty("balanceAfterTransaction"));
          
            %>
            <blockquote>$ ${fn:escapeXml(transaction_amount)} ${fn:escapeXml(transaction_timeStamp)} ${fn:escapeXml(transaction_balance)}</blockquote>
            <%
        }
    }
%>

  </body>
</html>