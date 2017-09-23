<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Business user registration</title>
<%@include file="header.jsp"%>

</head>
<body>

	<center>
		<h4>take advantage of great additional features and save even
			more time</h4>
	<h2>	first you will paid for this service</h2><br/>
		<form:form method="post" modelAttribute="user" action="registerBusinessUser">

			<table>
				<tr>
					<td>First Name</td>
					<td><input name="firstName" type="text" /></td>
					<td><form:errors path="firstName" /></td>
				</tr>
				<tr>
					<td>Last Name</td>
					<td><input name="lastName" type="text" /></td>
					<td><form:errors path="lastName" />
				</tr>
				<tr>
					<td>Email</td>
					<td><input name="email" type="text" /></td>
					<td><form:errors path="email" />${exist }</td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input name="password" type="password" /></td>
					<td><form:errors path="password" /></td>
				</tr>
				<tr>
					<td><input type="hidden" name="businessUser" value="true" /></td>
				</tr>
				<tr>
					<td>Add your bank account</td>
					<td><input type="text" name="cardnumber"  />${invalidcardnumber }${paiderror }</td>
					<td><form:errors path="cardnumber" /></td>
				</tr>
				<tr>
					<td colspan="2"><center><input type="submit" value="Subscriber with 30$ per year"></center></td>
				</tr>
			</table>
		</form:form>
	</center>
</body>
</html>