package com.epam.core.preperedstatement;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public enum SetterEnum {

	INT(Integer.class) {
		@Override
		void executeSet(PreparedStatement preparedStatement, int n, Object o)
				throws SQLException {
			preparedStatement.setInt(n, (Integer) o);
		}
	},

	LONG(Long.class) {
		@Override
		void executeSet(PreparedStatement preparedStatement, int n, Object o)
				throws SQLException {
			preparedStatement.setLong(n, (Long) o);
		}
	},

	DOUBLE(Double.class) {
		@Override
		void executeSet(PreparedStatement preparedStatement, int n, Object o)
				throws SQLException {
			preparedStatement.setDouble(n, (Double) o);
		}
	},

	STRING(String.class) {
		@Override
		void executeSet(PreparedStatement preparedStatement, int n, Object o)
				throws SQLException {
			preparedStatement.setString(n, (String) o);
		}
	},

	BOOLEAN(Boolean.class) {
		@Override
		void executeSet(PreparedStatement preparedStatement, int n, Object o)
				throws SQLException {
			preparedStatement.setBoolean(n, (Boolean) o);
		}
	},

	DATE(Date.class) {
		@Override
		void executeSet(PreparedStatement preparedStatement, int n, Object o)
				throws SQLException {
			preparedStatement.setDate(n,
					new java.sql.Date((((Date) o).getTime())));
		}
	},

	TIMESTAMP(Timestamp.class) {
		@Override
		void executeSet(PreparedStatement preparedStatement, int n, Object o)
				throws SQLException {
			preparedStatement.setTimestamp(n, (Timestamp) o);
		}
	};

	private Class<?> type;

	private SetterEnum(Class<?> type) {
		this.type = type;
	}

	abstract void executeSet(PreparedStatement preparedStatement, int n,
			Object o) throws SQLException;

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}

}
