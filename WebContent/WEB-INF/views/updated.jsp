<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Database Update</title>
</head>
<body>
	<c:choose>
		<c:when test="${! empty film }">
			Film updated
		
		
		</c:when>
		<c:otherwise>
			Unable to update film
		</c:otherwise>
	</c:choose>
	<a href="Films.do?filmId=${updatedFilmId }">Return</a>

</body>
</html>