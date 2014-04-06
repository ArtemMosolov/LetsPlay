package com.epam.controller.dao.genresongs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.epam.controller.db.ConnectionManager;
import com.epam.core.BaseDAO;
import com.epam.core.preperedstatement.PreparedStatementManager;
import com.epam.model.bean.GenreSong;

public class GenreSongDAOImpl extends BaseDAO<GenreSong> implements
		GenreSongDAO<GenreSong> {

	private static Logger logger = Logger.getLogger(GenreSongDAOImpl.class);

	private static final String INSERT_GENRE_SONG = "INSERT INTO genre_song "
			+ "(id_genre, id_song) VALUES(? , ?)";
	private static final String SELECT_GENRE_SONG = "SELECT * "
			+ "FROM genre_song WHERE id_song = ?";
	private static final String DELETE_GENRE_SONG = "DELETE FROM "
			+ "genre_song WHERE id_song = ?";
	private static final String UPDATE_GENRE_SONG = "UPDATE genre_song SET "
			+ "id_genre = ?, where id_song = ?";

	@Override
	public int create(GenreSong model) {
		int result = 0;
		PreparedStatement ps = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(INSERT_GENRE_SONG);
				new PreparedStatementManager().putArgs(ps, model.getIdGenre(),
						model.getIdSong());
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
	public GenreSong readId(int id) {
		GenreSong genreSong = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(SELECT_GENRE_SONG);
			new PreparedStatementManager().putArgs(ps, id);
			resultSet = ps.executeQuery();
			genreSong = resultSetToObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return genreSong;
	}

	@Override
	public void delete(int id) throws SQLException {
		PreparedStatement ps = null;
		Connection con = ConnectionManager.getInstance().getConnection();
		ps = con.prepareStatement(DELETE_GENRE_SONG);
		new PreparedStatementManager().putArgs(ps, id);
		ps.executeUpdate();
	}

	@Override
	public int update(GenreSong model) {
		int result = 0;
		PreparedStatement ps = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(UPDATE_GENRE_SONG);
			new PreparedStatementManager().putArgs(ps, model.getIdGenre(),
					model.getIdSong());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return result;
	}

}
