<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="nomi.model.user.PetModel" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<meta charset="ISO-8859-1">
<title>Pet selection (All pet)</title>
</head>
<body>

<h2>Choose your pet to groom</h2>


<div class="row">
<c:forEach items= "${all_pet}" var="pet">
  <div class="column">
    <div class="card">
    <img src="https://logowik.com/content/uploads/images/t_cat8600.jpg" class="card-img-top" alt="Paris" width="300" height="300">
      <h3 id="petID"> <c:out value="${pet.petID}"/> </h3>
      <p id="petName"><c:out value="${pet.petName}"/></p>
      <p id="petType"><c:out value="${pet.petType}"/></p>
      <input id="${pet.petID}" type="button" class="button" value="Groom my pet" onclick="selectPet(this.id)">
    </div>
  </div>
  
  </c:forEach>
 
  </div>

</body>

<script>

function selectPet(petid)
{
	alert(petid)
}
</script>

<style>
* {
  box-sizing: border-box;
}

body {
  font-family: Arial, Helvetica, sans-serif;
}

/* Float four columns side by side */
.column {
  float: left;
  width: 25%;
  padding: 0 10px;
}

/* Remove extra left and right margins, due to padding */
.row {margin: 0 -5px;}

/* Clear floats after the columns */
.row:after {
  content: "";
  display: table;
  clear: both;
}

/* Responsive columns */
@media screen and (max-width: 600px) {
  .column {
    width: 100%;
    display: block;
    margin-bottom: 20px;
  }
}

/* Style the counter cards */
.card {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
  padding: 16px;
  text-align: center;
  background-color: #f1f1f1;
}
img {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  border: 1px solid black;
}
.button {
  background-color: #4CAF50;
  border: none;
  color: white;
  padding: 3px 3px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 2px 2px;
  cursor: pointer;
}
</style>

</html>