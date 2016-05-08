package homeworks.task1;

import java.sql.*;

public class ConnectionFactory {

	private final String url;
	private final String user;
	private final String pass;

	public ConnectionFactory(final String url, final String user, final String pass) {
		this.url = url;
		this.user = user;
		this.pass = pass;
		try {
			Class.forName("org.h2.Driver");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		try {
			return DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
