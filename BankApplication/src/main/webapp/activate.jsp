<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
         <%@ page import="java.util.*" %>
    
<!DOCTYPE html>
<html>
<head>
 <link rel="stylesheet" href="css/style.css">
<meta charset="UTF-8">
<title>Activate</title>
</head>
<body>
<jsp:include page="adminmenu.jsp"></jsp:include>
<form action="activate" method="post">
&ensp;&ensp;<input type="submit"  class="btn btn-primary btn-lg btn-radius" formaction="Active" formmethod="post" class="btn btn-primary btn-lg btn-radius"   value="Activate">
<table id="customers" style="width:100%; margin-top: 0px; ">
  <tr>
  <th>Select</th>
    <th>Customer Id</th>
    <th>Account Number</th>
    <th>Customer Name</th>
    <th>Branch</th>  
    <th>Balance</th>
  </tr>
  
  <c:forEach items="${activeacc}" var="current">
    <c:forEach items="${current.value}" var="current1"> 
    <tr>
       <td><input type="checkbox" name="activate" value="${current1.key}"/>&nbsp;</td>
       <td><c:out value="${current.key}"/></td>
       <td><c:out value="${current1.key}" /></td>
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