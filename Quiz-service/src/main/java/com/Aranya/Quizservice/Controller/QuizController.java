package com.Aranya.Quizservice.Controller;



import com.Aranya.Quizservice.Model.QuestionWrapper;
import com.Aranya.Quizservice.Model.QuizDTO;
import com.Aranya.Quizservice.Model.Response;
import com.Aranya.Quizservice.Service.QuizServiece;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")

public class QuizController {
    @Autowired
    QuizServiece quizServiece;
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDTO dto){

        return quizServiece.createQuiz(dto.getCategory(), dto.getTitle(), dto.getNumQ());
    }
    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions (@PathVariable Integer id){
        return quizServiece.getQuizQuestions(id);
    }
    @PostMapping("submit")
    public ResponseEntity<Integer> RightAnswers(@RequestBody List<Response> responses){
        return quizServiece.RightAnswers( responses);
    }
}
