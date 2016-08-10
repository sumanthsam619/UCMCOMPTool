package action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import DBAccessor.DBUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class AddUserAction extends ActionSupport {
	private String fname, lname, email, role, department;

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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@SuppressWarnings("unchecked")
	public String execute() {

		try {
			Map<String, Object> attr = (Map<String, Object>) ActionContext
					.getContext().get("session");
			Connection conn = new DBUtil().getConnection();
			String query;
			boolean successfulylInsertedUserDetails = insertIntoUserDetais(conn,attr);
			if (successfulylInsertedUserDetails) {
				if (role.equalsIgnoreCase("student")) {
					query = "INSERT INTO student(fname,lname,email,role,department,university) VALUES (?,?,?,?,?,?)";
				} else {
					query = "INSERT INTO faculty(fname,lname,email,role,department,university) VALUES (?,?,?,?,?,?)";
				}
				PreparedStatement prepared = conn.prepareStatement(query);
				prepared.setString(1, fname);
				prepared.setString(2, lname);
				prepared.setString(3, email);
				prepared.setString(4, role);
				prepared.setString(5, department);
				prepared.setString(6, attr.get("universityOfLoggedUser").toString());
				int count = prepared.executeUpdate();
				prepared.close();
				conn.close();
				if (count == 0) {
					return "addUserAction-failed";
				} else {
					return "addUserAction-success";
				}
			}
		} catch (SQLException e) {
			System.out.println("Exception while adding course from execute of AddUserAction. "
					+ e.getMessage());
			e.printStackTrace();
		}
		return "addUserAction-failed";

	}

	private boolean insertIntoUserDetais(Connection conn,Map<String, Object> attr) throws SQLException {
		String query = "INSERT INTO userdetails VALUES (?,?,?,?)";
		PreparedStatement prepared = conn.prepareStatement(query);
		prepared.setString(1, email);
		prepared.setString(2, fname);
		if(role.equalsIgnoreCase("faculty"))
			prepared.setString(3, "F");
		else
			prepared.setString(3, "S");
		prepared.setString(4, attr.get("universityOfLoggedUser").toString());
		int count = prepared.executeUpdate();
		prepared.close();
		if (count == 0) {
			return false;
		} else {
			return true;
		}
	}
}
