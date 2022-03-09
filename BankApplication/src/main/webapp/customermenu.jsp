<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Details</title>
 <link rel="stylesheet" href="css/style.css">
 <link rel="stylesheet" href="css/buttons.css">
   <link rel="stylesheet" href="css/styles.css">
   <link rel="stylesheet" href="css/bootstrap.min.css">
   <style>
   body{
background-color: #E0E0E0;
}
</style>
</head>
<body>
<nav class="navbar navbar-dark bg-dark">
<form action="action" method="post">
<button type="submit" class="btn btn-primary btn-lg btn-radius" name="page" value="Customer menu" formaction="LoginController" formmethod="post">Account Details</button>
<a href="banktransfer.jsp" class="btn btn-primary btn-lg btn-radius">Transfer to another account</a>
<a href="login.jsp" class="btn btn-primary btn-lg btn-radius">logout</a>
<!-- <table id="customers" style="width:100%"> -->
<!--   <tr> -->
<!--     <th>Account Number</th> -->
<!--     <th>Branch</th> -->
<!--     <th>Balance</th> -->
<!--   </tr> -->
<%--   <c:forEach items="${userMap}" var="current1">  --%>
<!--     <tr> -->
<%--       <td><c:out value="${current1.key}"/></td> --%>
<%--       <td><c:out value="${current1.value.getBranch()}" /></td>     --%>
<%--       <td><c:out value="${current1.value.getBalance()}" /></td>  --%>
<!--     </tr> -->
<%--   </c:forEach> --%>
<!-- </table> -->
</form>
</nav>
</body>
</html>