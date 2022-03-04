<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
<a id="add" href="addcustomer.jsp">Add Customer</a>
<br>
<form action="AddCustomer" method="post">
<table id="customers" style="width:100%">
  <tr>
   <th>Customer Id</th>
    <th>Customer Name</th>
    <th>Age</th>
    <th>Gender</th>
  </tr>
  
  <c:forEach items="${LoginController}" var="current1"> 
    <tr>
      <td><button type="submit"  name="id" value="<c:out value="${current1.key}" />"  formaction="addcustomer.jsp" ><c:out value="${current1.key}"/></button></td>
       <td><c:out value="${current1.value.getName()}" /></td>
       <td><c:out value="${current1.value.getAge()}" /></td>
       <td><c:out value="${current1.value.getGender()}" /></td>   
    </tr>
  </c:forEach>
</table>
</form>
</body>
</html>