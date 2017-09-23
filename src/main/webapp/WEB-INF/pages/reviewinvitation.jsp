<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Review Invitation</title>
<%@ include file="header.jsp"%>
</head>
<body>
<center>
	<c:forEach var="judge" items="${judges }" varStatus="status">
		
			<a href="checkjudgeinvitation?eventID=${judge.eventID }">You have
				an invitation from ${judge.fromAddress}</a> <br/>
		
	</c:forEach>
	<c:if test="${empty judges  }"><h3> there is no invitation to you</h3> </c:if>
</center>
</body>
</html>