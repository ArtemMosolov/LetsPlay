package com.epam.controller.dao.rating;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.epam.controller.db.ConnectionManager;
import com.epam.core.BaseDAO;
import com.epam.core.preperedstatement.PreparedStatementManager;
import com.epam.model.bean.Rating;

public class RatingDAOImpl extends BaseDAO<Rating> implements RatingDAO<Rating> {

	private static Logger logger = Logger.getLogger(RatingDAOImpl.class);

	private static final String INSERT_RATING = "INSERT INTO rating "
			+ "(id_user, id_song, like, dislike) VALUES(?,?,?,?)";
	private static final String SELECT_RATING = "SELECT * FROM "
			+ "song_statistic WHERE id_song=?";
	private static final String DELETE_RATING = "DELETE from rating "
			+ "WHERE id_user=? AND id_song=?";
	private static final String UPDATE_RATING = "UPDATE rating SET "
			+ "like=?, dislike=? WHERE id_user=? AND id_song=?";

	@Override
	public int create(Rating model) {
		int result = 0;
		PreparedStatement ps = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(INSERT_RATING,Statement.RETURN_GENERATED_KEYS);
			new PreparedStatementManager().putArgs(ps, model.getIdUser(),
					model.getIdSong(), model.getLike(), model.getDislike());
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
	public Rating readId(int id) {
		Rating rating = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(SELECT_RATING);
			new PreparedStatementManager().putArgs(ps, id);
			resultSet = ps.executeQuery();
			rating = resultSetToObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return rating;
	}

	@Override
	public void delete(int id) throws SQLException {
		PreparedStatement ps = null;
		Connection con = ConnectionManager.getInstance().getConnection();
		ps = con.prepareStatement(DELETE_RATING);
		new PreparedStatementManager().putArgs(ps, id);
		ps.executeUpdate();
	}

	@Override
	public int update(Rating model) {
		int result = 0;
		PreparedStatement ps = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(UPDATE_RATING);
			new PreparedStatementManager().putArgs(ps, model.getLike(),
					model.getDislike(), model.getIdUser(), model.getIdSong());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return result;
	}

}
