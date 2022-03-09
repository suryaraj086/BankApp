<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="customermenu.jsp"></jsp:include>
<table id="customers" style="width:100%">
  <tr>
    <th>Account Number</th>
    <th>Branch</th>
    <th>Balance</th>
  </tr>
  <c:forEach items="${userMap}" var="current1"> 
    <tr>
    <c:if test="${current1.value.isStatus()}">
      <td><input type="hidden" name="acc" value="${current1.key}"><c:out value="${current1.key}"/></td>
      <td><c:out value="${current1.value.getBranch()}" /></td>    
      <td><c:out value="${current1.value.getBalance()}" /></td> 
             </c:if>
      
    </tr>
  </c:forEach>
</table>
</body>
</html>