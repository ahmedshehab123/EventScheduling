<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Judges and Sponsors</title>
<%@include file="header.jsp"%>
</head>
<body>
	<table border="1">
		<tr>
			<th>Invited people</th>
			<th>People who accepted invitation</th>
			<th>Invited Sponsors</th>
			<th>Sponsors who accept offer</th>

		</tr>
		<tr>
			<td>
				<c:forEach var="judge" items="${judges }">
     				 ${judge.toAddress }<br />
				</c:forEach>
			<td>
					<c:forEach var="judge" items="${judgeSubscriber }">
                         ${judge.subscriberEmail }<br />
					</c:forEach>
			</td>
			<td>
					<c:forEach var="sponsor" items="${sponsors }">
					
					   ${sponsor.sponsorEmail }<br/>
					</c:forEach>
			</td>
			<td>
				<c:forEach var="sponsor" items="${acceptedSponsors }">
				     ${sponsor.sponsorEmail}
				</c:forEach>
			</td>
		</tr>
	</table>
</body>
</html>