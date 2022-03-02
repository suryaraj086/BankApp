<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Add Customer</title>
<link rel="stylesheet" href="css/style.css">
<style>

body {
  font-family: Arial, Helvetica, sans-serif;
  background-color: #f2f2f2;
}

form{
height:100px;
width: 500px;
margin-left: 600px;
margin-top: 80px;
}
</style>
</head>
<body>
<a href="adminmenu.jsp">Home</a>
<form action="AddCustomer" method="post">
  <div class="container">
    <h1>Register</h1>
    <p>Please fill in this form to create an Customer Id</p>
    <hr>
<%-- <% String id=(String)request.getParameter("id"); out.print(id);%> --%>
 
    <label for="name"><b>Name</b></label>
    <input type="text" placeholder="Enter Name" name="name" id="name" required>
 
       <label for="age"><b>Age</b></label>
    <input type="text" placeholder="Enter Age" name="age" id="age" required>
    
   &nbsp; <input type="radio" id="male" name="gender" value="male" required>
  <label for="deposit">male</label><br>
  <input type="radio" id="female" name="gender" value="female" required>
  <label for="withdraw">female</label>
    <br><br>
     <label for="psw"><b>Password</b></label>
    <input type="password" placeholder="Enter Id" name="password" id="password" required>

     <label for="psw"><b>Confirm Password</b></label>
    <input type="password" placeholder="Enter Id" name="confirmpassword" id="psw" required>
    <hr>
<%String s=request.getParameter("id");%>
<input type="hidden" name="userId" value="<%out.print(s);%>">
    <button type="submit" class="registerbtn">Register</button>
  </div>
  
</form>

</body>
</html>
