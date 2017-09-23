<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<title>Landing page</title>
<%@include file="header.jsp"%>

<script type="text/javascript"
	src="/EventScheduling/resources/lib/jquery.leanModal.min.js"></script>
<script type="text/javascript"
	src="/EventScheduling/resources/lib/moment.js"></script>
<script type="text/javascript"
	src="/EventScheduling/resources/lib/bootstrap.min.js"></script>
<script type="text/javascript"
	src="/EventScheduling/resources/lib/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript"
	src="/EventScheduling/resources/scripts/datetimepicker.js"></script>
<!-- <link rel="stylesheet"
	href="/EventScheduling/resources/css/bootstrap.css" />
<link rel="stylesheet"
	href="/EventScheduling/resources/css/bootstrap-datetimepicker.css" /> -->
<link rel="stylesheet"
	href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css" />
<!-- <link rel="stylesheet"
	href="/EventScheduling/resources/css/font-awesome.min.css" /> -->
<link type="text/css" rel="stylesheet"
	href="/EventScheduling/resources/css/style.css" />
<script type="text/javascript">
	function showHint(str) {
		var xhttp;
		if (str.length == 0) {
			document.getElementById("search").innerHTML = "";
			return;
		}
		xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				document.getElementById("search").innerHTML = xhttp.responseText;
			}
		};
		xhttp.open("POST", "searchevent/ajax?title=  " + str + "&location="
				+ str + "&date=" + str + "", true);
		xhttp.send();
	}
</script>


</head>
<body>
	<center></center>


	<form method="post" action="searchevent">
		<table style="position: relative; left: 300px; top: 60px;">
			<tr>
				<td><h3>Search for award</h3></td>
			</tr>
			<tr>
				<td>Title</td>
				<td>Location</td>
				<td>Dead line</td>
			</tr>


			<tr>
				<td><input type="text" name="title"
					onkeyup="showHint(this.value)" /></td>
				<td><input type="text" name="location"
					onkeyup="showHint(this.value)" /></td>
				<td><input type="text" name="date"  onkeyup="showHint(this.value)"/>

					<!-- <div style="width: 250px;" class="form-group">
						<div class='input-group date' id='datetimepicker4'>
							<input type='text' onkeyup="showHint(this.value)"
								class="form-control" /> <span class="input-group-addon">
								<span class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</div> --></td>
				<td><button>Find</button></td>
			</tr>



		</table>
	</form>
	<c:if test="${sessionScope.userlogin.email==null }">

		<div class="container">
			<a id="modal_trigger" href="#modal" class="btn">Create your first
				event or check your event's</a>

			<div id="modal" class="popupContainer" style="display: none;">
				<header class="popupHeader"> <span class="header_title">Log
					in</span> <span class="modal_close"><i class="fa fa-times"></i></span> </header>

				<section class="popupBody"> <!-- Social Login -->
				<div class="social_login">

					<div class="">
						<a href="#" class="social_box fb"> <span class="icon"><i
								class="fa fa-facebook"></i></span> <span class="icon_title">Connect
								with Facebook</span>

						</a> <a href="#" class="social_box google"> <span class="icon"><i
								class="fa fa-google-plus"></i></span> <span class="icon_title">Connect
								with Google</span>
						</a>
					</div>
					<div class="centeredText">
						<span> Use your Email address to register </span>
					</div>

					<div class="action_btns">
						<div class="one_half">
							<a href="#" id="login_form" class="btn">Login</a>
						</div>
						<div class="one_half last">
							<a href="#" id="register_form" class="btn">Sign up</a>
						</div>

					</div>
					<br />
					<div style="position: relative; left: 60px;">
						<a href="redirecttobusinessregistration" id="register_form"
							class="btn">Sign up as business user</a>
					</div>
				</div>

				<!-- Username & Password Login form -->
				<div class="user_login">
					<form method="post" action="login">
						<label>Email / Username</label> <input name="email" type="text" />
						<br /> <label>Password</label> <input name="password"
							type="password" /> <br />

						<div class="checkbox">
							<input id="remember" type="checkbox" /> <label for="remember">Remember
								me on this computer</label>
						</div>

						<div class="action_btns">
							<div class="one_half">
								<a href="#" class="btn back_btn"><i
									class="fa fa-angle-double-left"></i> Back</a>
							</div>
							<div class="one_half last">
								<input type="submit" class="btn btn_red" value="Login" />
							</div>
						</div>
					</form>

					<a href="#" class="forgot_password">Forgot password?</a>
				</div>

				<!-- Register Form -->
				<div class="user_register">
					<form:form method="post" modelAttribute="user" action="register">
						<label>First Name</label>
						<form:input path="firstName" type="text" />
						<form:errors path="firstName" />
						<br />
						<label>Last Name</label>
						<form:input path="lastName" type="text" />
						<form:errors path="lastName" />
						<br />

						<label>Email Address</label>
						<form:input path="email" type="text" />
						<form:errors path="email" />
						<br />

						<label>Password</label>
						<form:input path="password" type="password" />
						<form:errors path="password" />
						<br />
						<div class="action_btns">
							<div class="one_half">
								<a href="#" class="btn back_btn"><i
									class="fa fa-angle-double-left"></i> Back</a>
							</div>
							<div class="one_half last">
								<button class="btn btn_red">Register></button>
							</div>
						</div>
					</form:form>
				</div>
				</section>
			</div>
		</div>
		<script type="text/javascript"
			src="/EventScheduling/resources/scripts/popup.js"></script>
	</c:if>
	<c:if test="${sessionScope.userlogin.email !=null }">
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
	</c:if>
	<c:set var="counter" value="0" />
	<div id="search">
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
				src="/EventScheduling/displayimage?eventId=${event.eventID }" width="200" height="200">
		</c:forEach>
	</div>




	<br />
	<br />
	<div style="background: gray; height: 100px; width: 100%;">
		<center>
			<a style="color: white" href="#"> About</a><br /> <a
				style="color: white;" href="">How it works </a><br /> <a
				style="color: white;" href="">Contact Us </a><br /> <a
				style="color: white;" href="">Terms and conditions </a><br /> <a
				style="color: white;" href="">Career </a>

		</center>
	</div>


</body>
</html>