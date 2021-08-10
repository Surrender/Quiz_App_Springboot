package com.QuizApp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.QuizApp.model.Answer;
import com.QuizApp.model.Question;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {
    Question findById(long id);
    Question findByQuestionTitle(String questionTitle);
    Answer findByAnswers(String answerTitle);
}
