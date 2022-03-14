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
margin-left: 650px;
margin-top: 115px
}
</style>
  <script src="script/myscripts.js"></script>
<link rel="stylesheet" href="css/style.css">

</head>
<body>
<%	 

if (session.getAttribute("customerId") == null) {
	 response.sendRedirect(request.getContextPath() + "/login.jsp");		 
} %>
<jsp:include page="adminmenu.jsp"></jsp:include>
<form action="AddAccount" id="accform" method="post">
  <div class="container">
    <h1>Register</h1>
    <p>Please fill in this form to create an account.</p>
    <hr>
<%String acc= request.getParameter("accountnumber");%>
<input type="hidden" name="accountnumber" value="<%out.print(acc); %>">
    <label for="email"><b>User Id</b></label>
    <input type="text" onkeypress="return isNumber(event)" placeholder="Enter Id" name="id" value="<%if(acc!=null){out.print(request.getParameter("id"));}%>" id="id" required>

    <label for="name"><b>Name</b></label>
    <input type="text"  onkeypress="return isLetter()" placeholder="Enter Name" name="name" value="<%if(acc!=null){out.print(request.getParameter("name"));}%>" id="name" required>
    
    <label for="branch"><b>branch</b></label>
<!--     <input type="text" placeholder="Enter branch" name="branch" id="branch" required> -->
<br>
<select class="btn btn-secondary dropdown-toggle" name="branch" style="width: 100%;" id="branch">
<%if(acc!=null){%><option value="<%out.print(request.getParameter("branch"));%>"><% out.print(request.getParameter("branch"));%></option><%}%>
  <option value="Karaikudi">Karaikudi</option>
  <option value="Coimbatore">Coimbatore</option>
  <option value="Madurai">Madurai</option>
  <option value="Trichy">Trichy</option>
</select>

    <hr>
    <button type="submit" class="registerbtn">Register</button>
    <input type="hidden" name="page" value="addaccount">

    <% %>
    <label style="color: red;" id="err"></label>
  </div>
  
</form>

</body>
</html>
