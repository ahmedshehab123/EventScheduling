<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Friends List</title>
<%@include file="header.jsp"%>
</head>
<body>
	<center>
		<form:form modelAttribute="friends" method="post" action="saveFriends">
			<input type="hidden" name="userID"
				value="${sessionScope.userlogin.id }" />
Friend email <input type="text" name="friendEmail" />
			<br />
			<button>save</button>
		</form:form>

		<c:if test="${empty friends }">
                         your friends list is empty add friends
        </c:if>
		<br /> <br />
		<table border="1">
			<c:forEach var="friend" items="${friends }">
				<tr>

					<td>${friend.friendEmail }</td>
					<td>
						<form action="deleteFriend" method="post">
							<input type="hidden" name="friendID" value="${friend.friendID }" />
							<button>Delete</button>
						</form>
					</td>
				</tr>

			</c:forEach>
			<tr>
				<td colspan="2"><center>
						<form action="clearlist" method="post">
							<button>clear list</button>
						</form>
					</center></td>
			</tr>
		</table>
	</center>
</body>
</html>