package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleConnectionFactory {
	public Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "123456");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
