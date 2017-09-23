<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>
	<c:set var="counter" value="0" />

	<c:forEach var="event" items="${events }">

		<c:set var="counter" value="${counter + 1}" />



		<c:if test="${counter>4 }">
			<c:set var="counter" value="0" />

			<c:set var="counter" value="${counter + 1}" />

			<br />
		</c:if>

		<a style="position: relative; left: 230px; top: -210px;"
			href="geteventInfo?eventID=${event.eventID }">${event.title }</a>
		<img
			style="background-image:url('/EventScheduling/displayimage?eventId=${event.eventID }');  position: relative; left: 100px; "
			src="" width="200" height="200">
	</c:forEach>

</body>
</html>