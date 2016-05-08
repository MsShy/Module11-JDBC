package homeworks.task1;

import java.sql.*;

public class Functionality {


	private Connection connection;



	public Functionality(final Connection connection) {
		this.connection = connection;

	}

	public void selectAll(String tableName) {
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

	public void update() {
		String update = "UPDATE USERS SET YEAROFBIRTH=1988 WHERE FIRSTNAME='Yurik'";
		try (PreparedStatement statement = connection.prepareStatement(update)) {
			int rows = statement.executeUpdate();
			System.out.println("Update count = " + rows);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public int delete() {
		int rows = -1;
		return rows;
//		String field = "DELETE from USERS WHERE ID >=?";
//		try (PreparedStatement statement = connection.prepareStatement(field)) {
//				statement.setInt(1, 34);
//			rows = statement.executeUpdate();
//			System.out.println("Delete count = " + rows);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return rows;
	}


}
