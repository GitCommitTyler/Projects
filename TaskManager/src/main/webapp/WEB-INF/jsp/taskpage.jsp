<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

}
</style>
<title>Your Tasks</title>
</head>
<body>
<script>

</script>
<label  class="input-group-text lead" for="">Upcoming Tasks</label>
<div class="row justify-content-around" style="height: '100px'">
<c:forEach items="${week}" var="day" varStatus="count">
	<div class="col-md-auto">
		<div class="card" style="width: 15rem; border: none;">
		  <div class="card-body">
		    <h5 class="card-title">${day}</h5>
		    <h6 class="card-subtitle mb-2 text-muted">    
      </h6>
      		
      		<c:forEach items="${dateMap.entrySet()}" var="entry" varStatus="i">
      			<c:if test= "${entry.getKey() == day}">
      			<div class="accordion" id="accordion${i.index}">
      			<c:forEach items="${entry.getValue()}" var="task" varStatus="j">

      				<div class="accordion-item" >
      					<h2 class="accordion-header" id="heading${i.index}${j.index}">
      					<c:if test="${j.index == 0}">
      				      	<button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse${i.index}${j.index}" aria-expanded="false" aria-controls="collapse${i.index}${j.index}">
       							<p class="card-text"> ${task.taskName}</p>
     			 			</button>
     			 		</h2>
     			 		<div id="collapse${i.index}${j.index}" class="accordion-collapse collapse" aria-labelledby="heading${i.index}${j.index}" data-bs-parent="#accordion${i.index}">
      						<div class="accordion-body">
      					</c:if>
      					<c:if test="${j.index != 0}">
      					<button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse${i.index}${j.index}" aria-expanded="false" aria-controls="collapse${i.index}${j.index}">
       							<p class="card-text"> ${task.taskName}</p>
     			 			</button>
     			 		</h2>
     			 		<div id="collapse${i.index}${j.index}" class="accordion-collapse collapse" aria-labelledby="heading${i.index}${j.index}" data-bs-parent="#accordion${i.index}">
      						<div class="accordion-body">
				    	<p>
      					
      					</c:if>
				    	<p>
				    		${task.description}
				    	</p>
				    	<p>
				    		Start Date: ${task.startDate}
				    	</p>
				    	<p>
				    		End Date: ${task.endDate}
				    	</p>
				    	<p>
				    		Priority: ${task.severity}
				    	</p>
					</p>
					<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal${i.index}${j.index}">
  						Update Task
					</button>
					</div>
					</div>
					
					</div>
					<div class="modal fade" id="exampleModal${i.index}${j.index}" tabindex="-1" aria-labelledby="exampleModalLabel${i.index}${j.index}" aria-hidden="true">
					  <div class="modal-dialog">
					    <div class="modal-content">
					      <div class="modal-header">
					        <h5 class="modal-title" id="exampleModalLabel${i.index}${j.index}">Update Task</h5>
					        
					        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					      </div>
					      <div class="modal-body">
						      <div class="row justify-content-md-right" style="height: 45px"></div>
								<div class="mx-auto" style="max-width: 30rem;">
								<form:form class="needs-validation" action="updatetasks" method="post" onchange="myFunction()" modelAttribute="task" id="form">
									<div class="input-group mb-3">
									 	<div class="input-group-prepend">
											<form:label path="taskId" class="input-group-text" for="taskId">Task ID</form:label>
										</div>
										<form:input path="taskId" type="text" name="taskId" class="form-control" value= "${task.taskId}" readonly="true"></form:input>
									</div>
									
									<div class="input-group mb-3">
									 	<div class="input-group-prepend">
											<form:label path="taskName" class="input-group-text" for="taskName">Task Name</form:label>
										</div>
										<form:input path="taskName" type="text" name="taskName" class="form-control" value= "${task.taskName}"></form:input>
										<form:errors path="taskName" cssClass="error"/>
									</div>
									
									<div class="input-group mb-3">
										<div class="input-group-prepend">
											<form:label path="description" class="input-group-text" for="taskName">Task Description</form:label>
										</div>
										<form:input path="description" type="text" name="description" class="form-control" value= "${task.description}"></form:input>
										<form:errors path="description" cssClass="error"/>
									</div>
									
									<div class="input-group mb-3">
									 	<div class="input-group-prepend">
											<form:label path="startDate" class="input-group-text" for="startDate">Start Date</form:label>
										</div>
										<form:input path="startDate" type="date" name="startDate" class="form-control" value= "${task.startDate}"></form:input>
										<form:errors path="startDate" cssClass="error"/>
									</div>
									
									<div class="input-group mb-3">
									 	<div class="input-group-prepend">
											<form:label path="endDate" class="input-group-text" for="endDate" value="${task.endDate}">End Date</form:label>
										</div>
										<form:input path="endDate" type="date" name="endDate" class="form-control"></form:input>
										<form:errors path="endDate" cssClass="error"/>
									</div>
									
									<div class="input-group mb-3">
									  <div class="input-group-prepend">
									    <form:label path="severity" class="input-group-text" for="severity">Severity</form:label>
									  </div>
									  <form:select path="severity" class="custom-select" name="severity">
									  <option selected value="${task.severity}">Choose...</option>
									  <% for(Severity level : Severity.values()){ %>
									    
									    <option value=<%=level%>><%out.print(level.toString());%></option>
									    <%} %>
								
									  </form:select>
									  <form:errors path="severity" cssClass="error"/>
									</div>

								</form:form>
							      <div class="modal-footer">
									<div class="row">
										<div class="col-md-6">
												<button form="form" type="submit" formaction="deletetask" class="btn btn-danger">Delete</button>
										</div>
										<div class="col-md-6">
											<button form="form" type="submit" class="btn btn-outline-primary">Save</button>
										</div>
									</div>
								</div>
								</div>
					      </div>

					      
					    </div>
					  </div>
					</div>
					
				</c:forEach>
				</div>
				</c:if>
			</c:forEach>
		  </div>
		</div>
	</div>
