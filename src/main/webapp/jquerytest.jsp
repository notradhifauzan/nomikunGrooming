<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="nomi.model.user.OwnerModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="jqueryIsFun.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
</script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>User detail retriever</title>
<c:set var="userinfo" scope="session" value="${userinfo}" />
</head>
<body>

	find me information of user, without reloading this page. go!
	<br>
	<br>
	<input type="text" id="username" placeholder="Username" />
	<input type="button" class="button" value="go!" onclick="callServlet()" />

	<div id="hideDiv">
		<c:if test="${not empty userinfo}">
			<div class="alert alert-primary" id="result" role="alert">
				<p id="here">
					hello
					<c:out value="${userinfo.username}" />
				</p>
			</div>
		</c:if>
		<!-- set null everytime user info fetched -->
		<c:set var="userinfo" scope="session" value="${null}" />
	</div>
</body>

<style>
.alert {
	display: block;
	width: 150px;
}
</style>
</html>