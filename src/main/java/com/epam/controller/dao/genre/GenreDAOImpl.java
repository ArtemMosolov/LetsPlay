package com.epam.controller.dao.genre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.controller.db.ConnectionManager;
import com.epam.core.BaseDAO;
import com.epam.core.preperedstatement.PreparedStatementManager;
import com.epam.model.bean.Genre;

public class GenreDAOImpl extends BaseDAO<Genre> implements GenreDAO<Genre> {

	private static Logger logger = Logger.getLogger(GenreDAOImpl.class);

	private static final String INSERT_GENRE = "INSERT INTO genre (name_genre) "
			+ "VALUES(?)";
	private static final String UPDATE_GENRE = "UPDATE genre SET "
			+ "name_genre = ? WHERE id_genre = ?";
	private static final String SELECT_GENRE = "SELECT * FROM genre "
			+ "WHERE id_genre = ?";
	private static final String SELECT_ALL_GENRES = "SELECT * FROM genre";
	private static final String DELETE_GENRE = "DELETE from genre WHERE id_genre = ?";
	private static final String SELECT_GENRES_NAME = "SELECT * from genre where "
			+ "name_genre LIKE ?";
	private static final String SELECT_ALL_GENRE_BY_SONG_NAME = "SELECT * FROM genre"
			+ "NATURAL JOIN genre_song NATURAL JOIN song WHERE name_song LIKE ?";
	private static final String SELECT_ALL_GENRE_BY_ARTIST_NAME = "SELECT * FROM genre "
			+ "NATURAL JOIN genre_song NATURAL JOIN song NATURAL JOIN album"
			+ "NATURAL JOIN artists WHERE name_artists LIKE ?";
	private static final String SELECT_ALL_GENRE_BY_ALBUM_NAME = "SELECT * FROM genre "
			+ "NATURAL JOIN genre_song NATURAL JOIN song NATURAL JOIN album "
			+ "WHERE name_album LIKE ?";

	public List<Genre> getGanreBySongName(String name) {
		List<Genre> genres = new ArrayList<Genre>();
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(SELECT_ALL_GENRE_BY_SONG_NAME);
			ps.setString(1, name + "%");
			resultSet = ps.executeQuery();
			genres = getMassObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return genres;
	}

	public List<Genre> getGanreByAlbumName(String name) {
		List<Genre> genres = new ArrayList<Genre>();
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(SELECT_ALL_GENRE_BY_ALBUM_NAME);
			ps.setString(1, name + "%");
			resultSet = ps.executeQuery();
			genres = getMassObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return genres;
	}

	public List<Genre> getGanreByArtistName(String name) {
		List<Genre> genres = new ArrayList<Genre>();
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(SELECT_ALL_GENRE_BY_ARTIST_NAME);
			ps.setString(1, name + "%");
			resultSet = ps.executeQuery();
			genres = getMassObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return genres;
	}

	@Override
	public int create(Genre model) {
		int result = 0;
		PreparedStatement ps = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(INSERT_GENRE,
					Statement.RETURN_GENERATED_KEYS);
			new PreparedStatementManager().putArgs(ps, model.getNameGenre());
			result = ps.executeUpdate();
			ResultSet resultSet = ps.getGeneratedKeys();
			resultSet.next();
			result = resultSet.getInt(1);
			resultSet.close();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return result;
	}

	@Override
	public Genre readId(int id) {
		Genre genre = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(SELECT_GENRE);
			new PreparedStatementManager().putArgs(ps, id);
			resultSet = ps.executeQuery();
			genre = resultSetToObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return genre;
	}

	@Override
	public int update(Genre genre) {
		int result = 0;
		PreparedStatement ps = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(UPDATE_GENRE);
			new PreparedStatementManager().putArgs(ps, genre.getNameGenre(),
					genre.getIdGenre());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return result;
	}

	@Override
	public List<Genre> getAll() {
		List<Genre> genres = new ArrayList<Genre>();
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		setTable("genre");
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(SELECT_ALL_GENRES);
			resultSet = ps.executeQuery();
			genres = getMassObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return genres;
	}

	@Override
	public void delete(int id) throws SQLException {
		PreparedStatement ps = null;
		Connection con = ConnectionManager.getInstance().getConnection();
		ps = con.prepareStatement(DELETE_GENRE);
		new PreparedStatementManager().putArgs(ps, id);
		ps.executeUpdate();
	}

	@Override
	public List<Genre> getGanreByName(String name) {
		List<Genre> genres = new ArrayList<Genre>();
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(SELECT_GENRES_NAME);
			ps.setString(1, name + "%");
			resultSet = ps.executeQuery();
			genres = getMassObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return genres;
	}

}
