<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript"
	src="/EventScheduling/resources/lib/jquery-1.11.0.min.js"></script>
<script type="text/javascript"
	src="/EventScheduling/resources/lib/jquery.leanModal.min.js"></script>


<script type="text/javascript"
	src="/EventScheduling/resources/lib/jquery.min.js"></script>
	
<!-- <script type="text/javascript"
	src="/EventScheduling/resources/scripts/notification.js"></script> -->
	
	<link type="text/css" rel="stylesheet"
	href="/EventScheduling/resources/css/style.css" />
	
<!-- <link rel="stylesheet"
	href="/EventScheduling/resources/css/notification.css"> -->
	<!-- <link rel="stylesheet"
	href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css" /> -->
<!-- <link rel="stylesheet"
	href="/EventScheduling/resources/css/font-awesome.min.css" /> -->
<title>Insert title here</title>
</head>
<body>
<c:if test="${sessionScope.userlogin.email ==null }">

<a style="position: relative;left: 1000px;" href="loginpage"> Login</a>
<a style="position: relative;left: 1020px;" href="index"> Sign Up</a>
<a style="position: relative;left: 1040px;" href=""> FAQ</a>
</c:if>
<c:if test="${sessionScope.userlogin.email !=null }">


						

						
						
				<h4><a style="position: relative;left: 20px;" href="/EventScheduling/">Home</a>
				<a style="position: relative;left: 30px;" href="redirectToCreateEventPage">Create event</a>
				<a style="position: relative;left: 40px;" href="dipalyFriends">Friends List</a>
				<a style="position: relative;left: 50px;" href="accountingSetting">Account Setting</a>
				<a  style="position: relative;left: 420px;"href="displayevents">Review your events</a>
				<a  style="position: relative;left: 440px;"href="sendsponsorOffernotify">Review  Offers</a>
				<a  style="position: relative;left: 460px;" href="reviewinvitation">Review  Invitation</a>
				<a style="position: relative;left: 480px;" href="logout">Log out</a></h4>
			
		
	</c:if>
</body>
</html>