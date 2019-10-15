package com.pnc.project.stackoverflow.Controller;

import com.pnc.project.stackoverflow.Entity.Question;
import com.pnc.project.stackoverflow.Entity.User;
import com.pnc.project.stackoverflow.Service.QuestionService;
import com.pnc.project.stackoverflow.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/stackoverflow/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Question> findAll(){
        return questionService.findAll();
    }

    @PostMapping("/{userId}")
    public Question postQuestion(@PathVariable String userId ,@RequestBody Question question){
         question.setDateAsked(new Date());
         Optional<User> user = userService.findById(userId);
        if(user.isPresent()) {
            User newUser = user.get();
            question.setUser(newUser);
        }

         questionService.postQuestion(question);
         return question;
    }

    @GetMapping("/{id}")
    public Optional<Question> findById(@PathVariable String id){
        Optional<Question> question = questionService.findById(id);
        return question;

    }



}
