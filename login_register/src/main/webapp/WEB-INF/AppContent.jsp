<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Things</title>
<%@ page import="com.hcl.data.entities.User" %>
<link rel="stylesheet" href="css/bootstrap.css" type="text/css">
<style>
.fill { 
    max-height: 100%;
    height: 100px;
}
.rotated {
  /*transform: rotateY(-25deg) translateX(20px) skewY(5deg) ;*/
   border: none;
  background-color: gray; /* For browsers that do not support gradients */
  
  
  
}
</style>
</head>
<body style="background-color: #262626">
<div class="row justify-content-md-right" style="height: 45px"></div>
<div class="row justify-content-md-end">
	
	<div class="col-md-auto">
		<a class="btn btn-primary" href="logout" role="button">Logout</a>
	</div>
	<div class="col-md-3"></div>
</div>
<div class="row fill"></div>
<div class="container">
	<div class="row">
		<%for(int i=0;i<4;i++){ %>
			<div class="col-md-3 ">
			<div class="card border-secondary rounded rotated shadow-lg p-6 mb-9 bg-body rounded">
			  <img class="card-img-top" src="pictures/Default-welcomer.png" alt="Card image cap">
			  <div class="card-body">
			    <p class="card-text">Hello, <% if (request.getAttribute("user")!= null) 
																	out.print(((User)request.getAttribute("user")).getFirstName());%> </p>
			  </div>

			</div>
			</div>
		<%} %>
	</div>
</div>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
</body>
</html>