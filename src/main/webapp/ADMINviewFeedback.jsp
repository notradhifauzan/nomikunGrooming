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

    <title>View bookings</title>
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
              <a class="nav-link" href="ADMINviewReport.jsp">Report</a>
            </li>
            <li class="nav-item">
              <a class="nav-link  active" aria-current="page" href="ADMINviewFeedback.jsp">Feedbacks</a>
            </li>
          </ul>
          <div class="d-flex flex-row-reverse">
              <div id="logoutButton" class="p-2"><button type="button" class="btn btn-outline-dark">Log out</button></div>
            </div>
        </div>
      </div>
    </nav>
      <br>
      <nav class="navbar bg-light table-nav">
        <div class="container-fluid">
          <button type="button" class="btn btn-primary" id="buttonRefresh">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-clockwise" viewBox="0 0 16 16">
            <path fill-rule="evenodd" d="M8 3a5 5 0 1 0 4.546 2.914.5.5 0 0 1 .908-.417A6 6 0 1 1 8 2v1z"/>
            <path d="M8 4.466V.534a.25.25 0 0 1 .41-.192l2.36 1.966c.12.1.12.284 0 .384L8.41 4.658A.25.25 0 0 1 8 4.466z"/>
            </svg> Refresh list </button>
          <form class="d-flex" role="search">
            <input class="form-control me-2" type="search" placeholder="Search staff" aria-label="Search">
            <button class="btn btn-outline-success" type="submit">Search</button>
          </form>
        </div>
      </nav>
      <table class="table table-striped table-hover">
        <thead>
          <tr>
            <th scope="col">#ID</th>
            <th scope="col">Date</th>
            <th scope="col">Owner name</th>
            <th scope="col">Pet name</th>
            <th scope="col">Pet type</th>
            <th scope="col">Groom type</th>
            <th scope="col">Groomer</th>
            <th scope="col">Feedback</th>
            
          </tr>
        </thead>
        
        <tbody>
        
        <c:if test="${not empty staffinfo}">
        <c:forEach items="${todays_booking}" var="todayBooking">
          <tr>
            <td><c:out value="${todayBooking.bookingID}"/></td>
            <td><c:out value="${todayBooking.bookdate}"/></td>
            <td><c:out value="${todayBooking.ownerName}"/></td>
            <td><c:out value="${todayBooking.petName}"/></td>
            <td><c:out value="${todayBooking.pettype}"/></td>
            <td><c:out value="${todayBooking.groomtype}"/></td>
            <td><c:out value="${todayBooking.staffName}"/></td>
            <td> <div class="border border-success p-2 mb-2 border-opacity-25 overflow-auto rate-text rounded">I sent my dog to this pet shop to do basic grooming and scaling for the very first time. They done the job super fast, just less than 1 hour! And i very satisfied the result, my dog smells so good and the teeth very clean now!!</div></td>
          </tr>
          </c:forEach>
          </c:if>
        </tbody>
        
      </table>
	</div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

    
  </body>
  <style>
    #buttonRefresh{
        height: 30px;
        width: 120px;
        font-size: 15px;
        margin-left: 8px;
        line-height: 15px;
    }
    
    .rate-text{
    	height: 100px;
    	width: 150px;
    }
  </style>
</html>