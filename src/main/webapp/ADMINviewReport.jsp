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

    <title>Booking report</title>
  </head>
  <body>
    <nav class="navbar navbar-expand-lg bg-light sticky-top">
      <div class="container-fluid">
        <a class="navbar-brand" href="#"><c:out value="${staffinfo.name}"/></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
              <a class="nav-link" href="ADMINviewBooking.jsp">Today's booking</a>
            </li>
            <li class="nav-item">
              <a class="nav-link active" aria-current="page" href="ADMINviewReport.jsp">Report</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="ADMINviewFeedback.jsp">Feedbacks</a>
            </li>
          </ul>
          <div class="d-flex flex-row-reverse">
              <div id="logoutButton" class="p-2"><button type="button" class="btn btn-outline-dark">Log out</button></div>
            </div>
        </div>
      </div>
    </nav>
    <br>
    <c:if test="${empty staffinfo}">
        	<div class="alert alert-danger alert-login" role="alert">
  					Please <a href="ADMINlogin.jsp" class="alert-link">login</a> to view appointments.
			</div>
        </c:if>
      <div id="refreshableTable"> 
        <nav class="navbar bg-light table-nav">
          <div class="container-fluid">
            <button type="button" class="btn btn-primary" id="buttonRefresh">
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

      <table class="table table-striped table-hover">
        <thead>
          <tr>
            <th scope="col">#ID</th>
            <th scope="col">Date</th>
            <th scope="col">Time</th>
            <th scope="col">Owner name</th>
            <th scope="col">Phone</th>
            <th scope="col">Pet name</th>
            <th scope="col">Pet type</th>
            <th scope="col">Groom type</th>
            <th scope="col">Groomer</th>
            <th scope="col">Status</th>
            <th scope="col">Charges</th>
          </tr>
        </thead>
        
        <tbody>
        
        <c:if test="${not empty staffinfo}">
        <c:forEach items="${completed_booking}" var="completeBooking">
          <tr>
            <td><c:out value="${completeBooking.bookingID}"/></td>
            <td><c:out value="${completeBooking.bookdate}"/></td>
            <td><c:out value="${completeBooking.timeslot}"/></td>
            <td><c:out value="${completeBooking.ownerName}"/></td>
            <td><c:out value="${completeBooking.ownerphone}"/></td>
            <td><c:out value="${completeBooking.petName}"/></td>
            <td><c:out value="${completeBooking.pettype}"/></td>
            <td><c:out value="${completeBooking.groomtype}"/></td>
            <td><c:out value="${completeBooking.staffName}"/></td>
            <td><c:out value="${completeBooking.progress}"/></td>
            <td><c:out value="${completeBooking.charges}"/></td>
          </tr>
          </c:forEach>
          </c:if>
        </tbody>
        
      </table>

	</div>
	
	<footer class="fixed-bottom sticky-xl-bottom">
  		<button type="button" class="btn btn-success btn-sales">Generate sales report</button>
  		<br>
  		<div class="alert alert-success sales-container" role="alert">
   		 Daily : RM XXX.XX <br> Weekly : RM XXX.XX <br> Monthly : RM XXXX.XX
  		</div>
  	</footer>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

    
  </body>
  <style>
  
  .alert-login{
		height : 50px;
        border-radius: 13px;
        margin-right: 8px;
        margin-left: 8px;
        margin-top: 5px;
        width: 500px;
	}

    .sales-container{
      width:300px;
      margin-left: 20px;
      margin-top: 20px;
    }

    #buttonRefresh{
        height: 30px;
        width: 120px;
        font-size: 15px;
        margin-left: 8px;
        line-height: 15px;
    }

    .btn-sales{
      margin-left: 20px;
    }
  </style>
</html>