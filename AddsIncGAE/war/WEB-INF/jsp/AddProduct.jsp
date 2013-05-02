<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Product</title>
</head>
<body>
    <h1>Add Product</h1>
    <form:form commandName="product" action="/products" method="POST">
        <label>Name:</label><form:input type="text" path="name" id="name" name="name" /><br/>
        <input type="submit" value="Create" />
    </form:form>
</body>
</html>