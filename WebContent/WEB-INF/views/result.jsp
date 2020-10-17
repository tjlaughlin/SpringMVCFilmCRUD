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
		<h2>${film.title }</h2>
		<table>
			<thead>
				<tr>
					<td>ID</td><td>Release Year</td><td>Language</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${film.id }</td><td><%-- ${film.releaseYear } --%>releaseYear</td><td><%-- ${film.language } --%>language</td>
				</tr>
			</tbody>
		</table>
		Description:
		<p><%-- ${film.description } --%></p>
	</c:when>
	<c:otherwise>
      <p>No result</p>
    </c:otherwise>
</c:choose>

</body>
</html>