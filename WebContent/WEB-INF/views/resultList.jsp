<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

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
	<table>
		<c:forEach var="f" items="${filmList}">
			<tr>
				<td><a href="presidentInfo?termNumber=${p.id }"> ${p.id } </a></td>
				<td> ${film.title }</td>
			</tr>
		</c:forEach>
	</table>






</body>
</html>