</c:forEach>
<div class="row justify-content-start" style="height: 45px"></div>
<div class="row justify-content-start" style="height: 45px">
<div class="col-md-3">
<a type="button" class="btn btn-primary" href="addtasks">add Task</a>
</div>
</div>
<div class="container" style="padding-top: 20px">
<label  class="input-group-text lead" for="">All Tasks</label>
<table class="table">
  <thead>
    <tr>
      <th scope="col">ID</th>
      <th scope="col">Task</th>
      <th scope="col">Start Date</th>
      <th scope="col">End Date</th>
      <th scope="col">Severity</th>
      <th scope="col">Description</th>
      <th scope="col"></th>
      <th scope="col"></th>
    </tr>
  </thead>
  <tbody>
  	<c:forEach items="${allTasks}" var="task">
  	<form:form method="post" action="deletetask" methodAttribute="task" id="tableform${task.taskId}"></form:form>
    <tr>
      <th scope="row"><form:input path="task.taskId" form="tableform${task.taskId}" readonly="true" value="${task.taskId}"></form:input></th>
      <td><form:input path="task.taskName" form="tableform${task.taskId}" readonly="true" value="${task.taskName}"></form:input></td>
      <td><form:input path="task.startDate" form="tableform${task.taskId}" readonly="true" value="${task.startDate}"></form:input></td>
      <td><form:input path="task.endDate" form="tableform${task.taskId}" readonly="true" value="${task.endDate}"></form:input></td>
      <td><form:input path="task.severity" form="tableform${task.taskId}" readonly="true" value="${task.severity}"></form:input></td>
      <td><form:input path="task.description" form="tableform${task.taskId}" readonly="true" value="${task.description}"></form:input></td>
      <td>
      <button class="btn btn-danger" form="tableform${task.taskId}" type="submit" >Delete</button>
      </td>
    </tr>
    </c:forEach>
  </tbody>
</table>
</div>
</body>
