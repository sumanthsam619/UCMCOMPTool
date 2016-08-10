package DBAccessor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDB {
	public static void main(String[] args) {

		Connection connection = null;

		try {

			Class.forName("org.postgresql.Driver");

		} catch (ClassNotFoundException e) {

			System.out.println("Where is your PostgreSQL JDBC Driver? "
					+ "Include in your library path!");
			e.printStackTrace();
			return;

		}

		try {

			connection = getConnection();

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;

		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
	}

	private static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(
				"jdbc:postgresql://localhost:5432/UCMCompToolDB", "postgres",
				"1221");
	}

}
