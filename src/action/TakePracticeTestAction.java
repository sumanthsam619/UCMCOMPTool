package action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.Questions;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import DBAccessor.DBUtil;

import com.opensymphony.xwork2.ActionContext;

public class TakePracticeTestAction implements SessionAware {
	private String courseSelected;
	private Questions question;
	private List<Questions> questionsList;
	public SessionMap<String, Object> userSession;

	public String getCourseSelected() {
		return courseSelected;
	}

	public void setCourseSelected(String courseSelected) {
		this.courseSelected = courseSelected;
	}

	@SuppressWarnings("unchecked")
	public String execute() {
		try {
			Map<String,Object> attr = (Map<String,Object>) ActionContext.getContext().get("session");
			questionsList = new ArrayList<Questions>();
			Connection conn = new DBUtil().getConnection();
			Statement stmt = conn.createStatement();
			String query = "select * from questions where coursename='"+getCourseSelected()+"' and university='"+attr.get("universityOfLoggedUser").toString()+"'";
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
				question.setUniversity(rs.getString("university"));
				questionsList.add(question);
			}
			userSession.put("questionsList", questionsList);
			userSession.put("actualNoOfQuestions", questionsList.size());
			if(questionsList.size()!=0){
				return "takePracticeTest-success";	
			}
			return "takePracticeTest-failed";
		} catch (SQLException e) {
			System.out.println("Exception in TakePracticeTest Action : "
					+ e.getMessage());
			e.printStackTrace();
			return "takePracticeTest-failed";
		}
	}

	@Override
	public void setSession(Map<String, Object> session) {
		userSession = (SessionMap<String, Object>) session;
		@SuppressWarnings("unchecked")
		Map<String, Object> attr = (Map<String, Object>) ActionContext.getContext().get("session");
	}
}
