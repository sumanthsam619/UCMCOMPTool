package action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;

import model.Questions;
import DBAccessor.DBUtil;

public class FinalTestTakeAction implements SessionAware {
	public SessionMap<String, Object> userSession;
	
	@SuppressWarnings("unchecked")
	public String execute() {
		try {
			Map<String,Object> attr = (Map<String,Object>) ActionContext.getContext().get("session");
			ArrayList<Questions> questionsList = new ArrayList<Questions>();
			Questions question;
			Connection conn = new DBUtil().getConnection();
			Statement stmt = conn.createStatement();
			String query = "select * from questions where university='"+attr.get("universityOfLoggedUser").toString()+"'";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				question = new Questions();
				question.setQuestion(rs.getString("question"));
				question.setOptiona(rs.getString("optiona"));
				question.setOptionb(rs.getString("optionb"));
				question.setOptionc(rs.getString("optionc"));
				question.setOptiond(rs.getString("optiond"));
				question.setAnswer(rs.getString("answer"));
				question.setCoursename(rs.getString("coursename"));
				question.setCourseoutcome(rs.getString("courseoutcome"));
				question.setInstructor(rs.getString("instructor"));
				questionsList.add(question);
			}
			userSession.put("questionsList", questionsList);
			userSession.put("actualNoOfQuestions", questionsList.size());
			if (questionsList.size() != 0) {
				return "finalTestTakeAction-success";
			}
			return "finalTestTakeAction-failed";
		} catch (SQLException e) {
			System.out.println("Exception in finalTestTaken Action.."
					+ e.getMessage());
			e.printStackTrace();
			return "finalTestTakeAction-failed";
		}
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		userSession = (SessionMap<String, Object>) session;
		@SuppressWarnings("unchecked")
		Map<String, Object> attr = (Map<String, Object>) ActionContext
				.getContext().get("session");
	}
	
}
