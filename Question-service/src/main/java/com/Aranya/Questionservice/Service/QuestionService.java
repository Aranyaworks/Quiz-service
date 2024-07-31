package com.Aranya.Questionservice.Service;


import com.Aranya.Questionservice.Model.Question;
import com.Aranya.Questionservice.Model.QuestionWrapper;
import com.Aranya.Questionservice.Model.Response;
import com.Aranya.Questionservice.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class QuestionService {
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions(){
        try {
         return  new ResponseEntity<>(questionDao.findAll(),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
        try {
            return new ResponseEntity<>( questionDao.findByCategory(category),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> addQuestion(Question question) {
        questionDao.save(question);
        return new ResponseEntity<>("Success",HttpStatus.CREATED) ;
    }

    public ResponseEntity<String>  deleteQuestion(Integer id) {
        questionDao.deleteById(id);
        return new ResponseEntity<>("Deleted",HttpStatus.ACCEPTED);
    }

    public ResponseEntity<List<Integer>> getQuestionForQuiz(String category, Integer numQ) {

        List<Integer> questions=questionDao.findRandomQuestionByCategory(category,numQ);

        return new ResponseEntity<>(questions, HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> qIDs) {
        List<Question> questions=new ArrayList<>();
        List<QuestionWrapper> wrappedQuestions=new ArrayList<>();
        for(Integer i:qIDs){
            questions.add(questionDao.findById(i).get());
        }
        for(Question q:questions){
            QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            wrappedQuestions.add(qw);
        }
        return new ResponseEntity<>(wrappedQuestions,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        Integer result=0;

        for(Response r:responses){
            if(r.getUserResponse().equals(questionDao.findById(r.getId()).get().getRightAnswer())){
                result++;
            }
        }
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
