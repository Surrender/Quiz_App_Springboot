package com.QuizAppSubscriber.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "questions_sub")
public class Question {

	@Id
	private int quesId;
	@Column
	private String title;
	@Column
	private String optionA;
	@Column
	private String optionB;
	@Column
	private String optionC;
	@Column
	private String optionD;
	@Column
	private int answer;
	@Column
	private int choice;

	public Question() {
		super();
	}

	public Question(int quesId, String title, String optionA,
			String optionB, String optionC, String optionD, int answer, int choice) {
		super();
		this.quesId = quesId;
		this.title = title;
		this.optionA = optionA;
		this.optionB = optionB;
		this.optionC = optionC;
		this.optionD = optionD;
		this.answer = answer;
		this.choice = choice;
	}

	public int getQuesId() {
		return quesId;
	}

	public void setQuesId(int quesId) {
		this.quesId = quesId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOptionA() {
		return optionA;
	}

	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}

	public String getOptionB() {
		return optionB;
	}

	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}

	public String getOptionC() {
		return optionC;
	}

	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}

	public String getOptionD() {
		return optionD;
	}

	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}

	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}

	public int getChoice() {
		return choice;
	}

	public void setChoice(int choosed) {
		this.choice = choosed;
	}

	@Override
	public String toString() {
		return "Question [quesId=" + quesId + ", title=" + title + ", optionA=" + optionA + ", optionB=" + optionB + ", optionC=" + optionC + ", optionD=" + optionD + ", answer=" + answer + ", choice=" + choice + "]";
	}

}
