<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Details</title>
 <link rel="stylesheet" href="css/style.css">
</head>
<body>

<a href="banktransfer.jsp">Transfer to another account</a>
<a href="login.jsp">logout</a>
<table id="customers" style="width:100%">
  <tr>
    <th>Account Number</th>
    <th>Branch</th>
    <th>Balance</th>
  </tr>
  <c:forEach items="${userMap}" var="current1"> 
    <tr>
      <td><c:out value="${current1.key}"/></td>
      <td><c:out value="${current1.value.getBranch()}" /></td>    
       <td><c:out value="${current1.value.getBalance()}" /></td>
       
    </tr>
  </c:forEach>
</table>
</body>
</html>