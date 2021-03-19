<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="testform.js">
</script>

</head>

<body>

<h2>Feedback Test Form</h2>

<form:form method="POST" action="/submit" modelAttribute="feedback">
  <label for="fname">Name:</label><br>
  <input type="text" id="user" name="user" ><br>
  <label for="comment">Comment:</label><br>
  <input type="text" id="comments" name="comments" ><br>
  <label for="rating">Rating</label><br>
  <input type="number" id="rating" name="rating" min="1" max="100"><br><br>
  <input type="submit" value="Submit" >
</form:form> 


</body>
</html>
