<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="nomi.model.user.OwnerModel" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body>
<% 
//this home.jsp belongs to "Appointment booking system"
	OwnerModel owner = new OwnerModel();
	owner = null;
	owner = (OwnerModel)session.getAttribute("ownermodel");
	
%>

<% if(owner == null) { //insert html element below %>
	hello, you haven't signed in yet
	
	<a href="petSelector">pet selector using JS DOM experiment</a>
	
	
	<% /*insert html element above*/} else { //insert html element below %>
	
	hello <%= owner.getUsername() %>
	
	<% /*insert html element above*/ } %>


<a href="groomingBooking.jsp">click here to make grooming appointment</a>
</body>
</html>











