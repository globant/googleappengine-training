<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page
	import="com.google.appengine.api.blobstore.BlobstoreServiceFactory"%>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%	
    BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Distribution channels</title>
</head>
<body>
	<h2>Add distribution channel</h2>
	<form action="<%=blobstoreService.createUploadUrl("/channels/add")%>"
		method="POST" enctype="multipart/form-data">
		<div>Name: <input type="text" name="name"/></div>
		<div>Country: <input type="text" name="country"/></div>
		<div>Media type: <input type="text" name="mediaType"/></div>
		<div>Template: <textarea rows="5" cols="60" name="template">&lt;div&gt;&lt;h4&gt;{product.name}&lt;/h4&gt;&lt;p&gt;{product.shortDescription}&lt;/p&gt;&lt;p&gt;{product.longDescription}&lt;/p&gt;&lt;p&gt;&lt;a href="{product.navigationURL}"&gt;Product URL Navigation&lt;/a&gt;&lt;/p&gt;&lt;p&gt;&lt;a href="{product.displayBreadcrumURL}"&gt;Display Product&lt;/a&gt;&lt;/p&gt;&lt;/div&gt;</textarea></div>
		<div>Logo: <input type="file" name="logo"/></div>
	</form>
</body>
</html>