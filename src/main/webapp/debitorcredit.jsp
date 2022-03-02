<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/style.css">
<style>
body {
  font-family: Arial, Helvetica, sans-serif;
  background-color: #f2f2f2;
}

form{
height:100px;
width: 500px;
margin-left: 650px;
margin-top: 130px
}

</style>
</head>
<body>

<a href="adminmenu.jsp">Home</a>

<form action="DebitCredit" method="post">

  <div class="container">
    <h1>Deposit/Withdraw</h1>
    <hr>

    <label for="id"><b>User Id</b></label>
    <input type="text" placeholder="Enter Id" name="id" id="id" required>

    <label for="account"><b>Account Number</b></label>
    <input type="text" placeholder="Enter Account Number" name="accountnumber" id="accountnumber" required>
    
  &nbsp;   <input type="radio" id="deposit" name="debitorcredit" value="deposit" required>
  <label for="deposit">Deposit</label><br>
  <input type="radio" id="withdraw" name="debitorcredit" value="withdraw" required>
  <label for="withdraw">Withdraw</label><br>
    <br>
    <label for="branch"><b>Amount</b></label>
    <input type="text" placeholder="Enter Amount" name="amount" id="amount" required>
    <hr>
    <button type="submit" class="registerbtn">SUBMIT</button>
  </div>
</form>

</body>
</html>
