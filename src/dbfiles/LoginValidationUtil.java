package dbfiles;

import DBAccessor.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginValidationUtil {
	public String verifyCredentials(String uname, String pwd) {
		try {
			DBUtil dbUtil = new DBUtil();
			Connection conn = dbUtil.getConnection();
			Statement stmt = conn.createStatement();
			String query = "select * from UserDetails where username='" + uname
					+ "' and password ='" + pwd+"'";
			ResultSet res = stmt.executeQuery(query);
			if (res.next())
				return res.getString("role");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "NA";
	}
}
