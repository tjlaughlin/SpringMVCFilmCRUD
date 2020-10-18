<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film Site</title>
</head>
<body>
	<h3>Find a film</h3>
	<div>
		<form action="Films.do" method="GET">
			Search by Film I.D. number: <input type="text" name="filmId" /> <input
				type="submit" value="Get film" />
		</form>
	</div>
	<br>

	<div>
		<form action="FilmsList.do" method="GET">
			Search Films by keyword: <input type="text" name="keyword" /> <input
				type="submit" value="Get films" />
		</form>
	</div>
	<br>

	 <div>
		<form action="createFilm.do" method="POST">
			Create Film: <input type="text" name="title" /> <input
				type="submit" value="Create film" />
		</form>
	</div>
	<br>
<!--
	<div>
		<form action="films.do" method="GET">
			Update Film: <input type="text" name="update" /> <input type="submit"
				value="Update film" />
		</form>
	</div>
	<br>

	<div>
		<form action="films.do" method="GET">
			Delete Film: <input type="text" name="delete" /> <input type="submit"
				value="Delete film" />
		</form>
	</div>

<h3>Find a Film</h3>
	<form action="Films.do" method="GET">
		Film ID:
		<input type="number" name="filmId"/> 
		<input type="submit" value="Get Film" />
	</form> -->

</body>
</html>