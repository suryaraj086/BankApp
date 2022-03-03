<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bank Transfer</title>
<link rel="stylesheet" href="css/style.css">
<style>
body {
  font-family: Arial, Helvetica, sans-serif;
  background-color: #f2f2f2;
}
#transfer{
height:100px;
width: 500px;
margin-left: 600px;
margin-top: 20px
}
</style>
</head>
<body>
<jsp:include page="adminmenu.jsp"></jsp:include>
<form action="TransactionServlet" id="transfer" method="post">
  <div class="container">
    <h1>Bank Transfer</h1>
    <hr>
<h2>From Account</h2>
    <label for="id"><b>User Id</b></label>
    <input type="text" placeholder="Enter Id" name="fromid" id="fromid" required>

    <label for="account"><b>Account Number</b></label>
    <input type="text" placeholder="Enter Account Number" name="fromaccount" id="fromaccount" required>
    
    <h2>To Account</h2>
    <label for="id"><b>User Id</b></label>
    <input type="text" placeholder="Enter Id" name="toid" id="toid" required>

    <label for="account"><b>Account Number</b></label>
    <input type="text" placeholder="Enter Account Number" name="toaccount" id="toaccount" required>

    <hr>
 <label for="branch"><b>Amount</b></label>
    <input type="text" placeholder="Enter Amount" name="amount" id="amount" required>
    <button type="submit" class="registerbtn">SUBMIT</button>
  </div>
  
</form>
</body>
</html>