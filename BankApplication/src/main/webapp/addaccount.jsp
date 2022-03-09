<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Add Account</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
  font-family: Arial, Helvetica, sans-serif;
  background-color: #f2f2f2;
}
#accform{
height:100px;
width: 500px;
margin-left: 600px;
margin-top: 130px
}
</style>
  <script src="script/myscripts.js"></script>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="adminmenu.jsp"></jsp:include>
<form action="AddAccount" id="accform" method="post">
  <div class="container">
    <h1>Register</h1>
    <p>Please fill in this form to create an account.</p>
    <hr>

    <label for="email"><b>User Id</b></label>
    <input type="text" onkeypress="return isNumber(event)" placeholder="Enter Id" name="id" id="id" required>

    <label for="name"><b>Name</b></label>
    <input type="text" placeholder="Enter Name" name="name" id="name" required>
    
    <label for="branch"><b>branch</b></label>
<!--     <input type="text" placeholder="Enter branch" name="branch" id="branch" required> -->
<br>
<select class="btn btn-secondary dropdown-toggle" name="branch" id="branch">
  <option value="Karaikudi">Karaikudi</option>
  <option value="Coimbatore">Coimbatore</option>
  <option value="Madurai">Madurai</option>
  <option value="Trichy">Trichy</option>
</select>

    <hr>
    <button type="submit" class="registerbtn">Register</button>
    <input type="hidden" name="page" value="addaccount">

    <% %>
  </div>
  
</form>

</body>
</html>
