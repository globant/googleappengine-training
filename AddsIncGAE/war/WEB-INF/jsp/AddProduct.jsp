<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>

<%	
	// Take a BlobstoreService object for this view
    BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	Long idCampaign = (Long)request.getAttribute("campaignId");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Product</title>
</head>
<body>
    <h1>Add Product</h1>
   <form action="<%=blobstoreService.createUploadUrl("/product/addProduct/"+idCampaign)%>" method="POST" enctype="multipart/form-data">
		Product name: <input type="text" id="name" name="name" /><br />
		Short Description: <input type="text" id="shortDescription" name="shortDescription" /><br />
		Long Description: <input type="text" id="longDescription" name="longDescription" /><br />
		Product URL: <input type="text" id="url" name="url" /><br />
		Product Country: <input type="text" id="country" name="country" /><br />
		Product Country: <input type="file" id="productImage" name="productImage" /><br />
		<input type="submit" value="Create" />
	</form>
</body>
</html>