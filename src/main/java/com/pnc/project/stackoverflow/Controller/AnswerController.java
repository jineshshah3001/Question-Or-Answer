package com.pnc.project.stackoverflow.Controller;

import com.pnc.project.stackoverflow.Entity.Answer;
import com.pnc.project.stackoverflow.Entity.Question;
import com.pnc.project.stackoverflow.Entity.User;
import com.pnc.project.stackoverflow.Service.QuestionService;
import com.pnc.project.stackoverflow.Service.SequenceGeneratorService;
import com.pnc.project.stackoverflow.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    private UserService userService;

    @PostMapping("/{userId}/{id}/addAnswer")
    public void addAnswer(@PathVariable String userId , @PathVariable String id , @RequestBody Answer answer){

        Optional<Question> question = questionService.findById(id);
        Optional<User> user = userService.findById(userId);
        User newUser = null;
        if(user.isPresent()){
            newUser = user.get();
        }

        if(question.isPresent()){
            Question newQuestion = question.get();
            answer.setId(sequenceGeneratorService.generateSequence(Question.SEQUENCE_NAME));
            answer.setUser(newUser);
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
