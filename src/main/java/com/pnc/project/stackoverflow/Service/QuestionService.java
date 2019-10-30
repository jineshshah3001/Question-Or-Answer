package com.pnc.project.stackoverflow.Service;

import com.pnc.project.stackoverflow.Entity.Question;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface QuestionService {

//    public List<Question> findAll();

    public void postQuestion(Question question);

    public Optional<Question> findById(String id);

    public Question findQuestionByAnswerId(long id);

    Page<Question> getAllQuestions(Integer pageNo, Integer pageSize);

    List<Question> findNewest();

    List<Question> findOldest();

    List<Question> findUnanswered();
}
