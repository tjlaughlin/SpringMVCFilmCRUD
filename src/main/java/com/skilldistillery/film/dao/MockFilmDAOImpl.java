package com.skilldistillery.film.dao;

import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.film.entities.Film;

public class MockFilmDAOImpl implements FilmDAO {
	List<Film> filmList;

	public MockFilmDAOImpl() {
		filmList = new ArrayList<>();
		Film film1 = new Film();
		film1.setId(1);
		film1.setTitle("Film 1");
		Film film2 = new Film();
		film2.setId(2);
		film2.setTitle("Film 2");
		Film film3 = new Film();
		film3.setId(3);
		film3.setTitle("Film 3");

		filmList.add(film1);
		filmList.add(film2);
		filmList.add(film3);

	}

	@Override
	public Film findFilmById(int filmId) {

		for (Film f : filmList) {
			if (f.getId() == filmId) {
				return f;
			}
		}
		return null;

	}

	@Override
	public Film addFilm(Film film) {
		return filmList.add(film) ? film : null;

	}

	@Override
	public boolean deleteFilm(int filmId) {
		boolean output = false;
		for (Film f : filmList) {
			if (f.getId() == filmId) {
				output = true;
				filmList.remove(f);
				break;
			}
		}

		return output;
	}

	@Override
	public Film editFilm( String property, String value , int id ) {
		Film toEdit = null;
		for (Film f : filmList) {
			if (f.getId() == id) {
				toEdit = f;
				break;
			}
		}
		if(toEdit != null) {
			toEdit.setTitle("Title Edited");
		}
		
		switch (property) {
		case "title":
			toEdit.setTitle(value);
			break;
		case "description":
			toEdit.setDescription(value);
			break;
		default:
			break;
		}
		
		
		return toEdit;
	}

	@Override
	public List<Film> findFilmsByKeyword(String keyword) {
		List<Film> films = new ArrayList<>();
		for (Film film : filmList) {
			if(film.getTitle().contains(keyword)) {
				films.add(film);
			}
		}
		return films;
	}

}
