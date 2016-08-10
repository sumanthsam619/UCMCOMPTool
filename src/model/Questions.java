package model;

import java.util.ArrayList;

public class Questions {
	private String question, optiona, optionb, optionc, optiond, answer,
			coursename, instructor, courseoutcome,university;
	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	private ArrayList<Questions> updatedList;

	public Questions() {

	}

	public ArrayList<Questions> getUpdatedList() {
		return updatedList;
	}

	public void setUpdatedList(ArrayList<Questions> updatedList) {
		this.updatedList = updatedList;
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

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public String getCourseoutcome() {
		return courseoutcome;
	}

	public void setCourseoutcome(String courseoutcome) {
		this.courseoutcome = courseoutcome;
	}
}
