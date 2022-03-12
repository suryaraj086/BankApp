<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bank Transfer</title>
<link rel="stylesheet" href="css/style.css">
 <link rel="stylesheet" href="css/style.css">
  <script src="script/myscripts.js"></script>
   <link rel="stylesheet" href="css/styles.css">
   <link rel="stylesheet" href="css/bootstrap.min.css">
<style>
<%Object err;%>
body {
  font-family: Arial, Helvetica, sans-serif;
  background-color: #f2f2f2;
}

#form{
height:100px;
width: 500px;
margin-left: 600px;
margin-top: 70px;
}
</style>
<script>
function validate() {
	var a=document.getElementById("acc").value;
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
<jsp:include page="customermenu.jsp"></jsp:include>
<form action="TransactionServlet" onsubmit=" return validate()"  id="form" method="post">
  <div class="container">
    <h3>Bank Transfer</h3>
    <hr>
<h4>From Account</h4>
<!--     <label for="id"><b>User Id</b></label> -->
<!--     <input type="text" placeholder="Enter Id" name="fromid" id="fromid" required> -->
<% Object id=request.getSession().getAttribute("customerId");%>
<input type="hidden" name="fromid" value="<%out.print(id); %>"> 
<input type="hidden" name="customer" value="customer">
<label for="account"><b>Account Number</b></label>
<!--     <input type="text" placeholder="Enter Account Number" name="fromaccount" id="fromaccount" required> -->
<%HttpSession sess= request.getSession();%>
<%Map arr=(Map)sess.getAttribute("acc");%>
<%Object[] array=arr.keySet().toArray(); %>
<br>
    <select name="fromaccount" class="btn btn-primary dropdown-toggle" name="cars" id="acc">
    <%for(int i=0;i<array.length;i++){ %>
  <option  value="<% out.print(array[i]);%>"><% out.print(array[i]);%></option>
     <% }%>
</select>
<br>
<br>
    <h4>To Account</h4>
<!--     <label for="id"><b>User Id</b></label> -->
<!--     <input type="text" placeholder="Enter Id" name="toid" id="toid" required> -->

    <label for="account"><b>Account Number</b></label>
    <input type="text" placeholder="Enter Account Number" onkeypress="return isNumber(event)" name="toaccount" id="toaccount" required>

    <hr>
 <label for="branch"><b>Amount</b></label>
    <input type="text" onkeypress="return isNumber(event)" placeholder="Enter Amount" name="amount" id="amount" required>
    <button type="submit" class="registerbtn">SUBMIT</button>
           <% err=request.getAttribute("errorMessage"); if(err!=null){out.print("<label id=err style=color:red; >*"+err+"</label>");}%>
    <label style="color:red;" id=err></label>
  </div>
</form>
</body>
</html>