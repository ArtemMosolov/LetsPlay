package com.epam.controller.dao.userplaylist;

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
import com.epam.model.bean.UserPlayList;

public class UserPlayListDaoImpl extends BaseDAO<UserPlayList> implements
		UserPlayListDAO<UserPlayList> {

	private static Logger logger = Logger.getLogger(UserPlayListDaoImpl.class);

	private static final String INSERT_USER_PLAY_LIST = "INSERT INTO "
			+ "user_play_list (name_play_list, id_user) VALUES(?, ?)";
	private static final String UPDATE_USER_PLAY_LIST = "UPDATE "
			+ "user_play_list SET name_play_list=?, id_user=? "
			+ "WHERE id_user_play_list=?";
	private static final String SELECT_USER_PLAY_LIST = "SELECT * "
			+ "FROM user_play_list WHERE id_user_play_list=?";
	private static final String DELETE_USER_PLAY_LIST = "DELETE "
			+ "from user_play_list WHERE id_user_play_list=?";
	private static final String SELECT_ALL_USER_PLAYLIST = "SELECT * "
			+ "FROM user_play_list WHERE id_user=?";

	@Override
	public int create(UserPlayList model) {
		int result = 0;
		PreparedStatement ps = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(INSERT_USER_PLAY_LIST,
					Statement.RETURN_GENERATED_KEYS);
			new PreparedStatementManager().putArgs(ps, model.getNamePlayList(),
					model.getIdUser());
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
	public UserPlayList readId(int id) {
		UserPlayList userPlayList = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(SELECT_USER_PLAY_LIST);
			new PreparedStatementManager().putArgs(ps, id);
			resultSet = ps.executeQuery();
			userPlayList = resultSetToObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return userPlayList;
	}

	@Override
	public void delete(int id) throws SQLException {
		PreparedStatement ps = null;
		Connection con = ConnectionManager.getInstance().getConnection();
		ps = con.prepareStatement(DELETE_USER_PLAY_LIST);
		new PreparedStatementManager().putArgs(ps, id);
		ps.executeUpdate();
	}

	@Override
	public int update(UserPlayList model) {
		int result = 0;
		PreparedStatement ps = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(UPDATE_USER_PLAY_LIST);
			new PreparedStatementManager().putArgs(ps, model.getNamePlayList(),
					model.getIdUser(), model.getIdUserPlayList());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return result;
	}

	public List<UserPlayList> getAll(int userId) {
		List<UserPlayList> userPlayLists = new ArrayList<UserPlayList>();
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(SELECT_ALL_USER_PLAYLIST);
			new PreparedStatementManager().putArgs(ps, userId);
			resultSet = ps.executeQuery();
			userPlayLists = getMassObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return userPlayLists;
	}
}
