package com.QuizApp.service;

import java.util.Set;

import com.QuizApp.dto.QuestionDto;

public interface QuestionService {

    QuestionDto createQuestion(QuestionDto questionDto);
    Set<QuestionDto> getAllQuestions();
    QuestionDto getQuestionById(long id);
    void publishQuestionById(QuestionDto questionDto);
//    QuestionDto updateAnswer(AnswerDto answerDto, long id);
//    String deleteQuestionById(long id);
}
