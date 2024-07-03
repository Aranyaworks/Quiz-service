package com.Aranya.Quizservice.dao;


import com.Aranya.Quizservice.Model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz,Integer> {
}
