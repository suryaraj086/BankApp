<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Login Page</title>
<meta charset="UTF-8">
<style>

input[type=text],input[type=password],input[type=number] {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

input[type=submit],input[type=reset] {
  width: 100%;
  background-color: #4CAF50;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 15px;
}

input[type=submit]:hover,input[type=reset]:hover {
  background-color: #45a049;
}

div {
  margin-top: 200px;
  margin-left: 40%;
  margin-right:40%;
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
</style>
<script>
// const pageAccessedByReload = (
// 		  (window.performance.navigation && window.performance.navigation.type === 1) ||
// 		    window.performance
// 		      .getEntriesByType('navigation')
// 		      .map((nav) => nav.type)
// 		      .includes('reload')
// 		);
<%-- 		<%Object err=null;%> --%>
// 		alert(pageAccessedByReload);
</script>
</head>
<body id="myFrame"  background="images/background.png"  >
<div>
  <form action="LoginController" method="post">
  <img src="images/1144709.png" style="margin-left:35%; height:80px; width:80px"/>
  <br/>
    <label for="fname">User ID</label>
    <input type="number" id="fname" name="id" placeholder="Enter the id">
    <label for="lname">Password</label>
    <input type="password" id="lname" name="password"  placeholder="Enter the password">
    <input type="submit"  value="Submit">
     <input type="reset" value="Reset">
     <input type="hidden" value="login" name="page">
     <%Object err=request.getAttribute("errorMessage"); if(err!=null){out.print("<label style=color:red; >*"+err+"</label>");}%>
  </form>
</div>
</body>
</html>