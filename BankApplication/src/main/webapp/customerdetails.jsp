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
<script>
$("#id").click(function(){
    $("#name").trigger('click');

})

</script>
    <script>
//       function load() {	  
    	  <%String mes=request.getParameter("message");%>
<%--           var message="<%=mes%>"; --%>
//           if(message!='null')
//         	  {
//         	 alert(message);
//         	  }
// 	}
      
      </script>
</head>
<body onpageshow="load()">

<%	 

if (session.getAttribute("customerId") == null) {
	 response.sendRedirect(request.getContextPath() + "/login.jsp");		 
} %>
<jsp:include page="adminmenu.jsp"></jsp:include>
<a id="add" class="btn btn-primary btn-lg btn-radius" href="addcustomer.jsp">Add Customer</a>

<form action="AddCustomer" method="post">
<% if(mes!=null){out.print("&ensp;<label style=color:Red;><b>*"+mes+"</b></label>");}%>

<table id="customers" style="width:100%">
  <tr>
   <th>Customer Id</th>
    <th>Customer Name</th>
    <th>Age</th>
    <th>Gender</th>
  </tr>
  
  <c:forEach items="${LoginController}" var="current1"> 
    <tr>
      <td><button style=" height: 35px; width: 50px;" formaction="addcustomer.jsp?name=${current1.value.getName()}&age=${current1.value.getAge()}" class="btn btn-primary btn-lg btn-radius" type="submit" id="id" name="id" value="<c:out value="${current1.key}" />"  formaction="addcustomer.jsp" ><c:out value="${current1.key}" /></button></td>
       <td><c:out value="${current1.value.getName()}"  /></td>
       <td><c:out value="${current1.value.getAge()}" /></td>
       <td><c:out value="${current1.value.getGender()}" /></td>   
    </tr>
  </c:forEach>
</table>
</form>
</body>
</html>