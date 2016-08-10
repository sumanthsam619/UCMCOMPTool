package action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import model.Questions;
import DBAccessor.DBUtil;

import com.opensymphony.xwork2.ActionContext;

public class TestSubmittedAction {
	private String optionSelected_ID;
	private static int correctAnswersCnt = 0;
	private double marksObtained;

	public String getOptionSelected_ID() {
		return optionSelected_ID;
	}

	public void setOptionSelected_ID(String optionSelected_ID) {
		this.optionSelected_ID = optionSelected_ID;
	}

	public Questions getQuestionsBean() {
		return questionsBean;
	}

	public void setQuestionsBean(Questions questionsBean) {
		this.questionsBean = questionsBean;
	}

	Questions questionsBean;

	public Questions getQuestions() {
		return questionsBean;
	}

	public void setQuestions(Questions questionsBean) {
		this.questionsBean = questionsBean;
	}

	@SuppressWarnings("unchecked")
	public String captureAnswers() {
		try {
			Map<String, Object> attr = (Map<String, Object>) ActionContext
				.getContext().get("session");
		ArrayList<Questions> tempList = (ArrayList<Questions>) attr
				.get("questionsList");
		if(attr.get("correctAnswer").toString().equalsIgnoreCase(getOptionSelected_ID())){
			correctAnswersCnt+=1;
		}
		
		if(tempList.size()!=0)
			return "takePracticeTest-success";
		else
		{
			attr.put("answeredCorrectly", correctAnswersCnt);
			correctAnswersCnt=0;
			if(attr.get("isPracticeTest").toString().equalsIgnoreCase("false")){
				System.out.println("Answered correctly:"+attr.get("answeredCorrectly"));
				System.out.println("Actual no of qstns:"+attr.get("actualNoOfQuestions").toString());
				marksObtained=(Double.parseDouble(attr.get("answeredCorrectly").toString())/Double.parseDouble(attr.get("actualNoOfQuestions").toString()))*100;
				System.out.println("marksObtained:"+marksObtained);
				Connection conn = new DBUtil().getConnection();
				String query="update student set marks=? where email=?";
				PreparedStatement prepared = conn.prepareStatement(query);
				prepared.setString(1, String.valueOf(marksObtained));
				prepared.setString(2, attr.get("uname").toString());
				int count = prepared.executeUpdate();
				prepared.close();
				conn.close();
				if (count == 0) {
					return "takePracticeTest-failed";
				} else {
					return "takePracticeTestResults-success";
				}
			}
			return "takePracticeTestResults-success";
		}
		}
		catch (Exception e) {
			System.out.println("Exception in TestSubmitted Action : "
					+ e.getMessage());
			e.printStackTrace();
			return "takePracticeTest-failed";
		}
	}

	public String execute() {
		// Map<String, Object> attr = (Map<String, Object>) ActionContext
		// .getContext().get("session");
		//
		// ArrayList<Questions> temp = (ArrayList<Questions>) attr
		// .get("questionsList");
		return "takePracticeTest-success";
	}
}
