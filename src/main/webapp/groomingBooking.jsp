<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="nomi.model.user.PetModel"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="en">
<head>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">

<title>Booking details</title>
</head>
<body>
	<c:set var="petinfo" scope="session" value="${all_pet}" />
	<c:set var="userinfo" scope="session" value="${ownermodel}" />
	<div class="container">
		<h5 class="card-title">
			<u>Appointment booking</u>
		</h5>
		<br>
		
		<div class="mb-3">
			<label for="petname" class="form-label">Appointment date</label> <input
				type="date" class="form-control" id="dateinput"
				aria-describedby="apptDate" required>
		</div>
		<div class="mb-3">
			<div id="containerbutton" id="button">
				<label for="timeslot" class="form-label">Choose time slot</label><br>
				<button class="btn btn-outline-primary active" type="button"
					id="9AM" onclick="timeslot(this.id)">9AM</button>
				<button class="btn btn-outline-primary" type="button" id="10AM"
					onclick="timeslot(this.id)">10AM</button>
				<button class="btn btn-outline-primary" type="button" id="11AM"
					onclick="timeslot(this.id)">11AM</button>
				<button class="btn btn-outline-primary" type="button" id="12PM"
					onclick="timeslot(this.id)">12PM</button>
				<button class="btn btn-outline-primary" type="button" id="2PM"
					onclick="timeslot(this.id)">2PM</button>
				<button class="btn btn-outline-primary" type="button" id="3PM"
					onclick="timeslot(this.id)">3PM</button>
				<br>
			</div>
		</div>
		<button type="button" class="btn btn-warning" onclick="check()">Check
			availability</button>
		<br> <br>

		<div id="successbox" style="display: none;">
			<div class="alert alert-success" role="alert">Date is
				available!</div>
		</div>

		<div id="failbox" style="display: none;">
			<div class="alert alert-danger" role="alert">Sorry, date is not
				available</div>
		</div>

		<div class="mb-3">
			<label for="petname" class="form-label">Choose groom type</label> <select
				class="form-select" id="groom-type"
				aria-label="Default select example" required>
				<option selected>Groom type</option>
				<option value="basic">Basic</option>
				<option value="medicated">Medicated</option>
				<option value="full">Full grooming</option>
			</select>
		</div>


		<c:if test="${not empty petinfo}">
			<label class="form-label">Select your pet</label>
			<div class="scrollCard" id="petcard"
				style="overflow-y: scroll; height: 250px; width: 250px;">
				<c:forEach items="${all_pet}" var="pet" varStatus="petcount">
					<div class="card" style="width: 18rem;">
						<img
							src="https://i.pinimg.com/originals/44/e2/42/44e2422c7ecf1e9234c7fa4cdf03f060.jpg"
							class="img card-img-top">
						<div class="card-body" id="cardbody">
							<h5 class="card-title">
								<c:out value="${pet.petID}" />
							</h5>
							<div>
								<c:out value="${pet.petName}" />
							</div>
							<div>
								<c:out value="${pet.petType}" />
							</div>
							<c:choose>
								<c:when test="${petcount.index == 0}">
									<button class="btn btn-outline-primary active" type="button"
										id="${pet.petID}" onclick="selectPet(this.id)">select</button>
								</c:when>
								<c:when test="${petcount.index > 0}">
									<button class="btn btn-outline-primary" type="button"
										id="${pet.petID}" onclick="selectPet(this.id)">select</button>
								</c:when>
							</c:choose>
						</div>
					</div>
				</c:forEach>
			</div>
		</c:if>
		<c:if test="${empty petinfo}">
			<div class="alert alert-warning" role="alert">
				You haven't registered any pet yet<br>
				<a href="petregister.jsp">click here to register your pet</a>
			</div>
		</c:if>
		<br>
		<c:if test="${empty userinfo}">
			<button type="button" class="btn btn-success" onclick="confirm()" disabled>Confirm
			appointment</button>
			<p style="color:red;">please log in to make appointment</p>
		</c:if>
		<c:if test="${not empty userinfo}">
			<button type="button" class="btn btn-success" onclick="confirm()">Confirm
			appointment</button>
		</c:if>
		<br><br>
		<div id="bookingfail" style="display: none;">
			<div class="alert alert-danger" role="alert">fail to register your booking, please contact admin</div>
		</div>
		
		<br> <br>
	</div>
</body>
<style>
.container {
	padding-top: 50px;
	margin-left: auto;
	margin-right: auto;
	margin-bottom: auto;
	width: 450px;
}

.img {
	width: 100px;
}
</style>
<script type="text/javascript">
//Get the container element
var btnContainer = document.getElementById("containerbutton");
var petContainer = document.getElementById("petcard");

// Get all buttons with class="btn" inside the container
var btns = btnContainer.getElementsByClassName("btn");

