package com.QuizApp.model.request;

import java.util.List;

public class QuestionRequestModel {

    private String questionTitle;
    private String correctAnswer;
    private List<AnswerRequestModel> answers; // set for unique values

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

	public List<AnswerRequestModel> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerRequestModel> answers) {
        this.answers = answers;
    }
}

