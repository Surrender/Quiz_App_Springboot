package com.QuizApp.model.response;

public class AnswerResponseModel {

    private String Id;
    private String answerTitle;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        this.Id = id;
    }

    public String getAnswerTitle() {
        return answerTitle;
    }

    public void setAnswerTitle(String answerTitle) {
        this.answerTitle = answerTitle;
    }
}
