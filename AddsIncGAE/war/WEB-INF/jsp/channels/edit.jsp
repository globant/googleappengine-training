<%@page import="com.globant.gaetraining.addsincgae.model.DistributionChannel"%>
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
	<h2>Edit distribution channel: <strong><c:out value="${distChannel.name}"/></strong></h2>
	<form action="<%=blobstoreService.createUploadUrl("/channels/"+((DistributionChannel)request.getAttribute("distChannel")).getKeyString())%>"
		method="POST" enctype="multipart/form-data">
		<div>Name: <input type="text" name="name" value="${distChannel.name}"/></div>
		<div>Country: <input type="text" name="country" value="${distChannel.country}"/></div>
		<div>Media type: <input type="text" name="mediaType" value="${distChannel.mediaType}"/></div>
		<div>Template: <textarea rows="5" cols="60" name="template">${distChannel.template}</textarea></div>
		<div>Logo: <img title="logo" src="<%="/channels/".concat(((DistributionChannel)request.getAttribute("distChannel")).getKeyString()).concat("/logo")%>"/>
		<input type="file" name="logo"/></div>
		<div><input type="submit"/></div>
	</form>
</body>
</html>