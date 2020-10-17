<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film Query</title>
</head>
<body>
	<c:choose>
		<c:when test="${! empty film }">

			<form action="FilmsUpdate.do" method="POST">
				<label for="title">Title:</label>
				<input type="text" name="title" value="${film.title }" />
				<input type="submit" value="Update" />
			</form>
			<br>
			<form action="FilmsUpdate.do" method="POST">
				<label for="description">Description:</label>
				<input type="text" name="description" value="${film.description }" />
				<input type="submit" value="Update" />
			</form>
			<br>
			<form action="FilmsUpdate.do" method="POST">
				<label for="releaseYear">Release Year:</label>
				<input type="text" name="releaseYear" value="${film.releaseYear }" />
				<input type="submit" value="Update" />
			</form>
			<br>
			<form action="FilmsUpdate.do" method="POST">
				<label for=rentalDuration>Rental Duration:</label>
				<input type="text" name="rentalDuration" value="${film.rentalDuration }" />
				<input type="submit" value="Update" />
			</form>
			<br>
			<form action="FilmsUpdate.do" method="POST">
				<label for="rentalRate">Rental Rate:</label>
				<input type="text" name="rentalRate" value="${film.rentalRate }" />
				<input type="submit" value="Update" />
			</form>
			<br>
			<form action="FilmsUpdate.do" method="POST">
				<label for="replacementCost">Replacement Cost:</label>
				<input type="text" name="replacementCost" value="${film.replacementCost }" />
				<input type="submit" value="Update" />
			</form>
			<br>
			<form action="FilmsUpdate.do" method="POST">
				<label for="length">Replacement Cost:</label>
				<input type="text" name="length" value="${film.length }" />
				<input type="submit" value="Update" />
			</form>
			<br>

		</c:when>
		<c:otherwise>
			<p>No result</p>
		</c:otherwise>
	</c:choose>

</body>
</html>