<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<title>Event Information</title>
 <%@include file="header.jsp"%> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="/EventScheduling/resources/lib/jquery-1.11.0.min.js"></script>
<script type="text/javascript"
	src="/EventScheduling/resources/lib/jquery.leanModal.min.js"></script>
<link rel="stylesheet"
	href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css" />
<link rel="stylesheet"
	href="/EventScheduling/resources/css/font-awesome.min.css" />
<link type="text/css" rel="stylesheet"
	href="/EventScheduling/resources/css/style.css" />

</head>
<body>

	 <center>
		<c:forEach var="event" items="${events }">

			<table border="1">
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

					<td>Background</td>
					<td><img alt=" "
						src="/EventScheduling/displayimage?eventId=${event.eventID }"
						width="200" height="100"></td>
				</tr>
				<tr>
					<td colspan="3">
						<center>
							<c:if test="${sessionScope.userlogin.email==null }">

								<div class="container">
									<a id="modal_trigger" href="#modal" class="btn">Subscribe
										to this event </a>

									<div id="modal" class="popupContainer" style="display: none;">
										<header class="popupHeader"> <span
											class="header_title">Log in</span> <span class="modal_close"><i
											class="fa fa-times"></i></span> </header>

										<section class="popupBody"> <!-- Social Login -->
										<div class="social_login">
											<div class="">
												<a href="#" class="social_box fb"> <span class="icon"><i
														class="fa fa-facebook"></i></span> <span class="icon_title">Connect
														with Facebook</span>

												</a> <a href="#" class="social_box google"> <span
													class="icon"><i class="fa fa-google-plus"></i></span> <span
													class="icon_title">Connect with Google</span>
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
											<form method="post" action="subscribelogin">
												<label>Email / Username</label> <input name="email"
													type="text" /> <br /> <input name="eventID" type="hidden"
													value="${event.eventID }"> <label>Password</label>
												<input name="password" type="password" /> <br />

												<div class="checkbox">
													<input id="remember" type="checkbox" /> <label
														for="remember">Remember me on this computer</label>
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
											<form:form method="post" modelAttribute="user"
												action="subscriberegister">
												<input name="eventID" type="hidden"
													value="${event.eventID }">

												<label>First Name</label>
												<input name="firstName" type="text" />

												<br />
												<label>Last Name</label>
												<input name="lastName" type="text" />
												<br />

												<label>Email Address</label>
												<input name="email" type="text" />

												<br />

												<label>Password</label>
												<input name="password" type="password" />

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
							<c:if test="${sessionScope.userlogin.email!=null }">
							<c:if test="${event.status==false }">
								<c:if test="${sessionScope.userlogin.id !=event.userID}">
									<c:if test="${empty subscribers }">
										<c:if test="${empty  userSubscribe}">
											<form:form modelAttribute="subscribers" method="post"
												action="getsubscribe">
												<input name="eventID" type="hidden"
													value="${event.eventID }">
												<input name="subscriberEmail" type="hidden"
													value="${sessionScope.userlogin.email }">
												<button type="submit">Subscribe</button>
											</form:form>
										</c:if>
										<c:if test="${not empty  userSubscribe}">
											<form action="removesubscribe" method="post">
												<input name="eventID" type="hidden"
													value="${event.eventID }" />
												<button>Un subscribe</button>
											</form>
											<!-- <h3>Register to receive ticket </h3>
										<button>Register</button>  -->
										</c:if>
									</c:if>
									<c:if test="${not empty subscribers }">
								 you already have invitation to this event and accepted it
								 <br /> review your invitation
								</c:if>
								<form:form modelAttribute="acceptedsponsors" method="post"
												action="acceptoffer">

												<input type="hidden" name="eventID"
													value="${event.eventID }" />
												<input type="hidden" name="sponsorEmail"
													value="${sessionScope.userlogin.email }">
												<button>Be sponsor to this event</button>

											</form:form>
								</c:if>
								<c:if test="${sessionScope.userlogin.id ==event.userID}">
								you is the owner of this event
								</c:if>
								
								
								</c:if>
								
								<c:if test="${event.status==true }">this event is private</c:if>
							</c:if>
						</center>
					</td>
				</tr>
			</table>
			<br />
		</c:forEach>
	</center> 
</body>
</html>