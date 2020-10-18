<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Films</title>
</head>
<body>
<c:choose>
	<c:when test="${not empty filmList}">
		<table>
			<c:forEach var="f" items="${filmList}">
				<tr>
					<td><a href="Films.do?filmId=${f.id }">Film ID: ${f.id }</a></td>
					<td>${f.title}</td>
				</tr>
			</c:forEach>
		</table>
	</c:when>
	<c:otherwise>
		No results were found
	</c:otherwise>
</c:choose>
</body>
</html>