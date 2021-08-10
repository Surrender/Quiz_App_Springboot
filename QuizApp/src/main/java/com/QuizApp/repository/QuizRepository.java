package com.QuizApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.QuizApp.model.QuizResponse;

public interface QuizRepository extends JpaRepository<QuizResponse, Long> {

}
