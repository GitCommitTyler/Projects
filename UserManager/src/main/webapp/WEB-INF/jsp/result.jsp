<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Find</title>
 
</head>
<body>
	<h2>Edit User</h2>
	<form:form method="POST" 
          action="submitChange" modelAttribute="user">
          <table>
          	<tr>
               <td><form:label path = "id">Id</form:label></td>
               <td><form:input path = "id" value="${user.id}" readonly="true"/></td>
            </tr>
            <tr>
               <td><form:label path = "email">Edit Email</form:label></td>
               <td><form:input path = "email" /></td>
            </tr>
             <tr>
               <td><form:label path = "name">Edit Name</form:label></td>
               <td><form:input path = "name" /></td>
            </tr>
             <tr>
               <td><form:label path = "password">Edit Password</form:label></td>
               <td><form:input path = "password" /></td>
            </tr>
            <tr>
               <td colspan = "2">
                  <input type = "submit" value = "Submit"/>
               </td>
	</table>
	</form:form>
</body>
</html>