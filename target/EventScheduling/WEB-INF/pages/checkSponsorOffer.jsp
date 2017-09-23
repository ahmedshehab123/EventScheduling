<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>Sponsor Offer</title>
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

			<c:if test="${not empty acceptedSponsor }">   
	get contact with event owner 
	<form action="contactowner" method="post">
					<input type="text" name="userMessage" /> <input type="hidden"
						name="eventID" value="${event.eventID }" />
					<button>send</button>
				</form>
			</c:if>
			<br />
			<br />
			<c:if test="${not empty acceptedSponsor }">
				<form:form method="post" modelAttribute="images"
					action="savesponsorimage" enctype="multipart/form-data">
					<input type="hidden" name="userID"
						value="${sessionScope.userlogin.id }" />
					<input type="hidden" name="eventID" value="${event.eventID }" />
					<input type="file" name="image" />
					<br />
					<button>Send file to event owner</button>
				</form:form>
				<%-- <c:forEach var="image" items="${images }">
                     <img alt="" src="displaysponsorimage?imageID=${image.imageID }">
                     
                </c:forEach>  --%>
			</c:if>
			<br />
			<br />
			<c:if test="${not empty acceptedSponsor }">
				<h2>
					There is another payment method i'm not decide how to do it but you
					can design it <br /> and i will implement it later
				</h2>
				<button>Choose Gold and pay100$</button>
				<button>Choose Silver and pay50$</button><br/>
				<button>Choose Bronze and pay 20$</button><br/>

			</c:if>
			<br />
			<br />




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
					<td>Event date and time</td>
					<td>${event.eventDateAndTime }</td>
				</tr>

				<tr>

					<td>Background</td>
					<td><img alt=" "
						src="/EventScheduling/displayimage?eventId=${event.eventID }"
						width="200" height="100"></td>
				</tr>
				<tr>
					<td>FAQ</td>
					<td><a
						href="/EventScheduling/displayfaq?eventId=${event.eventID }"
						target="_blank"> FAQ</a></td>
				</tr>
				<tr>
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
												<span> Use your Email address to register and there </span>
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
										<div style="position: relative; left: 30px;">
											<a href="redirecttobusinessregistration" id="register_form"
												class="btn">Sign up as business user</a>
										</div>
										</div>
										

										<!-- Username & Password Login form -->
										<div class="user_login">
											<form method="post" action="sponsorlogin">
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
												action="sponsoregister">
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

												<div class="checkbox">
													<input type="checkbox" name="businessUser" value="true" />

													<label style="font-size: 20px;">Register as
														business user</label>
												</div>

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
								

									<c:if test="${empty acceptedSponsor }">
									you have an offer to be a sponsor of this event <br />
										<form:form modelAttribute="acceptedsponsors" method="post"
											action="acceptoffer">

											<input type="hidden" name="eventID" value="${event.eventID }" />
											<input type="hidden" name="sponsorEmail"
												value="${sessionScope.userlogin.email }">
											<button>Accept Offer</button>

										</form:form>
									</c:if>
									<c:if test="${not empty acceptedSponsor }">
									You accepted offer <br />
										<form action="rejectOffer" method="post">
											<input type="hidden" name="eventID" value="${event.eventID }" />
											<button>Reject Offer</button>
										</form>
										<div style="position: relative; left: 200px; top: -20px;">
											<form action="social/facebook/signin" method="get">
												<input type="hidden" name="state" value="${event.eventID}">
												<button>Share on Facebook</button>
											</form>
											<form action="social/twitter/signin">
												<button>Share on Twitter</button>
											</form>
										</div>
									</c:if>

								


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