package com.skilldistillery.film.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;
public class JDBCFilmDAOImpl implements FilmDAO {
	static {
		try {
			Class.forName( "com.mysql.jdbc.Driver" ) ;
		} catch ( ClassNotFoundException e ) {
			System.out.println( "ERROR! CLASS DEFINITION NOT FOUND!" ) ;
		}
	}
	public void populateFilmFromDB( Film film , ResultSet rs , Connection conn ) throws SQLException {
		
		film.setId( rs.getInt( "film.id" ) ) ;
		film.setTitle( rs.getString( "title" ) ) ;
		film.setDescription( rs.getString( "description" ) ) ;
		film.setReleaseYear( rs.getInt( "release_year" ) ) ;
		film.setRentalDuration( rs.getInt( "rental_duration" ) ) ;
		film.setRentalRate( rs.getDouble( "rental_rate" ) ) ;
		film.setLength( rs.getInt( "length" ) ) ;
		film.setReplacementCost( rs.getDouble( "replacement_cost" ) ) ;
		film.setRating( rs.getString( "rating" ) ) ;
		film.setSpecialFeatures( rs.getString( "special_features" ) ) ;
		
		String actorQuery = "SELECT a.id , a.first_name , a.last_name " +
			"FROM film f JOIN  film_actor fa on f.id = fa.film_id " +
			"JOIN actor a ON fa.actor_id = a.id WHERE f.id = ?";
		PreparedStatement actors = conn.prepareStatement(actorQuery);
		actors.setInt( 1 , rs.getInt( "film.id" ) );
		ResultSet rs2 = actors.executeQuery();
		List<Actor> actorList = new ArrayList<>();
		
		while ( rs2.next() ) {
			
			Actor nextActor = new Actor();
			nextActor.setId( rs2.getInt( "a.id" ) );
			nextActor.setFirstName( rs2.getString( "first_name" ) );
			nextActor.setLastName( rs2.getString( "last_name" ) );
			actorList.add( nextActor );
			
		}
		
		film.setActors( actorList );
		rs2.close();
		actors.close();
		
		String categoryQuery = "SELECT name FROM film f " +
		"JOIN film_category fc ON f.id = fc.film_id " +
		"JOIN category c on fc.category_id = c.id " +
		"WHERE f.id = ?";
		List<String> genres = new ArrayList<>();
		PreparedStatement categories =  conn.prepareStatement( categoryQuery );
		categories.setInt( 1 , rs.getInt( "film.id" ) );
		ResultSet rs3 = categories.executeQuery();
		
		while ( rs3.next() ) {
			genres.add( rs3.getString( "name" ) );
		}
		
		film.setCategories( genres );
		
		rs3.close();
		categories.close();
		
	}
	
	@Override
	public Film findFilmById( int filmId ) {
		Film film = null ;
		String sqltxt = "SELECT * FROM film " +
		"JOIN language ON film.language_id = language.id " +
//		"JOIN film_list on film.id = film_list.FID " +
		"WHERE film.id = ?" ;
		try {
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/sdvid?useSSL=false&useJDBCCompliantTimeZoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC" , "student" , "student" ) ;
			PreparedStatement stmt = conn.prepareStatement( sqltxt ) ;
			stmt.setInt( 1 , filmId ) ;
			ResultSet rs = stmt.executeQuery() ;
			if ( rs.next() ) {
				film = new Film() ;
				populateFilmFromDB( film , rs , conn );
				rs.close() ;
				stmt.close() ;
				conn.close() ;
			}
		} catch ( SQLException e ) {
			e.printStackTrace() ;
		}
		return film ;
	}
	@Override
	public Film addFilm(Film film) {
		Film newFilm = null;
		String sql = "INSERT INTO film ( title , language_id ) VALUES ( ? , ? )";
		try {
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/sdvid?useSSL=false&useJDBCCompliantTimeZoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC" , "student" , "student" ) ;
			conn.setAutoCommit(false);
			PreparedStatement checkIfPresent = conn.prepareStatement(
					"SELECT * FROM film WHERE id = ?" );
			checkIfPresent.setInt( 1, film.getId() );
			if ( checkIfPresent.executeQuery().next() ) {
				checkIfPresent.close();
				return newFilm;
			}
			checkIfPresent.close();
			PreparedStatement stmt = conn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS ) ;
			stmt.setString( 1 , film.getTitle() ) ;
			stmt.setInt( 2 , 1 );
			stmt.executeUpdate();
			ResultSet key = stmt.getGeneratedKeys();
			key.next();
			int newId = key.getInt(1);
			stmt.close();
			
			PreparedStatement getNewObj = conn.prepareStatement(
					"SELECT * FROM film where id = ?");
			getNewObj.setInt( 1 , newId );
			ResultSet rs = getNewObj.executeQuery();
			rs.next();
			newFilm = new Film();
			populateFilmFromDB( newFilm , rs , conn );
			rs.close();
			getNewObj.close();
			conn.commit();
			conn.close();
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
		return newFilm;
	}
	@Override
	public boolean deleteFilm(int filmId) {
		String check = "SELECT * FROM film WHERE id = ?";
		String sql = "DELETE FROM film WHERE id = ?";
		boolean output = false;
		try {
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/sdvid?useSSL=false&useJDBCCompliantTimeZoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC" , "student" , "student" ) ;
			PreparedStatement checkIfPresent = conn.prepareStatement(check);
			PreparedStatement deleteCommand = conn.prepareStatement( sql );
			checkIfPresent.setInt( 1 , filmId );
			deleteCommand.setInt( 1 , filmId );
			ResultSet rs = checkIfPresent.executeQuery();
			if ( !rs.next() ) {
				deleteCommand.close();
				checkIfPresent.close();
				conn.close();
				return output;
			}
			deleteCommand.executeUpdate();
			output = true;
			rs.close();
			deleteCommand.close();
			checkIfPresent.close();
			conn.close();
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
		return output;
	}
	@Override
	public Film editFilm( String property, String value, int id ) {
		Film edited = null;
		String modify = "UPDATE film SET " + property + " = ? where id = ?";
		String retrieve = "SELECT * FROM film WHERE id = ?";
		
		
		try {
			
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/sdvid?useSSL=false&useJDBCCompliantTimeZoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC" , "student" , "student" ) ;
			PreparedStatement update = conn.prepareStatement( modify );
			update.setString( 1 , value );
			update.setInt( 2 , id );
			update.executeUpdate();
			update.close();
			
			PreparedStatement getUpdatedRecord = conn.prepareStatement( retrieve );
			getUpdatedRecord.setInt( 1 , id );
			ResultSet rs = getUpdatedRecord.executeQuery();
			edited = new Film();
			if (rs.next()) {
				populateFilmFromDB( edited, rs , conn );
			}
			
			rs.close();
			getUpdatedRecord.close();
			conn.close();
			
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
	
		return edited;
		
		
	}
	@Override
	public List<Film> findFilmsByKeyword(String keyword) {
		List<Film> films = new ArrayList<Film>();
		String sql = "SELECT * FROM film WHERE title LIKE ? OR description LIKE ?";
		try {
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/sdvid?useSSL=false&useJDBCCompliantTimeZoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC" , "student" , "student" ) ;
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString( 1 , "%" + keyword.toUpperCase() + "%" );
			stmt.setString( 2 , "%" + keyword.toUpperCase() + "%" );
			ResultSet rs = stmt.executeQuery();
			while ( rs.next() ) {
				Film f = new Film();
				populateFilmFromDB( f , rs , conn );
				films.add( f );
			}
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
		return films;
	}
}