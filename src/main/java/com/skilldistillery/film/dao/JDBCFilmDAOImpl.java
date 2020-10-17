package com.skilldistillery.film.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.skilldistillery.film.entities.Film;

public class JDBCFilmDAOImpl implements FilmDAO {
	
	static {
		try {
			Class.forName( "com.mysql.jdbc.Driver" ) ;
		} catch ( ClassNotFoundException e ) {
			System.out.println( "ERROR! CLASS DEFINITION NOT FOUND!" ) ;
		}
	}

}

