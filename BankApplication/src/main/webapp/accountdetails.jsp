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

      <script type = "text/javascript">
        
            function getConfirmation() {
               var retVal = confirm("Do you want to continue ?");
               if( retVal == true ) {
                  
                  return true;
               }
               else {
                 
                  return false;
               }
            }
        
      </script>
</head>
<body>
<jsp:include page="adminmenu.jsp"></jsp:include>
<a id="add" href="addaccount.jsp" class="btn btn-primary btn-lg btn-radius" >Add account</a>
<form action="Deactivate" method="post">
&ensp;&ensp;<input type="submit" class="btn btn-primary btn-lg btn-radius"  onclick = "return getConfirmation();" value="Deactivate">
<input type="submit" name="page" formaction="Active" formmethod="post" class="btn btn-primary btn-lg btn-radius"   value="Activate">
<table id="customers" style="width:100%; margin-top: 0px; ">
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
        <c:if test="${current1.value.isStatus()}">
    
       <td><input type="checkbox" name="name" value="${current1.key}"/>&nbsp;</td>
       <td><c:out value="${current.key}"/></td>
       <td><button style=" height: 35px; width: 50px;" class="btn btn-primary btn-lg btn-radius" type="submit" id="id" name="id" value="<c:out value="${current1.key}" />"  formaction="addaccount.jsp" ><c:out value="${current1.key}" /></button></td>
       <td><c:out value="${current1.value.getName()}" /></td>
       <td><c:out value="${current1.value.getBranch()}" /></td>
       <td><c:out value="${current1.value.getBalance()}" /></td>
         </c:if>
  
    </tr>
  </c:forEach>
 </c:forEach>
</table>
</form>
</body>
</html>