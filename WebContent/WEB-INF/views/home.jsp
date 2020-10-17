<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Films</title>
</head>
<body>

<h3>Find a Film</h3>
	<form action="Films.do" method="GET">
		Film ID:
		<input type="number" name="filmId"/> 
		<input type="submit" value="Get Film" />
	</form>

</body>
</html>