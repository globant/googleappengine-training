<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<title>Distribution channels</title>
<script type="text/javascript">
function deleteChannel(channelKey){
	$.ajax({url:'/channels/'+channelKey, dataType:'json', type:'DELETE', success: function() {
		window.location.href = "/channels";
	}});
}
</script>
</head>
<body>
<h2>Distribution channels</h2>
<a href="/channels/add">Add Distribution Channel</a>
<table>
		<tr>
			<th></th>
			<th>Name</th>
			<th>Country</th>
			<th>Media</th>
			<th>Template</th>
		</tr>
		<c:forEach items="${distchannels}" var="channel">
			<tr>
				<td><a href="/channels/${channel.keyString}">Edit</a></td>
                <td><c:out value="${channel.name}" /></td>
				<td><c:out value="${channel.country}" /></td>
				<td><c:out value="${channel.mediaType}" /></td>
				<td title="${fn:escapeXml(channel.template)}">view</td>
				
				<td><input type="button" onclick="deleteChannel('${channel.keyString}')" value="X"/></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>