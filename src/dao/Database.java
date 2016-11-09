package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

	// JDBC driver name and database URL
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost/giannism?useUnicode=true&characterEncoding=UTF-8";

	// Database credentials
	private static final String USER = "root";
	private static final String PASS = "*****";

	private static Connection conn = null;

	public static Connection open() throws SQLException {
		if (conn != null) {
			return conn;
		} else {
			// STEP 2: Register JDBC driver
			try {
				Class.forName(JDBC_DRIVER);
			} catch (ClassNotFoundException e) {
				throw new SQLException("MySQL Driver error: " + e.getMessage());

			}

			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			return conn;
		}

	}

	public static void close() throws SQLException {

		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			throw new SQLException("Couldn't close Db connection: " + e.getMessage());
		}
	}

}
