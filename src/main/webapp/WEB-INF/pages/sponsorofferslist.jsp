<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Offer List</title>
<%@include file="header.jsp" %>
</head>
<body>

	<center>

		<c:forEach var="spon" items="${sponsors }">
			<a href="displaysponsornotify?eventID=${spon.eventID }">you have
				new offer from ${spon.ownerEmail }</a><br/>
		</c:forEach>
		<c:if test="${empty sponsors }"> you don't have any offers</c:if>
	</center>
</body>
</html>