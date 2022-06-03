<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE html>
<html>
<% 
	//this registration.jsp belongs to "Appointment booking system"
	String register_error = null;
	register_error = (String)session.getAttribute("register_error");

%>
<head>
<meta charset="ISO-8859-1">
<title>Account Registration</title>
</head>
<body>
<head>

</head>
<div id="login-box">
	<form action="user-registration" method="post" class="left">
		<h1>Sign up</h1>

		<input type="text" name="username" placeholder="Username" /> <input
			type="text" name="email" placeholder="E-mail" /> <input type="text"
			name="phone" placeholder="phone" /> <input type="password"
			name="password" placeholder="Password" /> <input type="password"
			name="password2" placeholder="Retype password" /> <input
			type="submit" name="signup_submit" value="Sign me up" />
		<p class="forgot" align="left">
			<a href="login.jsp">Already a user? </a>
		</p>
		<% if(register_error != null) { %>
		<div class="alert" onclick="this.style.display='none';">
		<span class="closebtn">&times;</span> <strong>Login fail!</strong>
		<%= register_error %>.
		</div>
		<% session.removeAttribute("register_error"); %>
		<% } %>
	</form>
	
</div>
</body>
<style>
@import url(https://fonts.googleapis.com/css?family=Roboto:400,300,500);

.alert {
	padding: 20px;
	background-color: #EC7063;
	color: white;
	opacity: 1;
	transition: opacity 0.6s;
	margin-bottom: 15px;
}

.closebtn:hover {
	color: black;
	cursor: pointer;
}

*:focus {
	outline: none;
}

body {
	margin: 0;
	padding: 0;
	background: #DDD;
	font-size: 16px;
	color: #222;
	font-family: 'Roboto', sans-serif;
	font-weight: 300;
}

#login-box {
	position: relative;
	margin: 5% auto;
	width: 600px;
	height: 650px;
	background: #FFF;
	border-radius: 2px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.4);
}

.left {
	position: absolute;
	top: 0;
	left: 0;
	box-sizing: border-box;
	padding: 40px;
	width: 300px;
	height: 400px;
}

h1 {
	margin: 0 0 20px 0;
	font-weight: 300;
	font-size: 28px;
}

input[type="text"], input[type="password"] {
	display: block;
	box-sizing: border-box;
	margin-bottom: 20px;
	padding: 4px;
	width: 220px;
	height: 32px;
	border: none;
	border-bottom: 1px solid #AAA;
	font-family: 'Roboto', sans-serif;
	font-weight: 400;
	font-size: 15px;
	transition: 0.2s ease;
}

input[type="text"]:focus, input[type="password"]:focus {
	border-bottom: 2px solid #16a085;
	color: #16a085;
	transition: 0.2s ease;
}

input[type="submit"] {
	margin-top: 28px;
	width: 120px;
	height: 32px;
	background: #16a085;
	border: none;
	border-radius: 2px;
	color: #FFF;
	font-family: 'Roboto', sans-serif;
	font-weight: 500;
	text-transform: uppercase;
	transition: 0.1s ease;
	cursor: pointer;
}

input[type="submit"]:hover, input[type="submit"]:focus {
	opacity: 0.8;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.4);
	transition: 0.1s ease;
}

input[type="submit"]:active {
	opacity: 1;
	box-shadow: 0 1px 2px rgba(0, 0, 0, 0.4);
	transition: 0.1s ease;
}

.or {
	position: absolute;
	top: 180px;
	left: 280px;
	width: 40px;
	height: 40px;
	background: #DDD;
	border-radius: 50%;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.4);
	line-height: 40px;
	text-align: center;
}

.right {
	position: absolute;
	top: 0;
	right: 0;
	box-sizing: border-box;
	padding: 40px;
	width: 300px;
	height: 400px;
	background: url('https://goo.gl/YbktSj');
	background-size: cover;
	background-position: center;
	border-radius: 0 2px 2px 0;
}
</style>
</html>