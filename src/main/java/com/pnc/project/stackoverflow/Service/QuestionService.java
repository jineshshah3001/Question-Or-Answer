package com.pnc.project.stackoverflow.Service;

import com.pnc.project.stackoverflow.Entity.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionService {

    public List<Question> findAll();

    public void postQuestion(Question question);

    public Optional<Question> findById(String id);

    public Question findQuestionByAnswerId(long id);


}
