<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="nomi.model.user.PetModel"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
	<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <title>View bookings</title>
  </head>
  <body>
  <c:set var="todayBooking" value="${todays_booking}" scope="session"/>
  <c:set var="staffinfo" value="${staffinfo}" scope="session"/>
    <nav class="navbar navbar-expand-lg bg-light sticky-top">
    <div class="container-fluid">
      <a class="navbar-brand" href="#"><c:out value="${staffinfo.name}"/></a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="ADMINviewBooking.jsp">Today's booking</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="ADMINviewReport.jsp">Report</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="ADMINviewFeedback.jsp">Feedbacks</a>
          </li>
        </ul>
        <div class="d-flex flex-row-reverse logout-container">
            <div id="logoutButton" class="p-2"><button type="button" class="btn btn-outline-dark logout-button">Log out</button></div>
          </div>
      </div>
    </div>
  </nav>
      
      
	<div class="alert alert-warning alert-dismissible fade show" role="alert" id="hiddenBox"
        		style="display:none;">
            <p id="hiddenMessage"><strong></p>
        </div>
	<div id="refreshableTable"> 
        <c:if test="${empty staffinfo}">
        	<div class="alert alert-danger" role="alert">
  					Please <a href="ADMINlogin.jsp" class="alert-link">login</a> to view appointments.
			</div>
        </c:if>
		<br>
        <nav class="navbar bg-light table-nav">
              <div class="container-fluid">
                <button type="button" class="btn btn-primary" id="buttonRefresh" onclick="refreshTable()">
                  <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-clockwise" viewBox="0 0 16 16">
                  <path fill-rule="evenodd" d="M8 3a5 5 0 1 0 4.546 2.914.5.5 0 0 1 .908-.417A6 6 0 1 1 8 2v1z"/>
                  <path d="M8 4.466V.534a.25.25 0 0 1 .41-.192l2.36 1.966c.12.1.12.284 0 .384L8.41 4.658A.25.25 0 0 1 8 4.466z"/>
                  </svg> Refresh list </button>
                <form class="d-flex" role="search">
                  <input class="form-control me-2" type="search" placeholder="Search customer" aria-label="Search">
                  <button class="btn btn-outline-success" type="submit">Search</button>
                </form>
              </div>
            </nav>

      <table class="table table-hover">
        <thead>
          <tr>
            <th scope="col">#ID</th>
            <th scope="col">Date</th>
            <th scope="col">Time</th>
            <th scope="col">Owner name</th>
            <th scope="col">Phone</th>
            <th scope="col">Pet name</th>
            <th scope="col">Groom type</th>
            <th scope="col">Groomer</th>
            <th scope="col">Status</th>
            <th scope="col">Charges</th>
            <th scope="col">Action</th>
          </tr>
        </thead>
        
        <tbody>
        
        <c:if test="${not empty staffinfo}">
        <c:forEach items="${todays_booking}" var="todayBooking">
          <tr>
            <td><c:out value="${todayBooking.bookingID}"/></td>
            <td><c:out value="${todayBooking.bookdate}"/></td>
            <td><c:out value="${todayBooking.timeslot}"/></td>
            <td><c:out value="${todayBooking.ownerName}"/></td>
            <td><c:out value="${todayBooking.ownerphone}"/></td>
            <td>
            
            <c:out value="${todayBooking.petName}"/>
            <!-- there is a hidden modal box (pop-up) in ever table row, which will appear upon button clicked -->
            	<div class="modal fade" id="p<c:out value="${todayBooking.bookingID}"/>" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                  <div class="modal-content">
                    <div class="modal-header">
                      <h5 class="modal-title" id="exampleModalLabel">Pet Details</h5>
                      <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                      Name: 
                      <c:out value="${todayBooking.petName}"/> <br>
                      Type:
                      <c:out value="${todayBooking.pettype}"/> <br>
                      Age Class: 
                      <c:out value="${todayBooking.petAgeClass}"/> <br>
                      Fur:
                      <c:out value="${todayBooking.petFurType}"/> <br>
                      Gender:
                      <c:out value="${todayBooking.petGender}"/> <br>
                    </div>
                    <div class="modal-footer">
                      <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    </div>
                  </div>
                </div>
              </div>
              <!-- end of modal code -->
            	<br>
            <button type="button" class="btn btn-success pet-btn-det" data-bs-toggle="modal" data-bs-target="#p<c:out value="${todayBooking.bookingID}"/>">info</button>
            
            </td>
            <td><c:out value="${todayBooking.groomtype}"/></td>
            <td><c:out value="${todayBooking.staffName}"/></td>
            <td><c:out value="${todayBooking.progress}"/></td>
            <td><c:out value="${todayBooking.charges}"/></td>
            <td>
                
                <div class="button-container">
                <button id="<c:out value="${todayBooking.bookingID}"/>"
                 type="button" class="btn btn-outline-success action-button" onclick="assignBooking(this.id)">assign</button>
                <button id="<c:out value="${todayBooking.bookingID}"/>"
                 type="button" class="btn btn-outline-success action-button" onclick="completeBooking(this.id)">complete</button>
                <button id="<c:out value="${todayBooking.bookingID}"/>"
                 type="button" class="btn btn-outline-success action-button" onclick="cancelBooking(this.id)">cancel</button>
                <button id="<c:out value="${todayBooking.bookingID}"/>"
                 type="button" class="btn btn-outline-success action-button" onclick="deleteBooking(this.id)">delete</button>
                </div>

            </td>
          </tr>
          </c:forEach>
          </c:if>
        </tbody>
        
      </table>
	</div>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

    
  </body>
  
  <script>
  
  var hiddenMessage = document.getElementById("hiddenMessage").innerText;
  var hiddenBox = document.getElementById("hiddenBox");
  
  	function cancelBooking(bookingID)
  	{
  		hiddenBox.style.display = "none";
  		
  		$.ajax({
			url: 'cancel-booking',
			method: 'GET',
			data: {
				bookingID: bookingID
			},
			success: function(resultText /*resultText ni AJAX punya variable*/) {
				alert("cancelling bookingID: " + bookingID);
				var message = JSON.stringify(resultText);
				var respondedMessage = JSON.parse(message);
				
				$('#refreshableTable').load(' #refreshableTable');
				document.getElementById("hiddenMessage").innerText = respondedMessage;
				hiddenBox.style.display = "block";
			},
			error: function(jqXHR, exception) {
				alert('Error occured!!');
			}
		});
  		
  	}
  	
  	
  	function refreshTable()
  	{
  		hiddenBox.style.display = "none";
  		var message = "null";
  		
  		alert("refreshing table...");
  		$.ajax({
			url: 'refresh-list',
			method: 'GET',
			data: {
				message: message
			},
			success: function(resultText /*resultText ni AJAX punya variable*/) {
				alert("Appointment list refreshed!");
				var message = JSON.stringify(resultText);
				var respondedMessage = JSON.parse(message);
				
				$('#refreshableTable').load(' #refreshableTable');
				
			},
			error: function(jqXHR, exception) {
				alert('Error occured!!');
			}
		});
  		
  	}
  	
  	function completeBooking(bookingID)
  	{
  		hiddenBox.style.display = "none";
  		
  		alert("updating booking for ID:" + bookingID);
  		$.ajax({
			url: 'complete-booking',
			method: 'GET',
			data: {
				bookingID: bookingID
			},
			success: function(resultText /*resultText ni AJAX punya variable*/) {
				alert("sent");
				var message = JSON.stringify(resultText);
				var respondedMessage = JSON.parse(message);
				
				$('#refreshableTable').load(' #refreshableTable');
				document.getElementById("hiddenMessage").innerText = respondedMessage;
				hiddenBox.style.display = "block";
			},
			error: function(jqXHR, exception) {
				alert('Error occured!!');
			}
		});
  	}
  	
  	
  	function assignBooking(bookingID)
  	{
  		hiddenBox.style.display = "none";
  		
  		alert("updating booking for ID:" + bookingID);
  		$.ajax({
			url: 'gb-assign',
			method: 'GET',
			data: {
				bookingID: bookingID
			},
			success: function(resultText /*resultText ni AJAX punya variable*/) {
				alert("sent");
				var message = JSON.stringify(resultText);
				var respondedMessage = JSON.parse(message);
				
				$('#refreshableTable').load(' #refreshableTable');
				document.getElementById("hiddenMessage").innerText = respondedMessage;
				hiddenBox.style.display = "block";
			},
			error: function(jqXHR, exception) {
				alert('Error occured!!');
			}
		});
  	}
  	
  	
  	
  </script>

  <style>

    #buttonRefresh{
        height: 30px;
        width: 120px;
        font-size: 15px;
        margin-left: 8px;
        line-height: 15px;
    }

	.alert{
		height : 50px;
        border-radius: 13px;
        margin-right: 8px;
        margin-left: 8px;
        margin-top: 5px;
        width: 500px;
	}
	
	.logout-button{
		margin-right: none;
	}
	
    .action-button{
        width: 70px;
        height: 18px;
        font-size: 12px;
        text-align: center;
        line-height: 6px;
        display:block;
        margin-bottom: 7px;
    }
    
    td{
    	font-weight:normal;
    }
    
    .pet-btn-det{
    	width: 50px;
      	height: 20px;
      	line-height: 2px;
    }

  </style>
</html>
    
    