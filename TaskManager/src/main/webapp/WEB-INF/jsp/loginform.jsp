<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js" integrity="sha384-SR1sx49pcuLnqZUnnPwx6FCym0wLsk5JZuNx2bPPENzswTNFaQU1RDvt3wT4gWFG" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.min.js" integrity="sha384-j0CNLUeiqtyaRmlzUHCPZ+Gy5fQu0dQ6eZ/xAww941Ai1SxSY+0EQqNXNE6DZiVc" crossorigin="anonymous"></script>       	
</head>
<style>
.fill { 
    max-height: 100%;
    height: 100px;
}
</style>
<body>
	<div class="row justify-content-md-right" style="height: 45px"></div>
	<div class="row justify-content-md-end">
		
		<div class="col-md-auto">
			<a class="btn btn-primary" href="register" role="button">Sign Up</a>
		</div>
		<div class="col-md-3"></div>
	</div>
	

<div class="mx-auto" style="max-width: 30rem;">
<form action="login" method="post">
<div class="row align-items-start fill">

</div>
<div class="container border 
			border-secondary rounded 
			shadow-lg p-3 mb-5 bg-white rounded">
	<div class="row align-items-start fill"></div>
	<div class="row align-items-center">
	<div class="col-md-3"></div>
	<div class="col-md-6 col-xs-12">
		<div class="jumbotron">
			<h1 class="text-center">Login</h1>
			<br>
			
			 <%-- <p class="text-success">"${success}"</p> --%>
			<div class="form-group">
				<label class="control-label" for="username">Username</label>
				<input type="text" name="username" class="form-control" placeholder="Username" required>
			</div>
			<br>
			<div class="form-group">
				<label class="control-label" for="password">Password</label>
				<input type="password" name="password" class="form-control" placeholder="Password" required>
			</div>
			<br>
			<div class="row">
			<div class="col-md-6">
			<button type="submit" class="btn btn-outline-warning" value="Login">Login</button>
			</div>
			<label class="control-label" for="username" style="color:red; padding: 10px;">
			</label>
		</div>
		</div>
		<div class = "row fill"></div>
	</div>
</div>
</div>
</form>
</div>

</body>
</html>
