package com.epam.controller.dao.playlist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.epam.controller.db.ConnectionManager;
import com.epam.core.BaseDAO;
import com.epam.core.preperedstatement.PreparedStatementManager;
import com.epam.model.bean.PlayList;

public class PlayListDAOImpl extends BaseDAO<PlayList> implements
		PlayListDAO<PlayList> {

	private static Logger logger = Logger.getLogger(PlayListDAO.class);

	private static final String INSERT_PLAYLIST = "INSERT INTO "
			+ "playlist (id_user_play_list, id_song) VALUES(?, ?)";
	private static final String UPDATE_PLAYLIST = "UPDATE playlist "
			+ "SET id_user_play_list = ?, id_song = ? WHERE id_playlist = ?";
	private static final String SELECT_PLAYLIST = "SELECT * FROM "
			+ "playlist WHERE id_user_play_list = ?";
	private static final String DELETE_PLAYLIST = "DELETE FROM "
			+ "playlist WHERE id_user_play_list = ? AND id_song = ?";

	@Override
	public int create(PlayList model) {
		int result = 0;
		PreparedStatement ps = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(INSERT_PLAYLIST,
					Statement.RETURN_GENERATED_KEYS);
			new PreparedStatementManager().putArgs(ps,
					model.getIdUserPlayList(), model.getIdSong());
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
	public PlayList readId(int id) {
		PlayList playList = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(SELECT_PLAYLIST);
			new PreparedStatementManager().putArgs(ps, id);
			resultSet = ps.executeQuery();
			playList = resultSetToObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return playList;
	}

	@Override
	public void delete(int id) throws SQLException {
		PreparedStatement ps = null;
		Connection con = ConnectionManager.getInstance().getConnection();
		ps = con.prepareStatement(DELETE_PLAYLIST);
		new PreparedStatementManager().putArgs(ps, id);
		ps.executeUpdate();
	}

	@Override
	public void delete(int playlistId, int songId) throws SQLException {
		PreparedStatement ps = null;
		Connection con = ConnectionManager.getInstance().getConnection();
		ps = con.prepareStatement(DELETE_PLAYLIST);
		new PreparedStatementManager().putArgs(ps, playlistId, songId);
		ps.executeUpdate();
	}

	@Override
	public int update(PlayList model) {
		int result = 0;
		PreparedStatement ps = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(UPDATE_PLAYLIST);
			new PreparedStatementManager().putArgs(ps, model.getIdSong(),
					model.getIdUserPlayList());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return result;
	}

}
