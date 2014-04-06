package com.epam.core.preperedstatement;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.epam.model.exception.NoSuchDAOTypeException;

public class PreparedStatementManager {

	private static Logger logger = Logger
			.getLogger(PreparedStatementManager.class);

	public void putArgs(PreparedStatement preparedStatement, Object... args)
			throws SQLException {

		int countParameter = 1;

		for (Object o : args) {
			try {
				setObject(preparedStatement, countParameter++, o);
			} catch (NoSuchDAOTypeException e) {
				logger.error(e);
			}
		}
	}

	private void setObject(PreparedStatement preparedStatement, int count,
			Object o) throws NoSuchDAOTypeException, SQLException {

		if (o == null) {
			preparedStatement.setObject(count, null);
			return;
		}

		SetterEnum setter = new SetterCreator().findByType(o.getClass());
		if (setter == null) {
			preparedStatement.setObject(count, null);
			throw new NoSuchDAOTypeException();
		}
		setter.executeSet(preparedStatement, count, o);
	}

}
