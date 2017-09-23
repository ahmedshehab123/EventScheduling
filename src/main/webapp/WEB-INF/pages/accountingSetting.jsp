<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Setting</title>
<%@ include file="header.jsp"%>

<script type="text/javascript">
	$(function() {
		$("#email").hide();
		$("#fname").hide();
		$("#lname").hide();
		$("#workPhone").hide();
		$("#homePhone").hide();
		$("#workAddress").hide();
		$("#homeAddress").hide();
		$("#submit").hide();
		$("#cancel").hide();
		$("#edit").click(function(event) {
			$("#email").show();
			$("#fname").show();
			$("#lname").show();
			$("#workPhone").show();
			$("#homePhone").show();
			$("#workAddress").show();
			$("#homeAddress").show();
			$("#emailText").hide();
			$("#fnameText").hide();
			$("#lnameText").hide();
			$("#workPhoneText").hide();
			$("#homePhoneText").hide();
			$("#workAddressText").hide();
			$("#homeAddressText").hide();
			$("#edit").hide();
			$("#submit").show();
			$("#cancel").show();
			event.preventDefault();
		});
		$("#cancel").click(function(event){
			$("#email").hide();
			$("#fname").hide();
			$("#lname").hide();
			$("#workPhone").hide();
			$("#homePhone").hide();
			$("#workAddress").hide();
			$("#homeAddress").hide();
			$("#emailText").show();
			$("#fnameText").show();
			$("#lnameText").show();
			$("#workPhoneText").show();
			$("#homePhoneText").show();
			$("#workAddressText").show();
			$("#homeAddressText").show();
			$("#edit").show();
			$("#submit").hide();
			$("#cancel").hide();
			event.preventDefault();
		});

	});
	$(function(){
		$("#userimage").hide();
		$("#updatebtn").hide();
		$("#cancelupdateimg").hide();
		$("#updateimg").click(function(event){
			$("#userimage").show();
			$("#updatebtn").show();
			$("#cancelupdateimg").show();
			$("#updateimg").hide();
		});
		$("#cancelupdateimg").click(function(event){
			$("#userimage").hide();
			$("#updatebtn").hide();
			$("#cancelupdateimg").hide();
			$("#updateimg").show();
		});
	});
</script>
</head>
<body>
	<center>
	<%-- <c:if test="${ displayuserimage==null}">
	<img alt="" src="/EventScheduling/resources/images/avatar.png" width="200" height="100">
	</c:if> --%>
	<img alt="" src="displayuserimage" width="200" height="100">
	<form action="updateuserimage" enctype="multipart/form-data"
							method="post">
							
							<input id="userimage" type="file" name="userImage">

							<button id="updatebtn">Update</button></form>
							<a href="#" id="updateimg">update your profile photo</a>
							<a href="#" id="cancelupdateimg">cancel</a>
	<c:forEach var="user" items="${users }"> 
	<form action="updatesetting" method="post" autocomplete="off">
		<table border="1">
			<tr>
				<td>Email :</td>
				<td><span id="emailText">${user.email} </span> 
						
						<input type="text"  id="email" value="${user.email}" name="email">
					
					</td>

			</tr>
			<tr>
				<td>First name :</td>
				<td><span id="fnameText">${user.firstName}</span>
	                <input type="text" id="fname" value="${user.firstName}" name="fname">			
				</td>
				
			</tr>
			<tr>
				<td>Last name :</td>
				<td><span id="lnameText">${user.lastName}</span>
					<input type="text" id="lname" value="${user.lastName}" name="lname">
				</td>
				
			</tr>
			<tr> <td>Work Phone</td><td><span id="workPhoneText">${user.workPhone }</span>
			<input type="text" id="workPhone" value="${user.workPhone }" name="workPhone">
			
			</td></tr>
			<tr> <td>Home Phone</td><td><span id="homePhoneText">${user.homePhone }</span>
			<input type="text" id="homePhone" value="${user.homePhone }" name="homePhone">
			</td></tr>
			<tr> <td>Work Address</td><td><span id="workAddressText">${user.workAddress }</span>
			<input type="text" id="workAddress" value="${user.workAddress }" name="workAddress">
			</td></tr>
			<tr> <td>Home Address</td><td><span id="homeAddressText">${user.homeAddress }</span>
			<input type="text" id="homeAddress" value="${user.homeAddress }" name="homeAddress">
			</td></tr>
			<tr>
				<td></td>
				<td><a id="edit" href="#">Edit</a>
				    <button id="submit">Update</button><button id="cancel">cancel</button>
				</td>
			</tr>

		</table>
		</form>
		</c:forEach>
	</center>

</body>
</html>