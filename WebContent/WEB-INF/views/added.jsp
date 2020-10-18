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
			Film added with ID ${film.id }<br>
			<a href="Films.do?filmId=${film.id}">View and edit this new film</a>
		
		</c:when>
		<c:otherwise>
			Unable to add film
		</c:otherwise>
	</c:choose>

</body>
</html>