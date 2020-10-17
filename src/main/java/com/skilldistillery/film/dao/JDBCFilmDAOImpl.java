package com.skilldistillery.film.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.skilldistillery.film.entities.Film;
public class JDBCFilmDAOImpl implements FilmDAO {
	static {
		try {
			Class.forName( "com.mysql.jdbc.Driver" ) ;
		} catch ( ClassNotFoundException e ) {
			System.out.println( "ERROR! CLASS DEFINITION NOT FOUND!" ) ;
		}
	}
	public void populateFilmFromDB( Film film , ResultSet rs ) throws SQLException {
		
		film.setId( rs.getInt( "film.id" ) ) ;
		film.setTitle( rs.getString( "title" ) ) ;
		film.setDescription( rs.getString( "description" ) ) ;
		film.setReleaseYear( rs.getInt( "release_year" ) ) ;
		film.setLanguage( rs.getString( "name" ) );
		film.setRentalDuration( rs.getInt( "rental_duration" ) ) ;
		film.setRentalRate( rs.getDouble( "rental_rate" ) ) ;
		film.setLength( rs.getInt( "length" ) ) ;
		film.setReplacementCost( rs.getDouble( "replacement_cost" ) ) ;
		film.setRating( rs.getString( "rating" ) ) ;
		film.setSpecialFeatures( rs.getString( "special_features" ) ) ;
		
	}
	@Override
	public Film findFilmById( int filmId ) {
		Film film = null ;
		String sqltxt = "SELECT * FROM film " +
		"JOIN language ON film.language_id = language.id " +
		"JOIN film_list on film.id = film_list.FID " +
		"WHERE film.id = ?" ;
		try {
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/sdvid?useSSL=false&useJDBCCompliantTimeZoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC" , "student" , "student" ) ;
			PreparedStatement stmt = conn.prepareStatement( sqltxt ) ;
			stmt.setInt( 1 , filmId ) ;
			ResultSet rs = stmt.executeQuery() ;
			if ( rs.next() ) {
				film = new Film() ;
				populateFilmFromDB( film , rs );
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
			PreparedStatement checkIfPresent = conn.prepareStatement(
					"SELECT * FROM film WHERE id = ?" );
			checkIfPresent.setInt( 1, film.getId() );
			if ( checkIfPresent.executeQuery().next() ) {
				checkIfPresent.close();
				return newFilm;
			}
			checkIfPresent.close();
			PreparedStatement stmt = conn.prepareStatement( sql ) ;
			stmt.setString( 1 , film.getTitle() ) ;
			stmt.setInt( 2 , 1 );
			int newId = stmt.executeUpdate(sql , Statement.RETURN_GENERATED_KEYS );
			stmt.close();
			PreparedStatement getNewObj = conn.prepareStatement(
					"SELECT * FROM film where id = ?");
			getNewObj.setInt( 1 , newId );
			ResultSet rs = getNewObj.executeQuery();
			rs.next();
			newFilm = new Film();
			populateFilmFromDB(newFilm, rs);
			rs.close();
			getNewObj.close();
			conn.close();
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
		return newFilm;
	}
	@Override
	public boolean deleteFilm(int filmId) {
		String check = "SELECT * FROM film WHERE id = ?";
		String sql = "DELETE * FROM film WHERE id = ?";
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
		String modify = "UPDATE film SET ? = ? where id = ?";
		String retrieve = "SELECT * FROM film WHERE id = ?";
		
		
		try {
			
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/sdvid?useSSL=false&useJDBCCompliantTimeZoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC" , "student" , "student" ) ;
			PreparedStatement update = conn.prepareStatement( modify );
			update.setString( 1 , property );
			update.setString( 2 , value );
			update.setInt( 3 , id );
			update.executeUpdate();
			update.close();
			
			PreparedStatement getUpdatedRecord = conn.prepareStatement( retrieve );
			getUpdatedRecord.setInt( 1 , id );
			ResultSet rs = getUpdatedRecord.executeQuery();
			edited = new Film();
			populateFilmFromDB(edited, rs);
			
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
			stmt.setString( 1 , keyword );
			stmt.setString( 2 , keyword );
			ResultSet rs = stmt.executeQuery();
			while ( rs.next() ) {
				Film f = new Film();
				populateFilmFromDB(f, rs);
				films.add( f );
			}
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
		return films;
	}
}