package com.pnc.project.stackoverflow.Controller;

import com.pnc.project.stackoverflow.Service.QuestionService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.pnc.project.stackoverflow.Entity.Question;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/stackoverflow/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;


    @GetMapping
    public List<Question> findAll(){
        return questionService.findAll();
    }

    @PostMapping
    public Question postQuestion(@RequestBody Question question){
        questionService.postQuestion(question);
        return question;

    }

    @GetMapping("/{id}")
    public Optional<Question> findById(@PathVariable String id){
        Optional<Question> question = questionService.findById(id);
        return question;

    }



}
