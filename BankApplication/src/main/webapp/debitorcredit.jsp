<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/style.css">
<script src="script/myscripts.js"></script>
<style>
body {
  font-family: Arial, Helvetica, sans-serif;
  background-color: #f2f2f2;
}

#debtcrdt{
height:100px;
width: 500px;
margin-left: 650px;
margin-top: 115px;
}

</style>
</head>
<body>
<%	 

if (session.getAttribute("customerId") == null) {
	 response.sendRedirect(request.getContextPath() + "/login.jsp");		 
} %>
<jsp:include page="adminmenu.jsp"></jsp:include>
<form action="DebitCredit" id="debtcrdt" method="post">

  <div class="container">
    <h2>Deposit/Withdraw</h2>
    <hr>

<!--     <label for="id"><b>User Id</b></label> -->
<!--     <input type="text" onkeypress="return isNumber(event)" placeholder="Enter Id" name="id" id="id" required> -->

    <label for="account"><b>Account Number</b></label>
    <input type="text" onkeypress="return isNumber(event)" placeholder="Enter Account Number" name="accountnumber" id="accountnumber" required>
    
  &nbsp;   <input type="radio" id="deposit" name="debitorcredit" value="deposit" required>
  <label for="deposit">Deposit</label><br>
  <input type="radio" id="withdraw" name="debitorcredit" value="withdraw" required>
  <label for="withdraw">Withdraw</label><br>
    <br>
    <label for="branch"><b>Amount</b></label>
    <input type="text" onkeypress="return isNumber(event)" placeholder="Enter Amount" name="amount" id="amount" required>
    <hr>
    <button type="submit" class="registerbtn">SUBMIT</button>
               <%Object err=request.getAttribute("errorMessage"); if(err!=null){out.print("<label style=color:red; >*"+err+"</label>");}%>
    <label id="err" style="color: red;"></label>
  </div>
</form>
</body>
</html>
