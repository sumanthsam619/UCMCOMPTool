package action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import DBAccessor.DBUtil;

public class AddEvalQuestionAction {
	private String courseSelected, question, optiona, optionb, optionc, optiond, correctOption;


	public String getCourseSelected() {
		return courseSelected;
	}


	public void setCourseSelected(String courseSelected) {
		this.courseSelected = courseSelected;
	}


	public String getQuestion() {
		return question;
	}


	public void setQuestion(String question) {
		this.question = question;
	}


	public String getOptiona() {
		return optiona;
	}


	public void setOptiona(String optiona) {
		this.optiona = optiona;
	}


	public String getOptionb() {
		return optionb;
	}


	public void setOptionb(String optionb) {
		this.optionb = optionb;
	}


	public String getOptionc() {
		return optionc;
	}


	public void setOptionc(String optionc) {
		this.optionc = optionc;
	}


	public String getOptiond() {
		return optiond;
	}


	public void setOptiond(String optiond) {
		this.optiond = optiond;
	}


	public String getCorrectOption() {
		return correctOption;
	}


	public void setCorrectOption(String correctOption) {
		this.correctOption = correctOption;
	}


	@SuppressWarnings("unchecked")
	public String execute() {
		String courseOutcomes="";
		try {
			Map<String, Object> attr = (Map<String, Object>) ActionContext
					.getContext().get("session");
			Connection conn = new DBUtil().getConnection();
			Statement stmt = conn.createStatement();
			String query = "select courseoutcome from course where coursename='"
					+ courseSelected + "'";
			ResultSet res = stmt.executeQuery(query);
			while (res.next()) {
				courseOutcomes = res.getString("courseoutcome");
			}
			query="insert into questions values(?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement prepared = conn.prepareStatement(query);
			prepared.setString(1, question);
			prepared.setString(2, optiona);
			prepared.setString(3, optionb);
			prepared.setString(4, optionc);
			prepared.setString(5, optiond);
			prepared.setString(6, correctOption);
			prepared.setString(7, courseSelected);
			prepared.setString(8, getLoggedInInstrName());
			prepared.setString(9, courseOutcomes);
			prepared.setString(10, attr.get("universityOfLoggedUser").toString());
			
			int count = prepared.executeUpdate();
			prepared.close();
			conn.close();
			if (count == 0) {
				return "addEvalAction-failed";
			} else {
				return "addEvalAction-success";
			}
		} catch (SQLException e) {
			System.out
					.println("Exception in execute() of AddEvalQuestionAction."
							+ e.getMessage());
			e.printStackTrace();
			return "addEvalAction-failed";
		}
	}


	private String getLoggedInInstrName() {
		@SuppressWarnings("unchecked")
		Map<String,Object> attr = (Map<String,Object>) ActionContext.getContext().get("session");
		return (String) attr.get("uname");
	}
}
