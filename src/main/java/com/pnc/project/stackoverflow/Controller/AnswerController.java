package com.pnc.project.stackoverflow.Controller;

import com.pnc.project.stackoverflow.Config.JwtTokenUtil;
import com.pnc.project.stackoverflow.Entity.Answer;
import com.pnc.project.stackoverflow.Entity.Question;
import com.pnc.project.stackoverflow.Entity.User;
import com.pnc.project.stackoverflow.Service.QuestionService;
import com.pnc.project.stackoverflow.Service.SequenceGeneratorService;
import com.pnc.project.stackoverflow.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/stackoverflow")
public class AnswerController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/questions/{questionId}/answer/submit")
    public void addAnswer(@RequestHeader String Authorization , @PathVariable String questionId , @RequestBody Answer answer){

        Optional<Question> question = questionService.findById(questionId);
        String jwtToken = Authorization.substring(7);
        String email  = jwtTokenUtil.getUsernameFromToken(jwtToken);
        User user = userService.findByEmail(email);
        answer.setDateCreated(new Date(System.currentTimeMillis()));
        if(question.isPresent()){
            Question newQuestion = question.get();
            answer.setId(sequenceGeneratorService.generateSequence(Question.SEQUENCE_NAME));
            answer.setAnsweredBy(user.getDisplayName());
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

    @PutMapping("/answers/{answerId}")
    public void updateAnswer(@RequestHeader String Authorization , @PathVariable Long answerId , @RequestBody Answer answer)  {
        Question question = questionService.findQuestionByAnswerId(answerId);
        String jwtToken = Authorization.substring(7);
        String email  = jwtTokenUtil.getUsernameFromToken(jwtToken);
        User user = userService.findByEmail(email);
        List<Answer> answers = question.getAnswers();
        Optional<Answer> answerToBeUpdated = answers.stream().filter(A -> A.getId() == answerId).findFirst();
        Answer ans = null;
        if(answerToBeUpdated.isPresent()){
            ans = answerToBeUpdated.get();
        }

        ans.setBody(answer.getBody());
        ans.setEditedBy(user.getDisplayName());
        question.setAnswers(answers);
        questionService.postQuestion(question);

    }
}
