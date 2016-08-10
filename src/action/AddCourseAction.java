package action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import DBAccessor.DBUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class AddCourseAction extends ActionSupport {
	private String cname, cnum, instr, coutc;

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCnum() {
		return cnum;
	}

	public void setCnum(String cnum) {
		this.cnum = cnum;
	}

	public String getInstr() {
		return instr;
	}

	public void setInstr(String instr) {
		this.instr = instr;
	}

	public String getCoutc() {
		return coutc;
	}

	public void setCoutc(String coutc) {
		this.coutc = coutc;
	}

	@SuppressWarnings("unchecked")
	public String execute() {
		try {
			Map<String, Object> attr = (Map<String, Object>) ActionContext
					.getContext().get("session");
			Connection conn = new DBUtil().getConnection();
			boolean isInsertFacultySuccess = insertToFaculty(conn,attr);
			if(isInsertFacultySuccess){
				String query = "INSERT INTO course VALUES (?,?,?,?,?)";
				PreparedStatement prepared = conn.prepareStatement(query);
				prepared.setString(1, cname);
				prepared.setString(2, cnum);
				prepared.setString(3, instr);
				prepared.setString(4, coutc);
				prepared.setString(5, attr.get("universityOfLoggedUser").toString());
				int count = prepared.executeUpdate();
				prepared.close();
				conn.close();
				if (count == 0) {
					return "addCourseAction-failed";
				} else {
					return "addCourseAction-success";
				}
			}
		} catch (SQLException e) {
			System.out.println("Exception while adding course."
					+ e.getMessage());
			e.printStackTrace();
		}
		return "addCourseAction-failed";
	}

	private boolean insertToFaculty(Connection conn,Map<String, Object> attr) {
		try {
			//Faculty should have university associated with him by this point.
			String query = "UPDATE faculty SET coursename=?,coursenum=?,courseoutcome=? WHERE fname='"+getInstr()+"' and university='"+attr.get("universityOfLoggedUser").toString()+"'";
			PreparedStatement prepared = conn.prepareStatement(query);
			prepared.setString(1, cname);
			prepared.setString(2, cnum);
			prepared.setString(3, coutc);
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
}
