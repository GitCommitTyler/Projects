<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js" integrity="sha384-SR1sx49pcuLnqZUnnPwx6FCym0wLsk5JZuNx2bPPENzswTNFaQU1RDvt3wT4gWFG" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.min.js" integrity="sha384-j0CNLUeiqtyaRmlzUHCPZ+Gy5fQu0dQ6eZ/xAww941Ai1SxSY+0EQqNXNE6DZiVc" crossorigin="anonymous"></script>       	
<%@ page import="com.example.TaskManager.entities.Severity" %>


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
<title>Add Task</title>
</head>
<body>
<div class="row justify-content-md-right" style="height: 45px"></div>
<div class="mx-auto" style="max-width: 30rem;">
<h2>Add Task</h2>
<form:form class="needs-validation" action="addtasks" method="post" onchange="myFunction()" modelAttribute="task" id="form">
	<div class="input-group mb-3">
	 	<div class="input-group-prepend">
			<form:label path="taskName" class="input-group-text" for="taskName">Task Name</form:label>
		</div>
		<form:input path="taskName" type="text" name="taskName" class="form-control"></form:input>
		<form:errors path="taskName" cssClass="error"/>
	</div>
	
	<div class="input-group mb-3">
		<div class="input-group-prepend">
			<form:label path="description" class="input-group-text" for="description">Task Description</form:label>
		</div>
		<form:input path="description" type="text" name="description" class="form-control"></form:input>
		<form:errors path="description" cssClass="error"/>
	</div>
	
	<div class="input-group mb-3">
	 	<div class="input-group-prepend">
			<form:label path="startDate" class="input-group-text" for="startDate">Start Date</form:label>
		</div>
		<form:input path="startDate" type="date" name="startDate" class="form-control"></form:input>
		<form:errors path="startDate" cssClass="error"/>
	</div>
	
	<div class="input-group mb-3">
	 	<div class="input-group-prepend">
			<form:label path="endDate" class="input-group-text" for="endDate">End Date</form:label>
		</div>
		<form:input path="endDate" type="date" name="endDate" class="form-control"></form:input>
		<form:errors path="endDate" cssClass="error"/>
	</div>
	
	<div class="input-group mb-3">
	  <div class="input-group-prepend">
	    <form:label path="severity" class="input-group-text" for="severity">Severity</form:label>
	  </div>
	  <form:select path="severity" class="custom-select" name="severity">
	  <option selected>Choose...</option>
	  <% for(Severity level : Severity.values()){ %>
	    
	    <option value=<%=level%>><%out.print(level.toString());%></option>
	    <%} %>

	  </form:select>
	  <form:errors path="severity" cssClass="error"/>
	</div>
	
	<div class="row">
		<div class="col-md-6">
			<button type="submit" class="btn btn-outline-primary">Save</button>
		</div>
	</div>
<!-- 	<script  type="text/javascript">
		function myFunction(){
			let form = document.getElementById("form");
			let data = new FormData(form);
			for (let entry of data) {
				console.log(entry);
			}
				}
		</script> -->
	
</form:form>
</div>
</body>
</html>