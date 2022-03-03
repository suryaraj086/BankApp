<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*" %>
     <%@ page import="db.*" %>
      <%@ page import="javax.servlet.*" %>
<!DOCTYPE html>
<html>
<head>
 <link rel="stylesheet" href="css/style.css">
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
</head>
<body>
<form action="LoginController" method="post">
<input type="submit" name="page" value="Account details">
<input type="submit" name="page" value="Customer details">
<input type="submit" name="page" value="Deposit">
<input type="submit" name="page" value="Withdraw">
<input type="submit" name="page" value="Transfer to another account">
<input type="submit" name="page" value="logout">
</form>
</body>
</html>