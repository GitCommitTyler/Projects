<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Register</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js" integrity="sha384-SR1sx49pcuLnqZUnnPwx6FCym0wLsk5JZuNx2bPPENzswTNFaQU1RDvt3wT4gWFG" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.min.js" integrity="sha384-j0CNLUeiqtyaRmlzUHCPZ+Gy5fQu0dQ6eZ/xAww941Ai1SxSY+0EQqNXNE6DZiVc" crossorigin="anonymous"></script>       	
</head>
</head>
<style>
.fill { 
    max-height: 100%;
    height: 100px;
}
.error {
		color: red;
		font-style: italic;
	}
</style>
<body>
<div class="row justify-content-md-right" style="height: 45px"></div>
<div class="row justify-content-md-end">
	
	<div class="col-md-auto">
		<a class="btn btn-primary" href="login" role="button">Login</a>
	</div>
	<div class="col-md-3"></div>
</div>
<div class="mx-auto" style="max-width: 30rem;">
<form:form class="needs-validation" action="register" method="post" onchange="myFunction()" modelAttribute="user">
<div class="row align-items-start fill"></div>
<div class="container border 
			border-secondary rounded 
			shadow-lg p-3 mb-5 bg-white rounded">
	
	<div class="row align-items-start fill"></div>
	<div class="row align-items-center">
	<div class="col-md-7"></div>
	<div class="col-xs-auto">
		<div class="jumbotron">
			<h1 class="text-center">Sign Up</h1>
			<br>
			

    			<div class="form-group">
				<form:label path="userName" class="control-label" for="userName">Username</form:label>
				<form:input path="userName" type="text" name="userName" class="form-control" placeholder="Username"/>
				<form:errors path="userName" cssClass="error"/>
				</div>

			
			<br>
			
			<div class="row justify-content-around" > 
				<div class="form-group col-md-auto"> 
					<form:label path="firstName" class="control-label" for="firstName">First Name</form:label>
					<form:input path="firstName" type="text" name="firstName" class="form-control" placeholder="e.g. John"/>
					<form:errors path="firstName" cssClass="error"/>
				</div>
				
				<div class="form-group col-md-auto"> 
					<form:label path="lastName" class="control-label" for="lastName">Last Name</form:label>
					<form:input path="lastName" type="text" name="lastName" class="form-control" placeholder="e.g. Doe"/>
					<form:errors path="lastName" cssClass="error"/>
				</div>
			</div>
			<br>
			

    			<div class="form-group">
				<form:label path="email" class="control-label" for="email">Email</form:label>
				<form:input path="email" type="email" name="email" class="form-control" placeholder="email@domain.com"/>
				<form:errors path="email" cssClass="error"/>
			</div>
			<br>
			

    			<div class="form-group">
				<form:label path="password" class="control-label" for="password">Password</form:label>
				<form:input path="password" type="password" name="password" class="form-control" placeholder="Password"/>
				<form:errors path="password" cssClass="error" />
			</div>
			<br>
			<div class="row">
			<div class="col-md-6">
			<button type="submit" class="btn btn-outline-primary">Sign Up</button>
			</div>
			
		</div>
		</div>
		<div class = "row fill"></div>
		<script  type="text/javascript">
		function myFunction(){
			let form = document.getElementById("form");
			let data = new FormData(form);
			for (let entry of data) {
				console.log(entry);
			}
				}
		</script>
	</div>
	
</div>
</div>
</form:form>
</div>


</body>
</html>
