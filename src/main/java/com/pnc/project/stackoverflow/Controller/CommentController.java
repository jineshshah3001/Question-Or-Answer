package com.pnc.project.stackoverflow.Controller;

import com.pnc.project.stackoverflow.Config.JwtTokenUtil;
import com.pnc.project.stackoverflow.Entity.Answer;
import com.pnc.project.stackoverflow.Entity.Comment;
import com.pnc.project.stackoverflow.Entity.Question;
import com.pnc.project.stackoverflow.Entity.User;
import com.pnc.project.stackoverflow.Service.QuestionService;
import com.pnc.project.stackoverflow.Service.SequenceGeneratorService;
import com.pnc.project.stackoverflow.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CommentController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/{questionId}/addCommentToQuestion")
    public void addCommentToQuestion(@RequestHeader String Authorization ,@PathVariable String questionId , @RequestBody Comment comment){
        Optional<Question> question = questionService.findById(questionId);

        String jwtToken = Authorization.substring(7);
        String email  = jwtTokenUtil.getUsernameFromToken(jwtToken);
        User user = userService.findByEmail(email);

        if(question.isPresent()){
            Question newQuestion = question.get();
            comment.setId(sequenceGeneratorService.generateSequence(Question.SEQUENCE_NAME));
            comment.setUser(user);
            if(newQuestion.getComments()!=null){
                newQuestion.getComments().add(comment);
            }
            else{
                List<Comment> comments = new ArrayList<>();
                comments.add(comment);
                newQuestion.setComments(comments)   ;
            }

            questionService.postQuestion(newQuestion);
        }
    }

    @PostMapping("/{answerId}/addCommentToAnswer")
        public void addCommentToAnswer(@RequestHeader String Authorization ,@PathVariable Long answerId , @RequestBody Comment comment) {
            Question question = questionService.findQuestionByAnswerId(answerId);
        String jwtToken = Authorization.substring(7);
        String email  = jwtTokenUtil.getUsernameFromToken(jwtToken);
        User user = userService.findByEmail(email);


            List<Answer> answers = question.getAnswers();
            Optional<Answer> answer = answers.stream().filter(A -> A.getId() == answerId).findFirst();
            if (answer.isPresent()) {
                Answer newAnswer = answer.get();
                comment.setUser(user);
                comment.setId(sequenceGeneratorService.generateSequence(Question.SEQUENCE_NAME));
                if (newAnswer.getComments() != null) {
                    newAnswer.getComments().add(comment);
                } else {
                    List<Comment> comments = new ArrayList<>();
                    comments.add(comment);
                    newAnswer.setComments(comments);
                }

                question.setAnswers(answers);
                questionService.postQuestion(question);
            }


    }

}
