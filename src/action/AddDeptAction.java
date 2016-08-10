package action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import DBAccessor.DBUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class AddDeptAction extends ActionSupport {
	private String dname, dhead, demail;

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getDhead() {
		return dhead;
	}

	public void setDhead(String dhead) {
		this.dhead = dhead;
	}

	public String getDemail() {
		return demail;
	}

	public void setDemail(String demail) {
		this.demail = demail;
	}

	public String execute() {

		try {
			@SuppressWarnings("unchecked")
			Map<String, Object> attr = (Map<String, Object>) ActionContext
					.getContext().get("session");
			System.out.println("Univer of logged in user from execute() of AddDeptAction-->"+attr.get("universityOfLoggedUser"));
			Connection conn = new DBUtil().getConnection();
			String query = "INSERT INTO department VALUES (?,?,?,?)";
			PreparedStatement prepared = conn.prepareStatement(query);
			prepared.setString(1, dname);
			prepared.setString(2, dhead);
			prepared.setString(3, demail);
			prepared.setString(4, attr.get("universityOfLoggedUser").toString());
			int count =prepared.executeUpdate();
			prepared.close();
			conn.close();
			if(count==0){
				return "addDeptAction-failed";
			}else{
				return "addDeptAction-success";
			}
			
		} catch (SQLException e) {
			System.out.println("Exception while adding course."+e.getMessage());
			e.printStackTrace();
		}
		return "addDeptAction-failed";

}
}
