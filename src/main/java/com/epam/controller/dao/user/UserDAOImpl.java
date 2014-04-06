package com.epam.controller.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.epam.controller.db.ConnectionManager;
import com.epam.core.BaseDAO;
import com.epam.core.preperedstatement.PreparedStatementManager;
import com.epam.model.bean.UserModel;

public class UserDAOImpl extends BaseDAO<UserModel> implements
		UserDAO<UserModel> {

	private static Logger logger = Logger.getLogger(UserDAOImpl.class);

	private static final String INSERT_USER = "INSERT INTO user "
			+ "(login,email,password,language,is_admin) " + "VALUES(?,?,?,?,?)";
	private static final String SELECT_USER_ID = "SELECT * "
			+ "FROM user WHERE id_user = ?";
	private static final String DELETE_USER = "DELETE FROM user "
			+ "WHERE id=?";
	private static final String UPDATE_USER = "UPDATE user SET "
			+ "email = ?, password = ?, language = ? WHERE id_user = ?";
	private static final String SELECT_USER_LOGIN = "SELECT * "
			+ "FROM user WHERE login = ? AND is_admin=?";
	private static final String SELECT_USER_EMAIL = "SELECT * "
			+ "FROM user WHERE email = ? AND is_admin=?";

	@Override
	public int create(UserModel model) {
		int result = 0;
		PreparedStatement ps = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(INSERT_USER,
					Statement.RETURN_GENERATED_KEYS);
			new PreparedStatementManager().putArgs(ps, model.getLogin(),
					model.getEmail(), model.getPassword(), model.getLanguage(),
					model.isAdmin());
			result = ps.executeUpdate();
			ResultSet resultSet = ps.getGeneratedKeys();
			resultSet.next();
			result = resultSet.getInt(1);
			resultSet.close();
		} catch (SQLException e) {
			result = 0;
			logger.error(e.getMessage());
		}
		return result;
	}

	@Override
	public UserModel readId(int id) {
		UserModel model = null;
		PreparedStatement ps = null;
		ResultSet resultSet;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(SELECT_USER_ID);
			new PreparedStatementManager().putArgs(ps, id);
			resultSet = ps.executeQuery();
			model = resultSetToObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return model;
	}

	@Override
	public void delete(int id) throws SQLException {
		PreparedStatement ps = null;
		Connection con = ConnectionManager.getInstance().getConnection();
		ps = con.prepareStatement(DELETE_USER);
		new PreparedStatementManager().putArgs(ps, id);
		ps.executeUpdate();
	}

	@Override
	public int update(UserModel model) {
		int result = 0;
		PreparedStatement ps = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(UPDATE_USER);
			new PreparedStatementManager()
					.putArgs(ps, model.getEmail(), model.getPassword(),
							model.getLanguage(), model.getIdUser());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return result;
	}

	@Override
	public UserModel readLogin(String login, boolean isAdmin) {
		UserModel model = null;
		PreparedStatement ps = null;
		ResultSet resultSet;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(SELECT_USER_LOGIN);
			new PreparedStatementManager().putArgs(ps, login, isAdmin);
			resultSet = ps.executeQuery();
			model = resultSetToObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return model;
	}

	@Override
	public UserModel readEmail(String email, boolean isAdmin) {
		UserModel model = null;
		PreparedStatement ps = null;
		ResultSet resultSet;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(SELECT_USER_EMAIL);
			new PreparedStatementManager().putArgs(ps, email, isAdmin);
			resultSet = ps.executeQuery();
			model = resultSetToObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return model;
	}
}
