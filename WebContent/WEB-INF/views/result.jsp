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

			Film ID: ${film.id }
			<br>
			<form action="FilmsUpdate.do" method="POST">
				<label for="title">Title:</label> <input type="text" name="title"
					value="${film.title }" /> <input type="hidden" name="id"
					value="${film.id }" /> <input type="submit" value="Update" />
			</form>
			<br>
			<form action="FilmsUpdate.do" method="POST">
				<label for="description">Description:</label> <input type="text"
					name="description" value="${film.description }" size="100" /> <input
					type="hidden" name="id" value="${film.id }" /> <input type="submit"
					value="Update" />
			</form>
			<br>
			<form action="FilmsUpdate.do" method="POST">
				<label for="releaseYear">Release Year:</label> <input type="text"
					name="releaseYear" value="${film.releaseYear }" /> <input
					type="hidden" name="id" value="${film.id }" /> <input type="submit"
					value="Update" />
			</form>
			<br>
			<form action="FilmsUpdate.do" method="POST">
				<label for=rentalDuration>Rental Duration:</label> <input
					type="text" name="rentalDuration" value="${film.rentalDuration }" />
				<input type="hidden" name="id" value="${film.id }" /> <input
					type="submit" value="Update" />
			</form>
			<br>
			<form action="FilmsUpdate.do" method="POST">
				<label for="rentalRate">Rental Rate:</label> <input type="text"
					name="rentalRate" value="${film.rentalRate }" /> <input
					type="hidden" name="id" value="${film.id }" /> <input type="submit"
					value="Update" />
			</form>
			<br>
			<form action="FilmsUpdate.do" method="POST">
				<label for="replacementCost">Replacement Cost:</label> <input
					type="text" name="replacementCost" value="${film.replacementCost }" />
				<input type="hidden" name="id" value="${film.id }" /> <input
					type="submit" value="Update" />
			</form>
			<br>
			<form action="FilmsUpdate.do" method="POST">
				<label for="length">Length:</label> <input type="text" name="length"
					value="${film.length }" /> <input type="hidden" name="id"
					value="${film.id }" /> <input type="submit" value="Update" />
			</form>
			
			<!-- categories -->
			Categories:<br>
			<c:forEach var="cat" items="${film.categories }">${cat}<br></c:forEach>

			<!-- actors -->
			<br>
			<table>
				<caption>Actors</caption>
				<thead>
					<tr>
						<th>ID</th><th>Name</th>
					</tr>
				
				</thead>
				<c:forEach var="a" items="${film.actors}">
					<tr>
						<td>${a.id }</td><td>${a.firstName } ${a.lastName }</td>
					</tr>
				</c:forEach>
			</table>

			<br>

			<form action="deleteFilm.do" method="GET">
				<input type="hidden" name="filmId" value="${film.id }" /> <input
					type="submit" value="Delete Film From Database" />
			</form>
			<a href="index.do">Find another film</a>


		</c:when>
		<c:otherwise>
			<p>No result</p>
		</c:otherwise>
	</c:choose>

</body>
</html>