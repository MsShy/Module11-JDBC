package homeworks.task1;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;

public class Main {
	public static void main(String[] args) {


		ConnectionFactory connectionFactory =new ConnectionFactory("jdbc:h2:D:\\homeworks\\Module11-JDBC\\test","sa","");
		try (Connection connection = connectionFactory.getConnection()) {
			connection.setAutoCommit(false);
			Functionality functionality = new Functionality(connection);
			functionality.selectSpecificInformation();
			functionality.selectAll("users");
			int insert;
			insert = functionality.insert();
			if (insert != -1) {
				functionality.selectAll("users");
				Savepoint savepointToInsert = connection.setSavepoint("savepoint1");
				functionality.update();
				functionality.selectAll("users");
				int delete = functionality.delete();
				if (delete == -1) {
					connection.rollback(savepointToInsert);
					System.out.println("Rollback to insert statement");
				} else {
					connection.commit();
				}
			} else {
				connection.setAutoCommit(true);
			}
			functionality.selectAll("users");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
