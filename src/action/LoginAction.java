package action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import DBAccessor.DBUtil;

import com.opensymphony.xwork2.ActionSupport;

import dbfiles.LoginValidationUtil;

@SuppressWarnings("serial")
public class LoginAction extends ActionSupport implements SessionAware {
	private String username, password;
	private SessionMap<String, Object> userSession;
	String role;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String execute() {
		boolean isUnivSet = getUnivForLoggedUser(username, password);
		if (isUnivSet) {

			role = new LoginValidationUtil().verifyCredentials(username,
					password);
			userSession.put("role", role);
			userSession.put("uname", username);
			switch (role) {
			case "A":
				userSession.put("login", "true");
				return "admin-login-success";

			case "F":
				userSession.put("login", "true");
				return "faculty-login-success";

			case "S":
				userSession.put("login", "true");
				return "student-login-success";

			case "SA":
				userSession.put("login", "true");
				return "superadmin-login-success";

			case "NA":
				userSession.put("login", "false");
				return "login-failed";
			}
		}
		return "login-failed";
	}

	private boolean getUnivForLoggedUser(String username2, String password2) {
		try {
			DBUtil dbUtil = new DBUtil();
			Connection conn = dbUtil.getConnection();
			Statement stmt = conn.createStatement();
			String query = "select university from UserDetails where username='"
					+ username2 + "' and password ='" + password2 + "'";
			ResultSet res = stmt.executeQuery(query);
			if (res.next()) {
				userSession.put("universityOfLoggedUser",
						res.getString("university"));
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		userSession = (SessionMap<String, Object>) session;
	}

	public String logout() {
		if (userSession != null) {
			userSession.invalidate();
		}
		return "success";
	}
}
