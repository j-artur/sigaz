package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {
	private static BaseDAO instance;

	private Connection connection;
	private String url = "jdbc:mariadb://localhost/sigaz";
	private String user = "poo";
	private String password = "poo";

	private BaseDAO() {
	}

	public static BaseDAO getInstance() {
		if (instance == null) {
			instance = new BaseDAO();
		}

		return instance;
	}

	public Connection getConnection() throws SQLException {
		if (this.connection == null) {
			this.connection = DriverManager.getConnection(this.url, this.user, this.password);
		}

		return this.connection;
	}
}
