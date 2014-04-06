package com.epam.controller.dao.userrestore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.epam.controller.db.ConnectionManager;
import com.epam.core.BaseDAO;
import com.epam.core.preperedstatement.PreparedStatementManager;
import com.epam.model.bean.UserRestore;

public class UserRestoreDAOImpl extends BaseDAO<UserRestore> implements
		UserRestoreDAO<UserRestore> {

	private static Logger logger = Logger.getLogger(UserRestoreDAOImpl.class);

	private static final String INSERT_RESTORE = "INSERT INTO "
			+ "user_restore (hash, date_time, id_user)" + " VALUES(?, ?, ?)";
	private static final String UPDATE_RESTORE = "UPDATE "
			+ "user_restore SET hash=?, date_time=?, id_user=? "
			+ "WHERE id_user_restore=?";
	private static final String SELECT_RESTORE_TIME = "SELECT * "
			+ "FROM user_restore WHERE hash=?;";
	private static final String SELECT_RESTORE = "SELECT * FROM "
			+ "user_restore WHERE id_user_restore=?;";
	private static final String DELETE_RESTORE = "DELETE from "
			+ "user_restore WHERE id_user_restore=?";

	@Override
	public UserRestore readId(int id) {
		UserRestore userRestore = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(SELECT_RESTORE);
			new PreparedStatementManager().putArgs(ps, id);
			resultSet = ps.executeQuery();
			userRestore = resultSetToObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return userRestore;
	}

	@Override
	public void delete(int id) throws SQLException {
		PreparedStatement ps = null;
		Connection con = ConnectionManager.getInstance().getConnection();
		ps = con.prepareStatement(DELETE_RESTORE);
		new PreparedStatementManager().putArgs(ps, id);
		ps.executeUpdate();
	}

	@Override
	public int create(UserRestore model) {
		int result = 0;
		PreparedStatement ps = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(INSERT_RESTORE,
					Statement.RETURN_GENERATED_KEYS);
			new PreparedStatementManager().putArgs(ps, model.getHash(),
					model.getTime(), model.getIdUser());
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
	public int update(UserRestore model) {
		int result = 0;
		PreparedStatement ps = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(UPDATE_RESTORE);
			new PreparedStatementManager().putArgs(ps, model.getHash(),
					model.getTime(), model.getIdUser(),
					model.getIdUserRestore());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return result;
	}

	public UserRestore getRestoreByHash(String hash) {
		UserRestore userRestore = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(SELECT_RESTORE_TIME);
			new PreparedStatementManager().putArgs(ps, hash);
			resultSet = ps.executeQuery();
			userRestore = resultSetToObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return userRestore;
	}
}
