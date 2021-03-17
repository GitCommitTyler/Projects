<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<style>
table, th, td {
  border: 1px solid black;
}
</style>
</head>
<body>
<h2>Users</h2>

<table style="float:left">
   <tr><th>ID</th><th>Name</th><th>Email</th><th>Password</th></tr>
   <c:forEach items="${users2}" var="user2" varStatus="count">
    <tr id="${count.index}">
    	<td>${user2.id}</td>
        <td>${user2.name}</td>
        <td>${user2.email}</td>
        <td>${user2.password}</td>
    </tr>
  </c:forEach>
</table>


</body>
</html>
