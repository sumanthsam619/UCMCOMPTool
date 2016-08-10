package action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import DBAccessor.DBUtil;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class RegisterAdminAction extends ActionSupport implements SessionAware {

	private String fname, lname, email, password, university;
	private SessionMap<String, Object> userSession;

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public String execute() {
		try {
			Connection conn = new DBUtil().getConnection();
			boolean isUserDetailsInsrt = insertToUserDetails(conn);
			userSession.put("role", "A");
			userSession.put("uname", getEmail());
			userSession.put("login", "true");
			System.out.println("execte query-->");
			if (isUserDetailsInsrt) {
				String query = "INSERT INTO admin VALUES (?,?,?,?,?)";
				System.out.println("execte query-->" + query);
				PreparedStatement prepared = conn.prepareStatement(query);
				prepared.setString(1, getFname());
				prepared.setString(2, getLname());
				prepared.setString(3, getEmail());
				prepared.setString(4, getPassword());
				prepared.setString(5, getUniversity());
				int count = prepared.executeUpdate();
				prepared.close();
				conn.close();
				if (count == 0) {
					return "registerAdminAction-failed";
				} else {
					return "registerAdminAction-success";
				}
			}

		} catch (SQLException e) {
			System.out.println("Exception while adding admin." + e.getMessage());
			e.printStackTrace();
		}
		return "registerAdminAction-failed";
	}

	private boolean insertToUserDetails(Connection conn) throws SQLException {
			String query = "INSERT INTO userdetails values(?,?,?,?)";
			PreparedStatement prepared = conn.prepareStatement(query);
			prepared.setString(1, getEmail());
			prepared.setString(2, getPassword());
			prepared.setString(3, "A");
			prepared.setString(4, getUniversity());
			userSession.put("universityOfLoggedUser",getUniversity());
			int count = prepared.executeUpdate();
			prepared.close();
			if (count == 0) {
				return false;
			} else {
				return true;
			}
	}

	private boolean getUnivForLoggedUser(Connection conn, String username2, String password2) {
		try {
			System.out.println("getUnivForLoggedUser-->");
			if (isUnivDetails(conn)) {
				DBUtil dbUtil = new DBUtil();
				Statement stmt = conn.createStatement();
				String query = "select university from UserDetails where username='" + username2 + "' and password ='"
						+ password2 + "'";
				System.out.println("getUnivForLoggedUser query-->" + query);
				ResultSet res = stmt.executeQuery(query);
				if (res.next()) {
					userSession.put("universityOfLoggedUser", res.getString("university"));
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	private boolean isUnivDetails(Connection conn) {
		try {
			String query = "INSERT INTO university values(?)";
			PreparedStatement prepared = conn.prepareStatement(query);
			prepared.setString(1, getUniversity());
			int count = prepared.executeUpdate();
			prepared.close();
			if (count == 0) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void setSession(Map<String, Object> session) {
		userSession = (SessionMap<String, Object>) session;
	}

}
