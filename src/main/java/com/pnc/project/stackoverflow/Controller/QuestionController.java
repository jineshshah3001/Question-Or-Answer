package com.pnc.project.stackoverflow.Controller;

import com.pnc.project.stackoverflow.Config.JwtTokenUtil;
import com.pnc.project.stackoverflow.Entity.Question;
import com.pnc.project.stackoverflow.Entity.User;
import com.pnc.project.stackoverflow.Service.QuestionService;
import com.pnc.project.stackoverflow.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/stackoverflow/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @GetMapping
    public ResponseEntity<Page<Question>> getAllQuestions(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "3") Integer pageSize){


        Page<Question> allQuestions = questionService.getAllQuestions(pageNo , pageSize);
        System.out.println(allQuestions.getTotalPages());
        return new ResponseEntity<Page<Question>>(allQuestions, HttpStatus.OK);
    }

    @PostMapping("/submit")
    public Question postQuestion(@RequestHeader String Authorization , @RequestBody Question question){
         question.setDateAsked(new Date());
         String jwtToken = Authorization.substring(7);
         String email  = jwtTokenUtil.getUsernameFromToken(jwtToken);
         User user = userService.findByEmail(email);
         question.setUser(user);
         questionService.postQuestion(question);
         return question;
    }

    @GetMapping("/{id}")
    public Optional<Question> findById(@PathVariable String id){
        Optional<Question> question = questionService.findById(id);
        return question;

    }



}
