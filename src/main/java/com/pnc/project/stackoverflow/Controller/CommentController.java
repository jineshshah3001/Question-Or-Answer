package com.pnc.project.stackoverflow.Controller;

import com.pnc.project.stackoverflow.Entity.Answer;
import com.pnc.project.stackoverflow.Entity.Comment;
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
public class CommentController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    private UserService userService;

    @PostMapping("/{userId}/{id}/addCommentToQuestion")
    public void addCommentToQuestion(@PathVariable String userId ,@PathVariable String id , @RequestBody Comment comment){
        Optional<Question> question = questionService.findById(id);
        Optional<User> user = userService.findById(userId);
        User newUser = null;
        if(user.isPresent()){
            newUser = user.get();
        }
        if(question.isPresent()){
            Question newQuestion = question.get();
            comment.setId(sequenceGeneratorService.generateSequence(Question.SEQUENCE_NAME));
            comment.setUser(newUser);
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

    @PostMapping("/{userId}/{answerId}/addCommentToAnswer")
    public void addCommentToAnswer(@PathVariable String userId , @PathVariable Long answerId , @RequestBody Comment comment) {
        Question question = questionService.findQuestionByAnswerId(answerId);
        Optional<User> user = userService.findById(userId);
        User newUser = null;
        if(user.isPresent()){
            newUser = user.get();
        }
        List<Answer> answers = question.getAnswers();
        Optional<Answer> answer = answers.stream().filter(A -> A.getId() == answerId).findFirst();
        if (answer.isPresent()) {
            Answer newAnswer = answer.get();
            comment.setUser(newUser);
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
