package com.Aranya.Questionservice.Controller;


import com.Aranya.Questionservice.Model.Question;
import com.Aranya.Questionservice.Model.QuestionWrapper;
import com.Aranya.Questionservice.Model.Response;
import com.Aranya.Questionservice.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions")
public class QuestionController {

    @Autowired
    QuestionService questionService;
    @GetMapping("Allquestions")
    public ResponseEntity<List<Question>> getAllQuestions(){

        return questionService.getAllQuestions();

    }
    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>>getQuestionByCategory(@PathVariable String category){
        return questionService.getQuestionByCategory(category);
    }
    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }
    @PostMapping("delete")
    public ResponseEntity <String> deleteQuestion(@RequestBody Integer id){
        return questionService.deleteQuestion(id);
    }
    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionForQuiz(@RequestParam String category,@RequestParam Integer numQ){
       return questionService.getQuestionForQuiz(category,numQ);
    }
    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> qIDs){
        return questionService.getQuestionsFromId(qIDs);
    }
    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return questionService.getScore(responses);
    }
}
