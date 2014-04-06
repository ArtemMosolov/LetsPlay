package com.epam.controller.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionManager {

	private static ConnectionManager instance;
	private static final String DRIVE_CLASS_NAME = "com.mysql.jdbc.Driver";

	private ConnectionManager() {
	}

	public static synchronized ConnectionManager getInstance() {
		if (instance == null)
			instance = new ConnectionManager();
		return instance;
	}

	public Connection getConnection() throws SQLException {
		Connection connection = null;
		try {
			Class.forName(DRIVE_CLASS_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			InitialContext initContext = new InitialContext();
			DataSource ds = (DataSource) initContext
					.lookup("java:comp/env/jdbc/lets_play");
			connection = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		}

		return connection;
	}
}
