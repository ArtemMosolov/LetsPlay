package com.epam.controller.dao.songstatistic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.epam.controller.db.ConnectionManager;
import com.epam.core.BaseDAO;
import com.epam.core.preperedstatement.PreparedStatementManager;
import com.epam.model.bean.SongStatistic;

public class SongStatisticDAOImpl extends BaseDAO<SongStatistic> implements
		SongStatisticsDAO<SongStatistic> {

	private static Logger logger = Logger.getLogger(SongStatisticDAOImpl.class);

	private static final String INSERT_STATISTIC = "INSERT INTO song_statistic "
			+ "(id_user, id_song, count) VALUES(?,?,?)";
	private static final String SELECT_SONG_STATISTIC = "SELECT * FROM song_statistic "
			+ "WHERE id_song=?";
	private static final String DELETE_STATISTIC = "DELETE from song_statistic WHERE "
			+ "id_song=?";
	private static final String UPDATE_STATISTIC = "UPDATE song_statistic SET "
			+ "count=? WHERE id_user=? AND id_song=?";
	private static final String SELECT_USER_SONG_STATISTIC = "SELECT * FROM "
			+ "song_statistic WHERE id_user=? AND id_song=?";
	private static final String DELETE_USER_SONG_STATISTIC = "DELETE from song_statistic "
			+ "WHERE id_user=? AND id_song=?";

	@Override
	public int create(SongStatistic model) {
		int result = 0;
		PreparedStatement ps = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(INSERT_STATISTIC,Statement.RETURN_GENERATED_KEYS);
			new PreparedStatementManager().putArgs(ps, model.getIdUser(),
					model.getIdSong(), model.getCount());
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
	public SongStatistic readId(int id) {
		SongStatistic songStatistic = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(SELECT_SONG_STATISTIC);
			new PreparedStatementManager().putArgs(ps, id);
			resultSet = ps.executeQuery();
			songStatistic = resultSetToObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return songStatistic;
	}

	@Override
	public void delete(int id) throws SQLException {
		PreparedStatement ps = null;
		Connection con = ConnectionManager.getInstance().getConnection();
		ps = con.prepareStatement(DELETE_STATISTIC);
		new PreparedStatementManager().putArgs(ps, id);
		ps.executeUpdate();
	}

	@Override
	public int update(SongStatistic model) {
		int result = 0;
		PreparedStatement ps = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(UPDATE_STATISTIC);
			new PreparedStatementManager().putArgs(ps, model.getCount(),
					model.getIdUser(), model.getIdSong());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return result;
	}

	@Override
	public SongStatistic getUserSongStatistic(int id_user, int id_song) {
		SongStatistic songStatistic = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(SELECT_USER_SONG_STATISTIC);
			new PreparedStatementManager().putArgs(ps, id_user, id_song);
			resultSet = ps.executeQuery();
			songStatistic = resultSetToObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return songStatistic;
	}

	@Override
	public void deleteUserSongStatistic(int id_user, int id_song) {
		PreparedStatement ps = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(DELETE_USER_SONG_STATISTIC);
			new PreparedStatementManager().putArgs(ps, id_user, id_song);
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
	}

}
