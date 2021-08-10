package com.QuizAppSubscriber.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.QuizAppSubscriber.model.Question;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer> {

}