package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class BaseDAO {
	private Connection connection;
	private String url = "jdbc:mariadb://localhost/sigaz";
	private String user = "poo";
	private String password = "poo";

	public Connection getConnection() {
		if (this.connection == null) {
			try {
				this.connection = DriverManager.getConnection(this.url, this.user, this.password);
			} catch (SQLException e) {
				System.out.println("Conexão falhou");
				e.printStackTrace();
			}
		}

		return this.connection;
	}
}
