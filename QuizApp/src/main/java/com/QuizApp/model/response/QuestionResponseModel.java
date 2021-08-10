package com.QuizApp.model.response;

import java.util.List;

public class QuestionResponseModel {

    private String Id;
    private String questionTitle;
    private String correctAnswer;
    private List<AnswerResponseModel> answers; // set for unique values

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        this.Id = id;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public List<AnswerResponseModel> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerResponseModel> answers) {
        this.answers = answers;
    }
}
