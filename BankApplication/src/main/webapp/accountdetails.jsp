<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page import="java.util.*" %>
     <%@ page import="db.*" %>
     <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     <%@ page import="javax.servlet.*" %>
      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Account Details</title>
 <link rel="stylesheet" href="css/style.css">
<style type="text/css">
#add
{
float:right;
}
</style>
</head>
<body>


<jsp:include page="adminmenu.jsp"></jsp:include>
<a id="add" href="addaccount.jsp">Add account</a>
<form action="Deactivate" method="post">
<input type="submit" value="Deactivate">

<br>



<table id="customers" style="width:100%">
  <tr>
  <th>Select</th>
    <th>Customer Id</th>
    <th>Account Number</th>
    <th>Customer Name</th>
    <th>Branch</th>  
    <th>Balance</th>
     
  </tr>

  <c:forEach items="${LoginController}" var="current">
    <c:forEach items="${current.value}" var="current1"> 
    <tr>
       <td><input type="checkbox" name="name" value="${current1.key}"/>&nbsp;</td>
       <td><c:out value="${current.key}"/></td>
       <td><c:out value="${current1.key}"/></td>
       <td><c:out value="${current1.value.getName()}" /></td>
       <td><c:out value="${current1.value.getBranch()}" /></td>
       <td><c:out value="${current1.value.getBalance()}" /></td>

    </tr>
  </c:forEach>
 </c:forEach>
 
</table>
</form>
</body>
</html>