<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<link rel="stylesheet" href="/EventScheduling/resources/css/popup.css" />
<link rel="stylesheet"
	href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css" />
<title>Register Page</title>
</head>
<body>
	<center>
		<form:form method="post" action="login">
			<table>
				<tr>
					<td>Email</td>
					<td><input name="email" type="text" />${usernameError }</td>

				</tr>
				<tr>
					<td>Password</td>
					<td><input name="password" type="password" />${passwordError }</td>
				</tr>
				<tr>

					<td><input type="submit" value="Login" /></td>
				</tr>
			</table>

		</form:form>

		<form:form method="post" modelAttribute="user" action="register">

			<table>
				<tr>
					<td>First Name</td>
					<td><form:input path="firstName" type="text" /></td>
					<td><form:errors path="firstName" /></td>
				</tr>
				<tr>
					<td>Last Name</td>
					<td><form:input path="lastName" type="text" /></td>
					<td><form:errors path="lastName" />
				</tr>
				<tr>
					<td>Email</td>
					<td><form:input path="email" type="text" /></td>
					<td><form:errors path="email" /> ${exist }</td>
				</tr>
				<tr>
					<td>Password</td>
					<td><form:input path="password" type="password" /></td>
					<td><form:errors path="password" /></td>
					
				</tr>
				
				<tr>

					<td><input type="submit" value="Submit"></td>
				</tr>
			</table>
		</form:form>
		<table>
			<tr>
				<td>
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
					</div>
				</td>
			</tr>
		</table>


	</center>
</body>
</html>
