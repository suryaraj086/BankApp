<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*" %>
     <%@ page import="db.*" %>
      <%@ page import="javax.servlet.*" %>
      
<!DOCTYPE html>
<html>
<head>
 <link rel="stylesheet" href="css/style.css">
  <link rel="stylesheet" href="css/buttons.css">
   <link rel="stylesheet" href="css/styles.css">
   <link rel="stylesheet" href="css/bootstrap.min.css">
<meta charset="UTF-8">
<title>Admin Details</title>
<style>
input[type=submit] {
  background-color: #4CAF50; /* Green */
  border: none;
  color: white;
  padding: 15px 32px;
  text-align: center;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
  transition-duration: 0.4s;
  text-decoration: none;
  font-family: sans-serif;
}
body{
background-color: #E0E0E0;
}
</style>
<%	 

if (session.getAttribute("customerId") == null) {
	 response.sendRedirect(request.getContextPath() + "/login.jsp");		 
} %>
</head>

<body>
<nav class="navbar navbar-dark bg-dark">
<form action="LoginController" method="post">
<input type="submit" name="page" class="btn btn-primary btn-lg btn-radius" value="Account details">
<input type="submit" name="page" class="btn btn-primary btn-lg btn-radius"  value="Customer details">
<input type="submit" name="page" class="btn btn-primary btn-lg btn-radius" value ="Deposit/Withdraw">
<input type="submit" name="page" class="btn btn-primary btn-lg btn-radius" value="Transfer to another account">
<input type="submit" name="page" class="btn btn-primary btn-lg btn-radius"  value="logout">
</form>
</nav>
</body>
</html>