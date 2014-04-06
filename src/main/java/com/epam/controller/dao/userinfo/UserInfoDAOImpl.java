package com.epam.controller.dao.userinfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.epam.controller.db.ConnectionManager;
import com.epam.core.BaseDAO;
import com.epam.core.preperedstatement.PreparedStatementManager;
import com.epam.model.bean.UserInfo;

public class UserInfoDAOImpl extends BaseDAO<UserInfo> implements
		UserInfoDAO<UserInfo> {

	private static Logger logger = Logger.getLogger(UserInfoDAOImpl.class);

	private static final String INSERT_USER_INFO = "INSERT INTO user_info "
			+ "(id_user, id_image, first_name, last_name, "
			+ "gender, country, city, description, date_of_birth) "
			+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SELECT_USER_INFO = "SELECT * FROM "
			+ "user_info left join image on user_info.id_image=image.id_image WHERE id_user = ?";
	private static final String DELETE_USER_INFO = "DELETE FROM "
			+ "user_info WHERE id_user = ?";
	private static final String UPDATE_USER_INFO = "UPDATE user_info SET "
			+ "id_image = ?, first_name = ?, "
			+ "last_name = ?, gender = ?, country = ?, city = ?, "
			+ "description = ?, date_of_birth = ? WHERE id_user = ?";

	@Override
	public int create(UserInfo model) {
		int result = 0;
		PreparedStatement ps = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(INSERT_USER_INFO);
			new PreparedStatementManager().putArgs(ps, model.getIdUser(),
					model.getIdImageUser(), model.getFirstName(),
					model.getLastName(), model.getGender(), model.getCountry(),
					model.getCity(), model.getDescription(),
					model.getDateOfBirth());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return result;
	}

	@Override
	public UserInfo readId(int id) {
		UserInfo userInfo = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		setTable("user_info");
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(SELECT_USER_INFO);
			new PreparedStatementManager().putArgs(ps, id);
			resultSet = ps.executeQuery();
			userInfo = resultSetToObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return userInfo;
	}

	@Override
	public void delete(int id) throws SQLException {
		PreparedStatement ps = null;
		Connection con = ConnectionManager.getInstance().getConnection();
		ps = con.prepareStatement(DELETE_USER_INFO);
		new PreparedStatementManager().putArgs(ps, id);
		ps.executeUpdate();
	}

	@Override
	public int update(UserInfo model) {
		int result = 0;
		PreparedStatement ps = null;
		System.out.println(model);
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(UPDATE_USER_INFO);
			new PreparedStatementManager().putArgs(ps, model.getIdImageUser(),
					model.getFirstName(), model.getLastName(),
					model.getGender(), model.getCountry(), model.getCity(),
					model.getDescription(), model.getDateOfBirth(),
					model.getIdUser());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return result;
	}

}
