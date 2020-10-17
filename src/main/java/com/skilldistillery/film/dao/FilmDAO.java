package com.skilldistillery.film.dao;

import java.util.List;

import com.skilldistillery.film.entities.Film;

public interface FilmDAO {
	
	public Film findFilmById(int filmId);
	public Film addFilm( Film film);
	public boolean deleteFilm(int filmId);
	public boolean editFilm( int filmId );
	public List<Film> findFilmsByKeyword( String keyword );
	
}
