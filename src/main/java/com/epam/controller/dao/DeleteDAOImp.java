package com.epam.controller.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.epam.controller.db.ConnectionManager;
import com.epam.core.BaseDAO;
import com.epam.core.preperedstatement.PreparedStatementManager;
import com.epam.model.bean.DeleteModel;

public class DeleteDAOImp extends BaseDAO<DeleteModel> implements
		BaseDAOInterfase<DeleteModel> {
	private static Logger logger = Logger.getLogger(DeleteDAOImp.class);
	private static final String DELETE = "DELETE * FROM delete_file WHERE id=?";
	private static final String INSERT = "INSERT INTO delete_file(name_file) values(?)";
	private static final String UPDATE = "UPDATE delete_file SET name_file=?";
	private static final String SELECT = "SELECT * FROM delete_file WHERE id=?";

	@Override
	public int create(DeleteModel model) {
		int result = 0;
		PreparedStatement preparedStatement = null;
		try (Connection connection = ConnectionManager.getInstance()
				.getConnection()) {
			preparedStatement = connection.prepareStatement(INSERT,Statement.RETURN_GENERATED_KEYS);
			new PreparedStatementManager().putArgs(preparedStatement,
					model.getFileName());
			result = preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();
			result = resultSet.getInt(1);
			resultSet.close();
		} catch (SQLException e) {
			logger.error(e.getMessage());

		}
		return result;
	}

	@Override
	public DeleteModel readId(int id) {
		ResultSet resultSet = null;
		DeleteModel model = null;
		PreparedStatement preparedStatement = null;
		try (Connection connection = ConnectionManager.getInstance()
				.getConnection()) {
			preparedStatement = connection.prepareStatement(SELECT);
			new PreparedStatementManager().putArgs(preparedStatement, id);
			resultSet = preparedStatement.executeQuery();
			model = resultSetToObject(resultSet);

		} catch (SQLException e) {
			logger.error(e.getMessage());

		}

		return model;
	}

	@Override
	public void delete(int id) throws SQLException {
		Connection connection = ConnectionManager.getInstance().getConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement(DELETE);
		new PreparedStatementManager().putArgs(preparedStatement, id);
		preparedStatement.executeUpdate();

	}

	@Override
	public int update(DeleteModel model) {
		int result = 0;
		PreparedStatement preparedStatement = null;
		try (Connection connection = ConnectionManager.getInstance()
				.getConnection()) {
			preparedStatement = connection.prepareStatement(UPDATE);
			new PreparedStatementManager().putArgs(preparedStatement,
					model.getFileName());
			result = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			logger.error(e.getMessage());

		}
		return result;
	}

}
