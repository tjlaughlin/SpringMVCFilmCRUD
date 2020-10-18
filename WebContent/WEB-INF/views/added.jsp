<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Film</title>
</head>
<body>
	<c:choose>
		<c:when test="${! empty film }">
			Film added
			<br>
			${film.id }
		
		</c:when>
		<c:otherwise>
			Unable to add film
		</c:otherwise>
	</c:choose>
	<a href="Films.do?filmId=${film.id }">Continue</a>

</body>
</html>