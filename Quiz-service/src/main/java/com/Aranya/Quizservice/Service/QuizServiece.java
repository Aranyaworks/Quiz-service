package com.Aranya.Quizservice.Service;


import com.Aranya.Quizservice.Model.Question;
import com.Aranya.Quizservice.Model.QuestionWrapper;
import com.Aranya.Quizservice.Model.Quiz;
import com.Aranya.Quizservice.Model.Response;
import com.Aranya.Quizservice.dao.QuizDao;
import com.Aranya.Quizservice.feign.QuizInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service


public class QuizServiece {

    @Autowired
    QuizDao quizDao;
    @Autowired
    QuizInterface quizInterface;


    public ResponseEntity<String> createQuiz(String category,String title,int numQ) {

        List<Integer> questions= quizInterface.getQuestionForQuiz(category,numQ).getBody();
        Quiz quiz= new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIDs(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("Created a quiz", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz=quizDao.findById(id);
        List<Integer> questionIDFromDB = quiz.get().getQuestionIDs();
        List<QuestionWrapper> questionForUser = quizInterface.getQuestionsFromId(questionIDFromDB).getBody();

        return new ResponseEntity<>(questionForUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> RightAnswers( List<Response> responses) {

        Integer rightAnswers=quizInterface.getScore(responses).getBody();


        return new ResponseEntity<>(rightAnswers,HttpStatus.OK);
    }
}
