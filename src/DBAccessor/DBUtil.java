package DBAccessor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
	Connection connection = null;

	/**
	 * Gives connection object to connect to DB
	 * 
	 * @return connection
	 */
	public Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			Properties props = new Properties();
			props.setProperty("user","postgres");
			props.setProperty("password","1221");
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/UCMCompToolDB", props);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * Closes connection
	 * 
	 * @param con
	 * @return boolean
	 */
	public boolean closeConnection(Connection con) {
		try {
			if (con != null) {
				con.close();
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
