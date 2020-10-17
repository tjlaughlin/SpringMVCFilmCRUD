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

}
