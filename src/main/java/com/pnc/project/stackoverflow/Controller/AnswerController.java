package com.pnc.project.stackoverflow.Controller;

import com.pnc.project.stackoverflow.Entity.Answer;
import com.pnc.project.stackoverflow.Entity.Question;
import com.pnc.project.stackoverflow.Service.QuestionService;
import com.pnc.project.stackoverflow.Service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class AnswerController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @PostMapping("/{id}/addAnswer")
    public void addAnswer(@PathVariable String id , @RequestBody Answer answer){

        Optional<Question> question = questionService.findById(id);

        if(question.isPresent()){
            Question newQuestion = question.get();
            answer.setId(sequenceGeneratorService.generateSequence(Answer.SEQUENCE_NAME));
            if(newQuestion.getAnswers()!=null){
                newQuestion.getAnswers().add(answer);
                newQuestion.setNumberOfAnswers(newQuestion.getAnswers().size());
            }
            else{
                List<Answer> answers = new ArrayList<>();
                answers.add(answer);
                newQuestion.setAnswers(answers);
                newQuestion.setNumberOfAnswers(1);
            }

            questionService.postQuestion(newQuestion);
        }


    }
}