//if petContainer is not null, then we get all the buttons inside petbtns
//if null, then there is no point in getting the buttons
if(petContainer != null)
	{
		var petbtns = petContainer.getElementsByClassName("btn");
	}

// get active buttons inside "card" container

// Loop through the buttons and add the active class to the current/clicked button
for (var i = 0; i < btns.length; i++) {
	btns[i].addEventListener("click", function() {
		var current = btnContainer.getElementsByClassName("active");
		current[0].className = current[0].className.replace(" active", "");
		this.className += " active";
	});
}

//this will ensure that all buttons are working even though the user has not registered any pet yet
if(petbtns != null && petContainer!=null)
	{
	for (var i = 0; i < petbtns.length; i++) {
		petbtns[i].addEventListener("click", function() {
			var current = petContainer.getElementsByClassName("active");
			current[0].className = current[0].className.replace(" active", "");
			this.className += " active";
		});
	}
	}


var time_slot = null;
var groomType = null;
var petID = null;
var successBox = document.getElementById('successbox');
var failBox = document.getElementById('failbox');
var bookingfail = document.getElementById('bookingfail');

function selectPet(petid) {
	petID = petid;
}

//ajax will be inside this confirm function?
function confirm() {
	alert("confirm() function is invoked!");
	
	//hide necessary div message box
	successBox.style.display = "none";
	failBox.style.display = "none";
	bookingfail.style.display ="none";
	
	//process the booking date first
	var bookDateString = document.getElementById('dateinput');

	//process the groom type selection
	var select = document.getElementById('groom-type');
	var selectedGroomType = select.options[select.selectedIndex].value;

	//alert the browser for testing purpose
	alert("booking date: " + bookDateString.value + "\n" + "timeslot: "
		+ time_slot + "\n" + "groom type: " + selectedGroomType + "\n"
		+ "pet ID: " + petID);
	
	var is_groomTypeSelected = selectedGroomType.localeCompare("Groom type");
	
	if(bookDateString.value && time_slot != null && petID != null
			&& is_groomTypeSelected != 0)
	{
		alert("confirming your bookings...");
		//ajax execution!
		$.ajax({
			url: 'gb-register',
			method: 'GET',
			data: {
				bookdate: bookDateString.value,
				timeslot: time_slot,
				groomtype: selectedGroomType,
				petID: petID
			},
			success: function(resultText /*resultText ni AJAX punya variable*/) {
				var message = JSON.stringify(resultText);
				let respondedMessage = JSON.parse(message);

				var is_success = respondedMessage
					.localeCompare("true");
				var is_fail = respondedMessage
					.localeCompare("false");

				if (is_success == 0) {
					alert("redirecting you to booking status page...");
					//window.location.replace("")
				} else if (is_fail == 0) {
					bookingfail.style.display = "block";
				} else {
					alert("something wrong with the server response text");
				}

			},
			error: function(jqXHR, exception) {
				alert('Error occured!!');
			}
		});
		
	}
	else if(!bookDateString.value)
	{
		alert("please enter booking date first");
	}
	else if(time_slot == null)
	{
		alert("please choose time slot first");
	}
	else if(is_groomTypeSelected == 0)
	{
		alert("please choose groom type first");
	}
	else if(petID == null)
	{
		alert("please select pet to groom");
	}
	

}

function check() {
	
	alert("check() function invoked!");
	successBox.style.display = "none";
	failBox.style.display = "none";
	var bookDateString = document.getElementById('dateinput');
	//then you pass bookingdate and timeslot to servlet, then return true if available, else otherwise

	//this is the correct conditional statement
	if (bookDateString.value && time_slot != null) {
		
		alert("date and timeslot is not null");
		$.ajax({
			url: 'gb-validation',
			method: 'GET',
			data: {
				bookingdate: bookDateString.value,
				timeslot: time_slot
			},
			success: function(resultText /*resultText ni AJAX punya variable*/) {
				var message = JSON.stringify(resultText);
				let respondedMessage = JSON.parse(message);

				var is_success = respondedMessage
					.localeCompare("true");
				var is_fail = respondedMessage
					.localeCompare("false");

				if (is_success == 0) {
					successBox.style.display = "block";
				} else if (is_fail == 0) {
					failBox.style.display = "block";
				} else {
					alert("could not load hidden message box");
				}

			},
			error: function(jqXHR, exception) {
				alert('Error occured!!');
			}
		});
	} else if (!bookDateString.value) {
		alert("please select appointment date first");
	} else if (time_slot === null) {
		alert("please select time slot first");
	} else {
		alert("everything is not true");
	}
	
}

function timeslot(ts) {
	time_slot = ts;
	successBox.style.display = "none";
	failBox.style.display = "none";
}
</script>
</html>