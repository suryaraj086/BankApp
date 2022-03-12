<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bank Transfer</title>
<link rel="stylesheet" href="css/style.css"> 
 <script src="script/myscripts.js"></script>

<style>
body {
  font-family: Arial, Helvetica, sans-serif;
  background-color: #f2f2f2;
}
#transfer{
height: 100px;
width: 500px;
margin-left: 650px;
margin-top: 90px
}
</style>
<script>
function validate() {
	var a=document.getElementById("fromaccount").value;
	var b=document.getElementById("toaccount").value;
	if(a==b)
		{
		document.getElementById("err").innerText="*Two accounts cannot be same";
		return false
		}
	else
		{return true}
	
}
</script>
</head>
<body>
<%	 

if (session.getAttribute("customerId") == null) {
	 response.sendRedirect(request.getContextPath() + "/login.jsp");		 
} %>
<jsp:include page="adminmenu.jsp"></jsp:include>
<br>
<form action="TransactionServlet" onsubmit="return validate()" id="transfer" method="post">
  <div class="container">
    <h2>Bank Transfer</h2>
    <hr>
<h4>From Account</h4>
<!--     <label for="id"><b>User Id</b></label> -->
<!--     <input type="text" placeholder="Enter Id" name="fromid" id="fromid" required> -->

    <label for="account"><b>Account Number</b></label>
    <input type="text" onkeypress="return isNumber(event)" placeholder="Enter Account Number" name="fromaccount" id="fromaccount" required>
    
    <h4>To Account</h4>
<!--     <label for="id"><b>User Id</b></label> -->
<!--     <input type="text" placeholder="Enter Id" name="toid" id="toid" required> -->

    <label for="account"><b>Account Number</b></label>
    <input type="text" onkeypress="return isNumber(event)" placeholder="Enter Account Number" name="toaccount" id="toaccount" required>

    <hr>
 <label for="branch"><b>Amount</b></label>
    <input type="text" onkeypress="return isNumber(event)" placeholder="Enter Amount" name="amount" id="amount" required>
    <button type="submit" class="registerbtn">SUBMIT</button>
           <%Object err=request.getAttribute("errorMessage"); if(err!=null){out.print("<label style=color:red; >*"+err+"</label>");}%>
    <label style="color: red;" id=err ></label>
  </div>
  
</form>
</body>
</html>