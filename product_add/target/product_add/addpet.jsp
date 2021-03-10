<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Pet</title>
<link rel="stylesheet" href="styles.css">
</head>
<body style="height: 244px; ">
<form action="petadded" method="post" style="height: 222px; position: fixed; left: 50%; margin-left: -200px;"><p align="justify">
	 </p><label style="width: 96px; height: 20px">Name</label><input type="text" name="name" placeholder="e.g. Golden Retriever"></br>
	  <label style="width: 96px; height: 20px">Color</label><input type="text" name="color" placeholder="e.g. red"></br>
	 <label style="width: 97px; height: 20px">Price $</label><input type="number" step=0.01 name="price" placeholder="e.g. 5.00"></br>
	 <input type="submit" value="Submit Pet">
</form>
</body>
</html>