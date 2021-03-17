<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Find</title>
 
</head>
<body>
<h2>Find User By ID</h2>
	<form:form method="POST" 
          action="inputID" modelAttribute="user">
          <table>
                 <tr>
               <td><form:label path = "id">Input ID</form:label></td>
               <td><form:input path = "id" /></td>
            </tr>
            <tr>
               <td colspan = "2">
                  <input type = "submit" value = "Submit"/>
               </td>
	</table>
	</form:form>
</body>
</html>