package homeworks.task2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User implements DaoManager {


	private Connection connection;


	public User(final Connection connection) {
		this.connection = connection;
	}

	@Override
	public void selectAll(final String tableName) {
		String select = String.format("SELECT * FROM %s ORDER BY ID", tableName);
		try (PreparedStatement statement = connection.prepareStatement(select);
		     ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				System.out.println(resultSet.getInt("ID") + " " + resultSet.getString("lastname") + " " +
						resultSet.getString("firstname") + " " + resultSet.getString("email") + " " + resultSet.getString("password") + " " +
						resultSet.getString("YEAROFBIRTH"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void selectSpecificInformation() {
		String field = "SELECT FIRSTNAME, EMAIL FROM USERS WHERE FIRSTNAME LIKE 'T%'";
		try (PreparedStatement statement = connection.prepareStatement(field);
		     ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				System.out.println(resultSet.getString("FIRSTNAME") + " " + resultSet.getString("EMAIL"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int insert() {
		int rows = -1;
		String insert = "INSERT INTO USERS (LASTNAME, FIRSTNAME, EMAIL, PASSWORD) VALUES (?, ?, ?, ?)";
		try (PreparedStatement statement = connection.prepareStatement(insert)) {
			statement.setString(1, "Yurash");
			statement.setString(2, "Yurik");
			statement.setString(3, "misterY@mail.com");
			statement.setString(4, "853254dsf");

			rows = statement.executeUpdate();
			System.out.println("Insert count = " + rows);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public void update() {
		String update = "UPDATE USERS SET YEAROFBIRTH=? WHERE FIRSTNAME=?";
		try (PreparedStatement statement = connection.prepareStatement(update)) {
			statement.setInt(1, 1988);
			statement.setString(2, "Yurik");
			int rows = statement.executeUpdate();
			System.out.println("Update count = " + rows);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int delete() {
		int rows = -1;

		String field = "DELETE from USERS WHERE ID >=?";
		try (PreparedStatement statement = connection.prepareStatement(field)) {
			statement.setInt(1, 34);
			rows = statement.executeUpdate();
			System.out.println("Delete count = " + rows);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}

}
