<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="css/bootstrap.css" type="text/css">
</head>
<style>
.fill { 
    max-height: 100%;
 	width: 50px;
    height: 100px;
}
</style>
<body>
<div class="row justify-content-md-right" style="height: 45px"></div>
<div class="row justify-content-md-end">
	
	<div class="col-md-auto">
		<a class="btn btn-primary" href="Login" role="button">Login</a>
	</div>
	<div class="col-md-3"></div>
</div>
<div class="mx-auto" style="max-width: 30rem;">
<form class="needs-validation" action="register" method="post">
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
			
			<!--  <p class="text-danger">${error}</p>-->
			<div class="form-group">
				<label class="control-label" for="username">Username</label>
				<input type="text" name="username" class="form-control" placeholder="Username" required>
			</div>
			<br>
			<div class="row justify-content-around" > 
				<div class="form-group col-md-auto"> 
					<label class="control-label" for= "firstname">First Name</label>
					<input type="text" name="firstname" class="form-control" placeholder="e.g. John" required>
				</div>
				<div class="form-group col-md-auto"> 
					<label class="control-label" for= "lastname">Last Name</label>
					<input type="text" name="lastname" class="form-control" placeholder="e.g. Doe" required>
				</div>
			</div>
			<br>
			<div class="form-group">
				<label class="control-label" for="password">Password</label>
				<input type="password" name="password" class="form-control" placeholder="Password" required>
			</div>
			<br>
			<div class="row">
			<div class="col-md-6">
			<button type="submit" class="btn btn-outline-primary">Sign Up</button>
			</div>
			<label class="control-label" for="username" style="color:red; padding: 10px;"><% if (request.getAttribute("errormessage")!= null) 
																	out.print(request.getAttribute("errormessage"));%>
			</label>
		</div>
		</div>
		<div class = "row fill"></div>
		
	</div>
	
</div>
</div>
</form>
</div>

<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>

</body>
</html>
