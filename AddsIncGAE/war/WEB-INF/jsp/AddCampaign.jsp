<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
        
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Campaign</title>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
  <script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
  <link rel="stylesheet" href="/resources/demos/style.css" />
  <script>
  $(function() {
    $( "#startDate" ).datepicker();
    $( "#endDate" ).datepicker();
  });
  </script>
</head>
<body>
	<h1>Add Campaign</h1>
	<form action="/campaigns" method="POST">
		Campaign name: <input type="text" id="name" name="name" />
		Active: <input type="checkbox" id="active" name="active" />
		<p>Start Date: <input type="text" id="startDate" name ="startDate"/></p>
		<p>End Date: <input type="text" id="endDate" name ="endDate"/></p>
		<input type="submit" value="Create" />
	</form>
</body>
</html>