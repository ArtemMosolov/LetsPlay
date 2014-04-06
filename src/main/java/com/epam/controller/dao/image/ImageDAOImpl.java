package com.epam.controller.dao.image;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.epam.controller.db.ConnectionManager;
import com.epam.core.BaseDAO;
import com.epam.core.preperedstatement.PreparedStatementManager;
import com.epam.model.bean.Image;

public class ImageDAOImpl extends BaseDAO<Image> implements ImageDAO<Image> {

	private static Logger logger = Logger.getLogger(ImageDAOImpl.class);

	private static final String INSERT_IMAGE = "INSERT INTO image (file_name) VALUES(?)";
	private static final String UPDATE_IMAGE = "UPDATE image SET file_name = ? WHERE id_image = ?";
	private static final String SELECT_IMAGE = "SELECT * FROM image WHERE id_image = ?";
	private static final String DELETE_IMAGE = "DELETE FROM image WHERE id_image = ?";

	@Override
	public int create(Image model) {
		int result = 0;
		PreparedStatement ps = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(INSERT_IMAGE,Statement.RETURN_GENERATED_KEYS);
			new PreparedStatementManager().putArgs(ps, model.getFileName());
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
	public Image readId(int id) {
		Image image = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(SELECT_IMAGE);
			new PreparedStatementManager().putArgs(ps, id);
			resultSet = ps.executeQuery();
			image = resultSetToObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return image;
	}

	@Override
	public void delete(int id) throws SQLException {
		PreparedStatement ps = null;
		Connection con = ConnectionManager.getInstance().getConnection();
		ps = con.prepareStatement(DELETE_IMAGE);
		new PreparedStatementManager().putArgs(ps, id);
		ps.executeUpdate();
	}

	@Override
	public int update(Image model) {
		int result = 0;
		PreparedStatement ps = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(UPDATE_IMAGE);
			new PreparedStatementManager().putArgs(ps, model.getFileName(),
					model.getIdImage());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return result;
	}

}
