package com.QuizApp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "quiz_response")
public class QuizResponse implements Serializable {

	private static final long serialVersionUID = -2800355974329998316L;
	
	@Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
	private String username;
    
    @Column
    private int quesId;
    
    @Column(nullable = false)
    private String answer;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getQuesId() {
		return quesId;
	}

	public void setQuesId(int quesId) {
		this.quesId = quesId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}   
    
}
