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
</style>
</head>

<body>

<form action="LoginController" method="post">
<input type="submit" name="page" value="Account details">
<input type="submit" name="page" value="Customer details">
<a href="debitorcredit.jsp">Deposit</a>
<a href="debitorcredit.jsp">Withdraw</a>
<a href="banktransferadmin.jsp">Transfer to another account</a>
<a href="login.jsp">logout</a>
</form>
</body>
</html>