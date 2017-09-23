<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create Event</title>
 <%@include file="header.jsp"%> 


<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script type="text/javascript"
	src="/EventScheduling/resources/lib/jquery.easyeditor.js"></script>
	<script src="/EventScheduling/resources/scripts/countries.js"></script>
	
<script type="text/javascript"
	src="/EventScheduling/resources/lib/moment.js"></script>
<script type="text/javascript"
	src="/EventScheduling/resources/lib/bootstrap.min.js"></script>
<script type="text/javascript"
	src="/EventScheduling/resources/lib/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript"
	src="/EventScheduling/resources/scripts/datetimepicker.js"></script>
<link rel="stylesheet"
	href="/EventScheduling/resources/css/bootstrap.css" />
 <link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css"> 
<link rel="stylesheet"
	href="/EventScheduling/resources/css/bootstrap-datetimepicker.css" />

<link rel="stylesheet"
	href="/EventScheduling/resources/css/richtextbox.css">
<link rel="stylesheet"
	href="/EventScheduling/resources/css/easyeditor.css">

<script>
	jQuery(document).ready(function($) {
		new EasyEditor('#editor');
	});
</script>


</head>
<body>
	<br />
	<br />
	<br />



	<center>

		<form:form id="myform" method="post" modelAttribute="event"
			action="createEvent" enctype="multipart/form-data">
			<table>
				<tr>
					<td>Organizer name</td>
					<td style="position: relative; left: -400px;">Organizer
						Description</td>
				</tr>
				<tr>
					<td><input type="text" name="organizerName"></td>
					<td style="position: relative; left: -400px;"><input
						type="text" name="organizerDescription"></td>
				</tr>
				<tr>
																<td>Start Date</td>
					<td style="position: relative; left: -440px;">Deadline to apply</td>
					<td style="position: relative; left: -420px;">Event Date and time</td>
						
				</tr>
				<tr>
					<td>
						<div style="width: 250px;" class="form-group">
							<div class='input-group date' id='datetimepicker1'>
								<input type='text' class="form-control" /> <span
									class="input-group-addon"> <span
									class="glyphicon glyphicon-calendar"></span>
								</span>
							</div>
						</div>



						<p style="color: red;">
							<form:errors path="startDate" />
						</p>
					</td>
					<td style="position: relative; left: -440px;">
						
							<div class='input-group date' id='datetimepicker2'>
								<input type='text' class="form-control" /> <span
									class="input-group-addon"> <span
									class="glyphicon glyphicon-calendar"></span>
								</span>
							</div>
						
						<p style="color: red;">
							<form:errors path="regDate" />
						</p>
					</td>
					<td style="position: relative; left: -420px;">
						
							<div class='input-group date' id='datetimepicker3'>
								<input type='text' class="form-control" /> <span
									class="input-group-addon"> <span
									class="glyphicon glyphicon-calendar"></span>
								</span>
							</div>
						
						<p style="color: red;">
							<form:errors path="eventDateAndTime" />
						</p>
					</td>
				</tr>

				<tr>
					<td>
						<div style="width: 700px; ">
							<textarea name="description" id="editor"
								placeholder="Type description here ... "></textarea>
						</div>
					</td>
					<td><p style="color: red;">
							<form:errors path="description" />
						</p></td>
				</tr>
				<tr>
					<td><input name="startDate" type="hidden" /> 
						<input name="regDate" type="hidden" />
						<input name="eventDateAndTime" type="hidden" /></td>
				</tr>
				<tr>
					<td>Title</td>
					<td style="position: relative; left: -200px;">Location</td>
				</tr>
				<tr>
					<td><input  style="width: 200px;"
						name="title" type="text" />
						<p style="color: red;">
							<form:errors path="title" />
						</p></td>

					<td style="position: relative; left: -200px;"><input id="location" name="location" type="text" />
						
						<p style="color: red;">
							<form:errors path="location" />
						</p></td>
				</tr>
				<tr>
					<td>Choose your event picture<br /> <input type="file"
						name="image"><br /></td>
					<td><input type="hidden" name="userID"
						value="${sessionScope.userlogin.id }"></td>
				</tr>
				<tr>
					<td>Add you guide (FAQ) at your aword<br /> <input
						type="file" name="faqFile"></td>
				</tr>

				<tr>
					<td style="position: relative; left: 500px; top: -100px;">
						make your event public &nbsp;<input type="radio" name="status" value="false"/><br /> make
						your event private<input type="radio" name="status" value="true" />
					</td>
				</tr>
				<tr>
					<td style="position: relative; left: 500px; top: -90px;"><select name="subject">
							<option value="0" selected>Choose your subject</option>
								<option value="Business">Business</option>
								<option value="Eduction">Education</option>
								<option value="Celebration">Celebration</option>	
								<option value="Organizational">Organizational</option>
								<option value="Individual">Individual</option>
					</select></td>
				</tr>
				<tr>
					<td style="position: relative; left: 300px;">
						<button type="submit" id="submit">Save</button>
					</td>
				</tr>

			</table>
		</form:form>
	</center>
	<br />



</body>
</html>