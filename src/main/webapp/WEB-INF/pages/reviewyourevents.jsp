<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>Events review</title>
<%@include file="header.jsp"%>
<script type="text/javascript"
	src="/EventScheduling/resources/scripts/notification.js"></script>
<link rel="stylesheet"
	href="/EventScheduling/resources/css/notification.css">
<script type="text/javascript">
	$(function() {
		$("#image").hide();
		$("#update").hide();
		$("#canceledit").hide();
		$("#edit").click(function(event) {
			$("#image").show();
			$("#update").show();
            $("#edit").hide();
            $("#canceledit").show();
		});
		$("#canceledit").click(function(event) {
			$("#image").hide();
			$("#update").hide();
            $("#edit").show();
            $("#canceledit").hide();
		});
	});
	$(function() {
		$("#faqfile").hide();
		$("#updatefaq").hide();
		$("#canceleditfaq").hide();
		$("#editfaq").click(function(event) {
			$("#faqfile").show();
			$("#updatefaq").show();
			$("#editfaq").hide();
			$("#canceleditfaq").show();

		});
		$("#canceleditfaq").click(function(event) {
			$("#faqfile").hide();
			$("#updatefaq").hide();
			$("#editfaq").show();
			$("#canceleditfaq").hide();

		});
	});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>



	<center>
		<c:if test="${empty events }">
			<h3>Until now you didn't create any event</h3>
			<br />
			Create events and enjoy
			</c:if>

		<c:forEach var="event" items="${events }">



			<table border="1">
				<tr>

					<td colspan="3">${emailexist }<form:form method="post"
							modelAttribute="judge" action="sendJudgeEmail">
				
					Invite by email:<input name="toAddress" type="text" required />
							<input name="eventID" type="hidden" value="${event.eventID }" />
							<input name="fromAddress" type="hidden"
								value="${sessionScope.userlogin.email }" />
							<button type="submit">Invite Judges to your events</button>
						</form:form>

					</td>
				</tr>
				<tr>

					<td colspan="3"><form:form method="post"
							modelAttribute="sponsors" action="sendSponsorEmail">
				
					Invite by email:<input name="sponsorEmail" type="text" required />
							<input name="eventID" type="hidden" value="${event.eventID }" />
							<input name="ownerEmail" type="hidden"
								value="${sessionScope.userlogin.email }" />
							<button type="submit">Invite sponsors to your events</button>
						</form:form></td>
				</tr>
				<tr>

					<td colspan="3">
						<center>
							<form:form method="post" modelAttribute="judge"
								action="sendfriendinvitation">


								<input name="eventID" type="hidden" value="${event.eventID }" />
								<input name="fromAddress" type="hidden"
									value="${sessionScope.userlogin.email }" />
								<button type="submit">Invite your friends list to this
									event</button>
							</form:form>
						</center>
					</td>
				<tr>
					<td colspan="3">
						<center>
							<a href="listjudges?eventID=${event.eventID }">Review judges'
								invitations </a>
						</center>
					</td>
				</tr>
				<tr>
					<td>Organizer Name</td>
					<td>${event.organizerName }</td>
				</tr>
				<tr>
					<td>Organizer Description</td>
					<td>${event.organizerDescription }</td>
				</tr>
				<tr>

					<td>Title</td>
					<td>${event.title }</td>
				</tr>
				<tr>

					<td>Description</td>
					<td>${event.description }</td>
				</tr>
				<tr>

					<td>Location</td>
					<td>${event.location }</td>
				</tr>
				<tr>

					<td>Start Date</td>
					<td>${event.startDate }</td>
				</tr>
				<tr>

					<td>Deadline to register</td>
					<td>${event.regDate }</td>
				</tr>
				<tr>
					<td>Event date and time</td>
					<td>${event.eventDateAndTime }</td>
				</tr>

				<tr>
					<td>Background</td>
					<td><img alt=" "
						src="/EventScheduling/displayimage?eventId=${event.eventID }"
						width="200" height="100">
						<form action="updateimage" enctype="multipart/form-data"
							method="post">
							<input type="hidden" name="eventID" value="${event.eventID }">
							<input id="image" type="file" name="image">

							<button id="update">Update</button>

						</form> <a href="#" id="edit">Edit</a>
						<a href="#" id="canceledit">Cancel</a>
						</td>
				</tr>
				<tr>
					<td>FAQ</td>
					<td><h4><a target="_blank"
						href="/EventScheduling/displayfaq?eventId=${event.eventID }">
							Display FAQ</a></h4>
						<form action="updatefaq" enctype="multipart/form-data"
							method="post">
							<input type="hidden" name="eventID" value="${event.eventID }">
							<input id="faqfile" type="file" name="faqFile">

							<button id="updatefaq">Update</button>
							<a href="#" id="editfaq"> Edit</a>
							<a href="#" id="canceleditfaq"> cancel</a>
						</form></td>
				</tr>
				<tr>
					<td colspan="3"><center>
							<div>
								<form action="social/facebook/signin" method="get">
									<input type="hidden" name="state" value="${event.eventID}">
									<button>Share on Facebook</button>
								</form>
								<form action="">
									<button>Share on Twitter</button>
								</form>
							</div>
							<form action="deleteEvent" method="post">
								<input name="eventID" type="hidden" value="${event.eventID }" />
								<button>Delete event</button>
							</form>
						</center></td>
				</tr>
			</table>
			<br />
		</c:forEach>
	</center>
</body>
</html>