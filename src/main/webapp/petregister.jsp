<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="nomi.model.user.PetModel"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="petregister.js"></script>

<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<title>Pet Registration</title>
</head>
<body>
	<c:set var="petinfo" scope="session" value="${all_pet}" />
	<c:set var="userinfo" scope="session" value="${ownermodel}" />
	
	<div id="ownerid" style="display:block;">
	<c:out value="${userinfo.userID}"/>
	</div>
	
	<div class="formContainer">
			<h5 class="card-title">
				<u>Register your pet</u>
			</h5>
			<br>
			<div class="mb-3">
				<label for="petname" class="form-label">Your pet name</label> <input
					type="text" class="form-control" id="petname" name="petname" required
					aria-describedby="petname">
			</div>

			<div class="mb-3">
				<label for="petgender" class="form-label">Pet gender</label>
				<div class="form-check">
					<input class="form-check-input" type="radio" name="gender"
						value="male" required> <label class="form-check-label"
						for="male"> Male </label>
				</div>
				<div class="form-check">
					<input class="form-check-input" type="radio" name="gender"
						value="female" required> <label class="form-check-label"
						for="female"> Female </label>
				</div>
			</div>

			<div class="pet-type-container mb-3">
				<label for="pettype" class="form-label">Pet type</label>
				<div class="form-check">
					<input class="form-check-input" type="radio" name="pettype"
						id="cat" required> <label class="form-check-label"
						for="cat"> Cat </label>
				</div>
				<div class="form-check">
					<input class="form-check-input" type="radio" name="pettype"
						id="dog" required> <label class="form-check-label"
						for="dog"> Dog </label>
				</div>
			</div>

			<div class="mb-3">
				<select class="form-select" id="fur-type" name="furtype" required>
					<option>Fur type</option>
					<option value="short">Short</option>
					<option value="medium">Medium</option>
					<option value="long">Long</option>
				</select>
			</div>

			<div class="mb-3">
				<select class="form-select" id="age-class" name="ageclass" required>
					<option>Age class</option>
					<option value="junior">Junior</option>
					<option value="adult">Adult</option>
				</select>
			</div>
			<c:if test="${empty userinfo}">
				<button type="submit" class="btn btn-primary" disabled>Register
					my pet</button>
				<p style="color: red;">please log in to register your pet</p>
			</c:if>
			<c:if test="${not empty userinfo}">
				<button type="submit" class="btn btn-primary" onclick="register()">Register my
					pet</button>
			</c:if>

			<div id="failbox" style="display: none;">
				<div class="alert alert-danger" role="alert">Your pet name already exist, please try other name</div>
			</div>
			
	</div>

</body>

<style>
.formContainer {
	padding-top: 100px;
	margin-left: auto;
	margin-right: auto;
	margin-top: auto;
	margin-bottom: auto;
	width: 450px;
}
</style>
</html>